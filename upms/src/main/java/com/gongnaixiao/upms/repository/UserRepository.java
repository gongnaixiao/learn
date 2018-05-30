package com.gongnaixiao.upms.repository;

import com.gongnaixiao.upms.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
