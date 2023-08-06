package pl.matrasbartosz.parcellockersystem.entity.user.roles;

public enum ApplicationUserPermission {
    WORKER_READ("worker:read"),
    WORKER_WRITE("worker:write"),
    WORKER_DELETE("worker:delete"),
    CUSTOMER_READ("customer:read"),
    CUSTOMER_WRITE("customer:write"),
    CUSTOMER_DELETE("customer:delete");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
