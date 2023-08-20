package ru.mobile.effective.socialmedia.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@ToString(exclude = "posts")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 25, unique = true, nullable = false)
    private String username;

    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Post> posts;

    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Message> messages;
    public void addingPost(Post post) {
        if (this.posts == null) {
            this.posts = new ArrayList<>();
            this.posts.add(post);
            post.setUser(this);
        }
    }
    public void addingMessage(Message message) {
        if(this.messages == null) {
            this.messages = new ArrayList<>();
            this.messages.add(message);
            message.setUser(this);
        }
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
        return true;
    }

}
