package narrakos.bredex_backend_test.security;

import narrakos.bredex_backend_test.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class AppUserDetails implements UserDetails {
    private final User user;

    public AppUserDetails(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.EMPTY_LIST;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // N/A
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // N/A
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // N/A
    }

    @Override
    public boolean isEnabled() {
        return true; // N/A
    }
}
