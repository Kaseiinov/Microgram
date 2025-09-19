package kg.attractor.microgram.service.impl;

import kg.attractor.microgram.model.Role;
import kg.attractor.microgram.repository.RoleRepository;
import kg.attractor.microgram.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role findRoleByRole(String role){
        return roleRepository.findRoleByRole(role);
    }

}
