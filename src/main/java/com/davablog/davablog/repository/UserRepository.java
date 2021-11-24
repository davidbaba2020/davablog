package com.davablog.davablog.repository;

import com.davablog.davablog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Post, Long> {
}
