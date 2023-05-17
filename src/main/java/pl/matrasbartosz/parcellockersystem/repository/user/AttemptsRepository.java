package pl.matrasbartosz.parcellockersystem.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.matrasbartosz.parcellockersystem.entity.user.Attempts;

import java.util.Optional;

public interface AttemptsRepository extends JpaRepository<Attempts, Long> {

    Optional<Attempts> findAttemptsByEmail(String email);
}
