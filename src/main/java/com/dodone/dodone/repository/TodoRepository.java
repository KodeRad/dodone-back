package com.dodone.dodone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dodone.dodone.entity.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
}
