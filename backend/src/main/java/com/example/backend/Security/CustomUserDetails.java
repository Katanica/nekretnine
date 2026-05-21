package com.example.backend.Security;
import com.example.backend.Entity.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;
public class CustomUserDetails implements UserDetails{

    private final Profile profile;

    public CustomUserDetails(Profile profile){
        this.profile = profile;
    }


    public Long getId(){
        return profile.getId();
    }

    public Profile getProfile(){
        return profile;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(new SimpleGrantedAuthority("ROLE_" + profile.getRole().name()));

    }

    @Override
    public String getPassword(){
        return profile.getPasswordHash();
    }
    @Override
    public String getUsername(){
        return profile.getEmail();
    }
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}
