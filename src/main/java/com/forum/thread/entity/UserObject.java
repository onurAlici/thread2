package com.forum.thread.entity;


import com.forum.thread.entity.Comment;
import com.forum.thread.entity.Thread;
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
public class UserObject {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    private String username;
    private String password;
    private String role;

    public UserObject() {}

    public UserObject(String Username, String name, String password) {
        this.username = Username;
        this.name = name;
        this.password= password;
    }


    @OneToMany(mappedBy = "user")
    private List<Thread> threads;
    @OneToMany(mappedBy = "user")
    private List<Comment> comments;
    @CreationTimestamp
    private Timestamp timeCreated;

    @Override
    public String toString() {
        return "UserObject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", threads=" + threads +
                ", comments=" + comments +
                ", timeCreated=" + timeCreated +
                '}';
    }
}
