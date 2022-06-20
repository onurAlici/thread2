package com.forum.thread.services;


import com.forum.thread.entity.Comment;
import com.forum.thread.entity.Thread;
import com.forum.thread.entity.UserObject;
import com.forum.thread.models.CommentDTO;
import com.forum.thread.models.CommentModel;
import com.forum.thread.repos.CommentRepository;
import com.forum.thread.repos.ThreadRepository;
import com.forum.thread.repos.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ThreadRepository threadRepository;

    public CommentDTO getComment(Long id) {
        CommentDTO c = commentRepository.findDTOById(id).get();

        return c;
    }

    public List<CommentDTO> getCommentsByThread(Long thread_id) {
        Thread t = threadRepository.findById(thread_id).get();
        List<CommentDTO> comments = commentRepository.findByThread(t);

        return comments;
    }
    public Comment createComment(CommentModel c) {
        Thread thread_id    = threadRepository.findById( Long.valueOf(c.getThread_id()) ).get();

        UserObject user_id      = userRepository.findById(Long.valueOf(c.getUser_id())).get();
        String text       = c.getText();

        Comment comment = new Comment( thread_id,  user_id,  text);

        commentRepository.save(comment);

        return comment;




    }

}
