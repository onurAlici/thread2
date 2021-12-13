package com.forum.thread;



import org.springframework.data.repository.PagingAndSortingRepository;

public interface ThreadRepository extends PagingAndSortingRepository<Thread,Long> {

}