package pl.matrasbartosz.parcellockersystem.entity.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "attempts")
@Getter
@Setter
public class Attempts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "attempts", nullable = false, columnDefinition = "integer default 0")
    private int attempts;

}
