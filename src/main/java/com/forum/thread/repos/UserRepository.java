package com.forum.thread.repos;


import com.forum.thread.entity.UserObject;
import com.forum.thread.models.UserDTO;
import com.forum.thread.models.UserpDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserObject, Long> {
    Optional<UserObject> findUserObjectByUsername(String username);

    Optional<UserpDTO> findUserDTOByUsername(String username);
    Optional<UserDTO> findDTOByUsername(String username);
}
