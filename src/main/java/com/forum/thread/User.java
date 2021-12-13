package com.forum.thread;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "author")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String name;
    @OneToMany
    private List<Thread> threads;
    @OneToMany
    private List<Comment> comments;
    @CreationTimestamp
    private Timestamp timeCreated;



}
