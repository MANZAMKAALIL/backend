package com.example.demo.service;

import com.example.demo.model.Agent;
import com.example.demo.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgentService {
    @Autowired
    private AgentRepository agentRepository;

    public List<Agent> findAllAgents() {
        return agentRepository.findAll();
    }

    public Agent saveAgent(Agent agent) {
        return agentRepository.save(agent);
    }

    public Agent findAgentById(Long id) {
        return agentRepository.findById(id).orElse(null);
    }

    public Agent updateAgent(Long id, Agent agentDetails) {
        Agent agent = agentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Agent not found"));
        agent.setFirstName(agentDetails.getFirstName());
        agent.setLastName(agentDetails.getLastName());
        agent.setEmail(agentDetails.getEmail());
        agent.setPhoneNumber(agentDetails.getPhoneNumber());
        return agentRepository.save(agent);
    }

    public void deleteAgent(Long id) {
        Agent agent = agentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Agent not found"));
        agentRepository.delete(agent);
    }
}