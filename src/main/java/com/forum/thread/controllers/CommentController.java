package com.forum.thread.controllers;


import com.forum.thread.entity.Comment;
import com.forum.thread.entity.Thread;
import com.forum.thread.configs.AppConfig;
import com.forum.thread.models.CommentDTO;
import com.forum.thread.models.CommentModel;
import com.forum.thread.repos.ThreadRepository;
import com.forum.thread.repos.UserRepository;
import com.forum.thread.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path="/comment")
@CrossOrigin(origins="*")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/create")
    public Comment createComment(@RequestBody CommentModel newComment, HttpServletRequest request) {
        Comment c = commentService.createComment(newComment);

        return c;
    }

    @GetMapping("/{id}")
    public CommentDTO getComment(@PathVariable("id") Long id ) {
        CommentDTO c = commentService.getComment(id);

        return c;
    }



}
