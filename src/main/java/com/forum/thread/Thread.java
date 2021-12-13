package com.forum.thread;



import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;


@Entity
@Getter
@Setter
@Table
public class Thread {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String title;
    private String text;
    @CreationTimestamp
    private Timestamp timeCreated;
    @UpdateTimestamp
    private Timestamp timeUpdated;
    @ManyToOne
    @JoinColumn(name="author_id", nullable=false)
    private User user;
    @OneToMany
    private List<Comment> comments;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Thread thread = (Thread) o;
        return getTitle().equals(thread.getTitle()) && getText().equals(thread.getText()) && Objects.equals(getTimeCreated(), thread.getTimeCreated()) && Objects.equals(getTimeUpdated(), thread.getTimeUpdated()) && Objects.equals(getComments(), thread.getComments());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getText(), getTimeCreated(), getTimeUpdated(), getComments());
    }
}
