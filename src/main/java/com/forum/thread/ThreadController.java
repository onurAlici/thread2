package com.forum.thread;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path="/thread")
@CrossOrigin(origins="*")
public class ThreadController {
    private ThreadRepository repo;

    ThreadController(ThreadRepository repo){
        this.repo = repo;
    }

    @GetMapping("/{id}")
    public Thread thread(@PathVariable("id") Long id) {
        return repo.findById(id).orElse(null);
    }

    @GetMapping("/recent")
    public HashMap<String,Object> recent(@PageableDefault(page = 0, size = 2) Pageable pageable) {
        HashMap<String,Object> map = new HashMap<>();
        Page page = repo.findAll(pageable);
        Long elementNumber = page.getTotalElements();
        Integer pageNumber = page.getTotalPages();

        List<Thread> threads = page.getContent();
        map.put("elementNumber", elementNumber);
        map.put("pageNumber", pageNumber);
        map.put("threads", threads);

        return map;
    }
    @GetMapping("/list")
    public Page<Thread> list(@PageableDefault(page = 0, size = 2) Pageable pageable) {
        Page page = repo.findAll(pageable);


        return page;
    }
}
