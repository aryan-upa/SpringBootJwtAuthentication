package com.example.demo1.bootstrap;

import com.example.demo1.entities.Role;
import com.example.demo1.entities.RoleEnum;
import com.example.demo1.repositories.RoleRepository;
import lombok.NonNull;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Component
public class RoleSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final RoleRepository roleRepository;

    public RoleSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void onApplicationEvent(@NonNull ContextRefreshedEvent event) {
        this.saveRoles();
    }

    private void saveRoles() {
        RoleEnum[] roles = RoleEnum.values();
        Arrays.stream(roles).forEach(role -> {
            Optional<Role> optionalRole = roleRepository.findByName(role);
            if (optionalRole.isEmpty()) {
                Role roleToSave = new Role();
                roleToSave.setName(role);
                roleRepository.save(roleToSave);
            }
        });
    }
}
