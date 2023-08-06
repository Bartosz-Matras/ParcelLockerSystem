package pl.matrasbartosz.parcellockersystem.entity.user.roles;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pl.matrasbartosz.parcellockersystem.entity.user.User;

import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    @ManyToMany
    @JoinTable(
            name = "roles_permissions",
            joinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "permissions_id", referencedColumnName = "id"))
    private Set<Permissions> permissions;

}
