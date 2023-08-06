package pl.matrasbartosz.parcellockersystem.repository.user.roles;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.matrasbartosz.parcellockersystem.entity.user.roles.Roles;

public interface RolesRepository extends JpaRepository<Roles, Long> {
    Roles findRolesByName(String name);
}
