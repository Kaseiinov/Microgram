package kg.attractor.microgram.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Getter
@Setter
@Builder
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    private String password;
    private String description;
    private String avatar;
    private Boolean enabled;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Collection<File> files;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Collection<Comment> comments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Collection<Like> likes;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "subscriptions",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "user_subscriber_id")
    )
    private Collection<User> subscriptions;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "subscriptions")
    private Collection<User> followers;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "users")
    private Collection<Role> roles;

}
