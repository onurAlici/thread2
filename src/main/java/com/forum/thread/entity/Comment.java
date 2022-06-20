package com.forum.thread.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table
public class Comment {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @CreationTimestamp
    private Timestamp timeCreated;
    @UpdateTimestamp
    private Timestamp timeUpdated;
    private String text;

    public Comment() {}
    public Comment(Thread thread, UserObject user, String text) {
        this.thread = thread;
        this.user = user;
        this.text = text;
    }
    public Comment(Thread thread, String comment_id, UserObject user, String text) {
        this.thread = thread;
        this.id = Long.valueOf(comment_id);
        this.user = user;
        this.text = text;
    }

    @ManyToOne
    @JoinColumn(name="author_id", nullable=false)
    private UserObject user;
    @ManyToOne
    @JoinColumn(name="thread_id", nullable=false)
    private Thread thread;
}
