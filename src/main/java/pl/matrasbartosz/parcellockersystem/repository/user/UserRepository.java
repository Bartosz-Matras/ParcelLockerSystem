package pl.matrasbartosz.parcellockersystem.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.matrasbartosz.parcellockersystem.entity.user.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmailAndPhoneNumber(String email, String phoneNumber);

    Optional<User> findUserByEmail(String email);
}
