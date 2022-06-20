package com.forum.thread.entity;



import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class Thread {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Long id;
    public String title;
    public String text;
    @CreationTimestamp
    public Timestamp timeCreated;
    @UpdateTimestamp
    public Timestamp timeUpdated;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="author_id")
    public UserObject user;
    @OneToMany(mappedBy = "thread")
    public List<Comment> comments;

    public Thread() {}

    public Thread(String title, String text) {
        this.title = title;
        this.text = text;

    }
    public Thread(String title, String text, UserObject user) {
        this.title = title;
        this.text = text;
        this.user = user;

    }



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

    @Override
    public String toString() {
        return "Thread{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", timeCreated=" + timeCreated +
                ", timeUpdated=" + timeUpdated +
                ", user=" + user +
                ", comments=" + comments +
                '}';
    }
}
