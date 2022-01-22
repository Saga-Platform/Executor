package com.saga.executor.dao;

import com.saga.executor.models.Resume;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;

public interface ResumeRepository extends ReactiveNeo4jRepository<Resume, String> {
}
