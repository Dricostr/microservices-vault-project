package com.immobilier.agent.controller;

import com.immobilier.agent.model.Agent;
import com.immobilier.agent.repository.AgentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/agents")
@RequiredArgsConstructor
public class AgentController {

    private final AgentRepository agentRepository;

    @GetMapping
    public List<Agent> getAll() {
        return agentRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agent> getById(@PathVariable Long id) {
        return agentRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Agent createAgent(@RequestBody Agent agent) {
        agent.setActive(true);
        return agentRepository.save(agent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agent> update(
            @PathVariable Long id, @RequestBody Agent updated) {
        return agentRepository.findById(id).map(a -> {
            a.setFirstName(updated.getFirstName());
            a.setLastName(updated.getLastName());
            a.setEmail(updated.getEmail());
            a.setPhone(updated.getPhone());
            a.setCommissionRate(updated.getCommissionRate());
            return ResponseEntity.ok(agentRepository.save(a));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        agentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}