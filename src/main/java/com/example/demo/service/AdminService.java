package com.example.demo.service;

import com.example.demo.model.Admin;
import com.example.demo.model.Agent;
import com.example.demo.model.Tenant;
import com.example.demo.model.Users;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.AgentRepository;
import com.example.demo.repository.TenantRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final AgentRepository agentRepository;
    private final TenantRepository tenantRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminService(AdminRepository adminRepository, AgentRepository agentRepository, TenantRepository tenantRepository,
                        UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.agentRepository = agentRepository;
        this.tenantRepository = tenantRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Admin createAdmin(Admin admin) {
        Users user = new Users();
        user.setUsername(admin.getEmail());
        user.setPassword(passwordEncoder.encode("defaultPassword")); // Default password should be changed later
        user.setRole("ADMIN");
        userRepository.save(user);
        admin.setUserAccount(user);
        return adminRepository.save(admin);
    }

    public Agent createAgent(Agent agent) {
        Users user = new Users();
        user.setUsername(agent.getEmail());
        user.setPassword(passwordEncoder.encode("defaultPassword")); // Default password should be changed later
        user.setRole("AGENT");
        userRepository.save(user);

        agent.setUserAccount(user);
        return agentRepository.save(agent);
    }

    public Tenant createTenant(Tenant tenant) {
        // No need to create a user account for tenant
        // Directly save the tenant without associating with a user account
        return tenantRepository.save(tenant);
    }
}
