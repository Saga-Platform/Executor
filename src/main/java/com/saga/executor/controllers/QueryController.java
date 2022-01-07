package com.saga.executor.controllers;

import com.saga.executor.dao.MonitorRepository;
import com.saga.executor.dao.StudentRepository;
import com.saga.executor.models.users.Monitor;
import com.saga.executor.models.users.Student;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Controller
@QueryMapping
public class QueryController {

    private final MonitorRepository monitorRepo;
    private final StudentRepository studentRepo;

    public QueryController(MonitorRepository repo, StudentRepository studentRepo) {
        this.monitorRepo = repo;
        this.studentRepo = studentRepo;
    }

    @SchemaMapping
    public Flux<Student> students() {
        return studentRepo.findAll();
    }

    @SchemaMapping
    public Flux<Student> studentsOfDepartment(@Argument String departmentId) {
        return studentRepo.findByDepartment_Id(UUID.fromString(departmentId));
    }

    @SchemaMapping
    public Mono<Monitor> monitor(@Argument String id) {
        return monitorRepo.findById(id);
    }
}
