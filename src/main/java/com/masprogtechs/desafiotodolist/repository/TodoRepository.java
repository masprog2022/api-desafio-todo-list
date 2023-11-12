package com.masprogtechs.desafiotodolist.repository;

import com.masprogtechs.desafiotodolist.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
