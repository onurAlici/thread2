package com.forum.thread.repos;



import com.forum.thread.entity.Comment;

import com.forum.thread.entity.Thread;
import com.forum.thread.models.CommentDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends PagingAndSortingRepository<Comment,Long> {

    List<CommentDTO> findByThread(Thread thread);

    Optional<CommentDTO> findDTOById(Long id);





}
