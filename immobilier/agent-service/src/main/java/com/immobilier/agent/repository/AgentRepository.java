package com.immobilier.agent.repository;

import com.immobilier.agent.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AgentRepository extends JpaRepository<Agent, Long> {
    List<Agent> findByAgency(String agency);
    List<Agent> findBySpecialization(String specialization);
    List<Agent> findByActive(Boolean active);
}
