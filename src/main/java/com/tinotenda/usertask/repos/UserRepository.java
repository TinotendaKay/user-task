package com.tinotenda.usertask.repos;

import com.tinotenda.usertask.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
