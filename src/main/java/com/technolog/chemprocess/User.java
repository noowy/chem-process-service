package com.technolog.chemprocess;

import com.sun.tools.javac.jvm.Gen;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NotFound;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.Collection;

@Entity
public class User implements UserDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    @NotBlank
    private final String username;

    @Length(min = 4)
    private final String password;

    private final String fullname;

    private User()
    {
        username = null;
        password = null;
        fullname = null;
    }

    public User(String username, String password, String fullname)
    {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword()
    {
        return password;
    }

    @Override
    public String getUsername()
    {
        return username;
    }

    public String getFullname()
    {
        return fullname;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }
}
