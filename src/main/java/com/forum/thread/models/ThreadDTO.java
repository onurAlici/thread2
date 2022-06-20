package com.forum.thread.models;


import java.sql.Timestamp;
import java.util.List;

public interface ThreadDTO {
    Long getId();
    String getTitle();
    String getText();
    UserDTO getUser();
    Timestamp getTimeCreated();
    List<CommentDTO> getComments();
}
