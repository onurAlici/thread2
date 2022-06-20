package com.forum.thread.models;


import com.forum.thread.entity.UserObject;

import java.sql.Timestamp;

public interface ThreadPagesDTO {
    Long getId();
    String getTitle();
    String getText();
    UserDTO getUser();
    Timestamp getTimeCreated();

}
