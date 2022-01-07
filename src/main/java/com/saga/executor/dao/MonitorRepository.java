package com.saga.executor.dao;

import com.saga.executor.models.users.Monitor;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;

public interface MonitorRepository extends ReactiveNeo4jRepository<Monitor, String> {
}
