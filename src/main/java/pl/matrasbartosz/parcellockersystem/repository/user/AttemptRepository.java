package pl.matrasbartosz.parcellockersystem.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.matrasbartosz.parcellockersystem.entity.user.Attempt;

import java.util.Optional;

public interface AttemptRepository extends JpaRepository<Attempt, Long> {

    Optional<Attempt> findAttemptByEmail(String email);
}
