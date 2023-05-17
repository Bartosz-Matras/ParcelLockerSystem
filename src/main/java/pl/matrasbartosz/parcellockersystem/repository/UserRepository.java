package pl.matrasbartosz.parcellockersystem.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.matrasbartosz.parcellockersystem.entity.user.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("Select u from User u" +
            " left join fetch u.address")
    List<User> findAllUsers(Pageable pageable);

    Optional<User> findUserByEmailAndPhoneNumber(String email, String phoneNumber);
    Optional<User> findUserByEmail(String email);

}
