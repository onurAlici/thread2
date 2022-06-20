package com.forum.thread.controllers;


import com.forum.thread.entity.Thread;
import com.forum.thread.configs.AppConfig;
import com.forum.thread.models.ThreadDTO;
import com.forum.thread.models.ThreadPagesDTO;
import com.forum.thread.repos.ThreadRepository;
import com.forum.thread.repos.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path="/thread")
@CrossOrigin(origins="*")
public class ThreadController {

    @Autowired
    private ThreadRepository repo;
    @Autowired
    private UserRepository userRepo;

    public static final Logger log = LoggerFactory.getLogger(ThreadController.class);

    @GetMapping("/{id}")
    public ThreadDTO thread(@PathVariable("id") Long id) {


        return repo.findDTOById(id).orElse(null);
    }

    @GetMapping("/recent")
    public Page<ThreadPagesDTO> listDTO(@PageableDefault(page = 0, size = 2) Pageable pageable) {
        Page page = repo.findAllBy(pageable);


        return page;
    }

    @PostMapping("/create")
    public Thread createThread(@RequestBody Thread newThread, HttpServletRequest request) {
        Thread saved = repo.save(newThread);

        saved.setUser(userRepo.findById(1L).orElse(null));
        System.out.println(saved);
        AppConfig.log.info(String.valueOf(saved));

        AppConfig.log.info("ip adress: " + String.valueOf(request.getRemoteAddr()));

        return saved;
    }
    @DeleteMapping("/{id}")
    void deleteThread(@PathVariable Long id) {
        repo.deleteById(id);
    }

}
