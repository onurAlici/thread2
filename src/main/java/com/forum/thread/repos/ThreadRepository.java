package com.forum.thread.repos;



import com.forum.thread.entity.Thread;
import com.forum.thread.models.ThreadDTO;
import com.forum.thread.models.ThreadPagesDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ThreadRepository extends PagingAndSortingRepository<Thread,Long> {

        Page<ThreadPagesDTO> findAllBy(Pageable pageable);

        Optional<ThreadDTO> findDTOById(Long Id);


}