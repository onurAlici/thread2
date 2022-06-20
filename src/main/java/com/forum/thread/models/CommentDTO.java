package com.forum.thread.models;

public interface CommentDTO {
    Long getId();

    String getText();
    UserDTO getUser();
}
