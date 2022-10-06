package com.vitaliy.kairachka.arthew.repository;

import com.vitaliy.kairachka.arthew.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Vitaliy Kayrachka
 */
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
  User findUserByLogin(String name);
}
