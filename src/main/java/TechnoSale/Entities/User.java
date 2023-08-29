package TechnoSale.Entities;

import TechnoSale.Entities.Template.AbsEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
public class User extends AbsEntity implements UserDetails{

    @Column(name = "firstName",nullable = false)
    private String firstName;

    @Column(name = "lastName",nullable = false)
    private String lastName;

    @Column(name = "phoneNumber",nullable = false)
    private String phoneNumber;

    @Column(name = "gmail",nullable = false)
    private String gmail;

    @Column(name = "user_name",nullable = false,unique = true)
    private String userName;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    private boolean enabled = true;
    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() { //acountni muddati tugamaganmi?
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() { //acount bloklaganmagana?
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() { //yaroqlik muddati otib ketmagana?
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
