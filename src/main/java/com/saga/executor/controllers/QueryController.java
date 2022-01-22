package com.saga.executor.controllers;

import com.saga.executor.dao.ResumeRepository;
import com.saga.executor.models.Resume;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@QueryMapping
public class QueryController {
    private final ResumeRepository resumeRepo;

    public QueryController(ResumeRepository resumeRepo) {
        this.resumeRepo = resumeRepo;
    }

    @SchemaMapping
    public Flux<Resume> getResumes() {
        return resumeRepo.findAll();
    }

    @SchemaMapping
    public Mono<Resume> updateResume(Resume resume) {
        return resumeRepo.save(resume);
    }

    @SchemaMapping
    Mono<String> deleteResume(@Argument String id) {
        return resumeRepo.deleteById(id).thenReturn(id);
    }
}
