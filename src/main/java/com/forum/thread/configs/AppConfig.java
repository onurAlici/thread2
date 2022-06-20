package com.forum.thread.configs;

import com.forum.thread.entity.Comment;
import com.forum.thread.entity.Thread;
import com.forum.thread.models.ThreadDTO;
import com.forum.thread.repos.CommentRepository;
import com.forum.thread.repos.ThreadRepository;
import com.forum.thread.entity.UserObject;
import com.forum.thread.repos.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


//spring.sql.init.data-locations= classpath:/data.sql
@Configuration
public class AppConfig {

    public static final Logger log = LoggerFactory.getLogger(com.forum.thread.configs.AppConfig.class);



    @Autowired
    private PasswordEncoder encoder2;

    @Autowired
    private UserRepository userRepo;

    @Bean
    public PasswordEncoder encoder2() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    ApplicationRunner init(ThreadRepository repo, UserRepository userRepo, CommentRepository commentRepository) {
        return args -> {
            log.info("app runner çalışıyor");
            UserObject user1 = userRepo.findById(1L).orElse(null);
            user1.setPassword(encoder2.encode("123"));
            userRepo.save(user1);

            Thread t1 = new Thread("apptitle","apptext", user1);

            repo.save(t1);
            Comment c1 = new Comment(t1,user1,"comment1");
            commentRepository.save(c1);
            for(int i=1; i<88; i++){
                Thread tt = new Thread("title"+i,"apptext"+i, user1);
                Thread saved = repo.save(tt);
                Comment cc = new Comment(saved,user1,"comment" +i);
                commentRepository.save(cc);
            }


        };
    }

}
