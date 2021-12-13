package com.forum.thread;


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

    @ManyToOne
    @JoinColumn(name="author_id", nullable=false)
    private User user;
    @ManyToOne
    @JoinColumn(name="thread_id", nullable=false)
    private Thread thread;
}
