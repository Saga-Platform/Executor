package com.saga.executor;

import com.github.javafaker.Faker;
import com.saga.executor.models.Department;
import com.saga.executor.models.users.Monitor;
import com.saga.executor.models.users.Student;
import lombok.extern.slf4j.Slf4j;
import org.neo4j.driver.Driver;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.core.Neo4jOperations;
import org.springframework.data.neo4j.core.ReactiveDatabaseSelectionProvider;
import org.springframework.data.neo4j.core.transaction.ReactiveNeo4jTransactionManager;
import org.springframework.data.neo4j.repository.config.ReactiveNeo4jRepositoryConfigurationExtension;
import org.springframework.stereotype.Component;
import org.springframework.transaction.ReactiveTransactionManager;
import reactor.core.publisher.Hooks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;

@Slf4j
@SpringBootApplication
public class ExecutorApplication {

    public static void main(String[] args) {
        Hooks.onErrorDropped(e -> {
            if (e instanceof CancellationException || e.getCause() instanceof CancellationException) {
                log.trace("Operator called default onErrorDropped", e);
            } else {
                log.error("Operator called default onErrorDropped", e);
            }
        });

        SpringApplication.run(ExecutorApplication.class, args);
    }

    @Bean(ReactiveNeo4jRepositoryConfigurationExtension.DEFAULT_TRANSACTION_MANAGER_BEAN_NAME)
    public ReactiveTransactionManager reactiveTransactionManager(Driver driver, ReactiveDatabaseSelectionProvider databaseNameProvider) {
        return new ReactiveNeo4jTransactionManager(driver, databaseNameProvider);
    }

    @Component
    public static class Runner implements ApplicationRunner {

        private final Faker faker = new Faker();

        private final Neo4jOperations ops;

        public Runner(Neo4jOperations ops) {
            this.ops = ops;
        }

        @Override
        public void run(ApplicationArguments args) {
            ops.deleteAll(Department.class);
            ops.deleteAll(Student.class);
            ops.deleteAll(Monitor.class);

            List<Department> ds = new ArrayList<>();

            for (int i = 0; i < 10; i++) {
                var d = new Department();
                d.setName(faker.educator().course());
                ds.add(d);
            }

            ds = ops.saveAll(ds);

            var ms = new ArrayList<Monitor>();
            for (int i = 0; i < 60; i++) {
                var m = new Monitor();

                m.setId(faker.bothify("??#####"));
                m.setFirstName(faker.name().firstName());
                m.setLastName(faker.name().lastName());
                m.setEmail(faker.internet().emailAddress());
                m.setPhoneNumber(faker.phoneNumber().cellPhone());

                var dds = new ArrayList<Department>();
                dds.add(ds.get(faker.number().numberBetween(0, ds.size())));

                if (faker.bool().bool()) {
                    dds.add(ds.get(faker.number().numberBetween(0, ds.size())));
                }

                m.setDepartments(dds);

                ms.add(m);
            }
            ops.saveAll(ms);

            var ss = new ArrayList<Student>();
            for (int i = 0; i < 250; i++) {
                var s = new Student();

                s.setId(faker.numerify("1######"));
                s.setFirstName(faker.name().firstName());
                s.setLastName(faker.name().lastName());
                s.setEmail(faker.internet().emailAddress());
                s.setPhoneNumber(faker.phoneNumber().cellPhone());
                s.setDepartment(ds.get(faker.number().numberBetween(0, ds.size())));

                ss.add(s);
            }
            ops.saveAll(ss);
        }
    }

}
