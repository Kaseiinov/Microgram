package kg.attractor.microgram.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "files")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "file_name")
    private String fileName;
    private String description;
    @Column(name = "date_time_publish")
    private LocalDateTime dateTimePublished;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "file", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Collection<Comment> comments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "file", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Collection<Like> likes;
}
