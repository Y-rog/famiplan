package com.yrog.famiplan.repository;

import com.yrog.famiplan.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long>{

   User findUserByUsername(String username);

}
