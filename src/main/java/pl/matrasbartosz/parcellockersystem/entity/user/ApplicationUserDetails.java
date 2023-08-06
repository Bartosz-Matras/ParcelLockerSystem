package pl.matrasbartosz.parcellockersystem.entity.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.matrasbartosz.parcellockersystem.entity.user.roles.Permissions;
import pl.matrasbartosz.parcellockersystem.entity.user.roles.Roles;


import java.util.*;

public class ApplicationUserDetails implements UserDetails {

    private final Collection<? extends GrantedAuthority> grantedAuthorities;
    private final User user;
    private final boolean isAccountNonExpired;
    private final boolean isAccountNonLocked;
    private final boolean isCredentialsNonExpired;

    public ApplicationUserDetails(User user) {
        this.grantedAuthorities = setAuthorities(user);
        this.user = user;
        this.isAccountNonExpired = true;
        this.isAccountNonLocked = true;
        this.isCredentialsNonExpired = true;
    }

    private Collection<GrantedAuthority> setAuthorities(User user) {
        Collection<Roles> roles = user.getRoles();
        List<GrantedAuthority> authorities = new ArrayList<>(roles.size());
        for (Roles role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
            for (Permissions permissions : role.getPermissions()) {
                authorities.add(new SimpleGrantedAuthority(permissions.getName()));
            }
        }
        return authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.user.isEnabled();
    }
}
