package com.saga.executor;

import com.github.javafaker.Faker;
import com.saga.executor.models.*;
import com.saga.executor.models.users.Monitor;
import com.saga.executor.models.users.Student;
import com.saga.executor.models.users.Supervisor;
import com.saga.executor.models.utils.Address;
import com.saga.executor.models.utils.SimpleReview;
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
import org.springframework.transaction.ReactiveTransactionManager;
import reactor.core.publisher.Hooks;

import java.net.URI;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import java.util.stream.Collectors;

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
// Uncomment following line to reset DB to a new generated state
//    @Component
    public static class Runner implements ApplicationRunner {

        private static final String SEM = "A2021";
        private static final String F1 = "567fc3b2f51c752aa08242d2b97881ab6445436bcca880b11dffd2ed2fa64645:eb061d9e-6cbd-409c-9f11-b38be1d43a91";
        private static final String F2 = "f0ab4954979e47a7c73c2a859576a8cc59b0ed7c56f6c798beca40f6532b7269:83d01d42-1061-4a58-992e-e3981cd5183b";

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
            ops.deleteAll(Address.class);
            ops.deleteAll(Company.class);
            ops.deleteAll(Supervisor.class);
            ops.deleteAll(InternshipOffer.class);
            ops.deleteAll(InternshipApplication.class);
            ops.deleteAll(SimpleReview.class);
            ops.deleteAll(Resume.class);

            List<Company> cs = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                var c = new Company();
                c.setName(faker.company().name());
                c.setPhoneNumber(faker.phoneNumber().phoneNumber());
                c.setAddress(fakeAddress(faker));
                cs.add(c);
            }
            cs = ops.saveAll(cs);

            var sps = new ArrayList<Supervisor>();
            int csIdx = 0;
            for (int i = 0; i < 7; i++) {
                var s = new Supervisor();
                s.setId(UUID.randomUUID().toString());
                s.setFirstName(faker.name().firstName());
                s.setLastName(faker.name().lastName());
                s.setEmail(faker.internet().emailAddress());
                s.setPhoneNumber(faker.phoneNumber().cellPhone());
                s.setCompany(cs.get(csIdx++));

                if (csIdx >= cs.size()) {
                    csIdx = 0;
                }

                sps.add(s);
            }
            sps.forEach(ops::save);

            List<Department> ds = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                var d = new Department(null, faker.educator().course());
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

                if (faker.bool().bool() && faker.bool().bool()) {
                    dds.add(ds.get(faker.number().numberBetween(0, ds.size())));
                }

                m.setDepartments(dds);

                ms.add(m);
            }
            ops.saveAll(ms);

            List<Student> ss = new ArrayList<>();
            for (int i = 0; i < 50; i++) {
                var s = new Student();

                s.setId(faker.numerify("1######"));
                s.setFirstName(faker.name().firstName());
                s.setLastName(faker.name().lastName());
                s.setEmail(faker.internet().emailAddress());
                s.setAddress(fakeAddress(faker));
                s.setPhoneNumber(faker.phoneNumber().cellPhone());
                s.setDepartment(ds.get(faker.number().numberBetween(0, ds.size())));
                s.setSemesters(List.of(SEM));
                ss.add(s);
            }
            ss = ops.saveAll(ss);

            var rs = new ArrayList<Resume>();
            for (Student s : ss) {
                var r = new Resume(null,
                        s,
                        s.getFirstName() +"'s resume",
                        URI.create("http://filer.saga.int.slongpre.com/" + (faker.bool().bool() ? F1 : F2)),
                        new SimpleReview());

                rs.add(ops.save(r));
            }

            List<InternshipOffer> ios = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                var o = new InternshipOffer();
                o.setSupervisor(sps.get(faker.number().numberBetween(0, sps.size())));
                o.setCreatedOn(ZonedDateTime.now());
                o.setClosesOn(ZonedDateTime.now().plusDays(14));
                o.setOpenings(faker.number().numberBetween(1, 5));
                o.setDepartment(ds.get(faker.number().numberBetween(0, ds.size())));
                o.setTitle(faker.company().profession());
                o.setDescription(faker.elderScrolls().quote());
                o.setStartsOn(ZonedDateTime.now().plusMonths(2));
                o.setFinishesOn(ZonedDateTime.now().plusMonths(5));
                o.setSalary(faker.number().randomDouble(2, 10, 50));
                o.setWorkdayStartsAt(LocalTime.of(8, 30));
                o.setWorkdayFinishesAt(LocalTime.of(17, 30));
                o.setAttachedFile(URI.create("http://filer.saga.int.slongpre.com/" + (faker.bool().bool() ? F1 : F2)));
                o.setSemester(SEM);
                o.setReview(new SimpleReview());

                var s = new ArrayList<Student>();
                s.add(ss.get(faker.number().numberBetween(0, ss.size())));
                for (int j = 0; j < 10; j++) {
                    if (faker.bool().bool()) {
                        s.add(ss.get(faker.number().numberBetween(0, ss.size())));
                    }
                }

                o.setVisibleTo(s);

                ios.add(o);
            }
            ios = ios.stream().map(ops::save).collect(Collectors.toList());

            var ias = new ArrayList<InternshipApplication>();
            for (int i = 0; i < 15; i++) {
                var a = new InternshipApplication(
                        null,
                        ios.get(faker.number().numberBetween(0, ios.size())),
                        rs.get(faker.number().numberBetween(0, rs.size())),
                        null
                        );
                ias.add(ops.save(a));
            }

            log.info("Database reset complete");
        }

        private Address fakeAddress(Faker faker) {
            var a = new Address();

            a.setNumber(faker.address().buildingNumber());
            a.setStreet(faker.address().streetName());
            a.setCity(faker.address().city());
            a.setPostalCode(faker.address().zipCode());
            a.setProvince(faker.address().state());
            a.setCountry(faker.address().country());

            return a;
        }
    }

}
