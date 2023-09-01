package me.sewook.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name="users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name="email", nullable = false, unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name="nickname", unique = true)
    private String nickname;

    @Builder
    public User(String email, String password, String auth,String nickname){
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    @Override //권한 반환
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override //id 반환 (고유값)
    public String getUsername(){
        return email;
    }

    @Override //비번 반환
    public String getPassword(){
        return password;
    }

    //계정 만료 여부 반환
    @Override
    public boolean isAccountNonExpired(){
        return true;
    }
    // 계정 잠금 여부 반환
    @Override
    public boolean isAccountNonLocked(){
        return true;
    }
    // 비번 만료 여부 반환
    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }
    //계정 사용 가능 여부 반환
    @Override
    public boolean isEnabled(){
        //계정이 사용가능한지 확인하는 로직
        return true; //true -> 사용 가능
    }
    public User update(String nickname){
        this.nickname = nickname;
        return this;
    }

}
