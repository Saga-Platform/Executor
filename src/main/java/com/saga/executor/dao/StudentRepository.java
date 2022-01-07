package com.saga.executor.dao;

import com.saga.executor.models.users.Student;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface StudentRepository extends ReactiveNeo4jRepository<Student, String> {

    Flux<Student> findByDepartment_Id(UUID id);
}
