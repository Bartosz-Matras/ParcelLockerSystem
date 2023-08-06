package pl.matrasbartosz.parcellockersystem.repository.user.roles;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.matrasbartosz.parcellockersystem.entity.user.roles.Permissions;

public interface PermissionsRepository extends JpaRepository<Permissions, Long> {
}
