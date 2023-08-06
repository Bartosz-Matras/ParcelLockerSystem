package pl.matrasbartosz.parcellockersystem.entity.user.roles;


import com.google.common.collect.Sets;

import java.util.Set;

import static pl.matrasbartosz.parcellockersystem.entity.user.roles.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    ADMIN(Sets.newHashSet(CUSTOMER_WRITE, CUSTOMER_READ, CUSTOMER_DELETE, WORKER_WRITE, WORKER_READ, WORKER_DELETE)),
    WORKER(Sets.newHashSet(CUSTOMER_WRITE, CUSTOMER_READ, CUSTOMER_DELETE, WORKER_WRITE, WORKER_READ)),
    CUSTOMER(Sets.newHashSet(CUSTOMER_WRITE, CUSTOMER_READ));

    private final Set<ApplicationUserPermission> permission;

    ApplicationUserRole(Set<ApplicationUserPermission> permission) {
        this.permission = permission;
    }

    public Set<ApplicationUserPermission> getPermission() {
        return permission;
    }
}
