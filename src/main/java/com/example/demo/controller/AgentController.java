package com.example.demo.controller;


import com.example.demo.model.Agent;
import com.example.demo.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agents")
public class AgentController {

    @Autowired
    private AgentService agentService;

    @GetMapping("/getAllAgents")
    public List<Agent> getAllAgents() {
        return agentService.findAllAgents();
    }

    @PostMapping
    public Agent createAgent(@RequestBody Agent agent) {
        return agentService.saveAgent(agent);
    }

    @GetMapping("/getAgentById/{id}")
    public Agent getAgentById(@PathVariable Long id) {
        return agentService.findAgentById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Agent> updateAgent(@PathVariable Long id, @RequestBody Agent agentDetails) {
        return ResponseEntity.ok(agentService.updateAgent(id, agentDetails));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAgent(@PathVariable Long id) {
        agentService.deleteAgent(id);
    }
}
