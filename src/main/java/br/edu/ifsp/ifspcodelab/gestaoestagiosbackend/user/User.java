package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.EntityStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User implements UserDetails {
    @Id
    private UUID id;
    private String registration;
    private String name;
    private String password;
    private String email;

    @Enumerated(EnumType.STRING)
    private EntityStatus isActivated;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "users_roles")
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Collection<Role> roles = new ArrayList<>();

    public User(String registration, String name, String password, String email, Collection<Role> roles) {
        this.id = UUID.randomUUID();
        this.registration = registration;
        this.name = name;
        this.password = password;
        this.email = email;
        this.roles = roles;
        this.isActivated = EntityStatus.DISABLED;
    }

    public User(String registration, String name, String email, Collection<Role> roles) {
        this.id = UUID.randomUUID();
        this.registration = registration;
        this.name = name;
        this.email = email;
        this.roles = roles;
        this.isActivated = EntityStatus.DISABLED;
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return authorities;
    }

    @Override
    public String getUsername() {
        return registration;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActivated == EntityStatus.ENABLED;
    }
}
