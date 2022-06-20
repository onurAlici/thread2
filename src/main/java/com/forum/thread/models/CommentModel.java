package com.forum.thread.models;


import lombok.Data;

@Data
public class CommentModel {

    private String thread_id;
    private String user_id;
    private String text;

}
