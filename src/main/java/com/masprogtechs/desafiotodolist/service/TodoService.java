package com.masprogtechs.desafiotodolist.service;

import com.masprogtechs.desafiotodolist.entity.Todo;
import com.masprogtechs.desafiotodolist.exception.BadRequestException;
import com.masprogtechs.desafiotodolist.repository.TodoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository){
        this.todoRepository = todoRepository;
    }

    public List<Todo> list(){
        Sort sort = Sort.by(Sort.Direction.DESC, "priority")
                .and(Sort.by(Sort.Direction.ASC, "id"));

        return todoRepository.findAll(sort);
    }
    public List<Todo> create(Todo todo){
        todoRepository.save(todo);
        return list();
    }

    public List<Todo> update(Long id, Todo todo){
        todoRepository.findById(id).ifPresentOrElse((existingTodo) -> {
            todo.setId(id);
            todoRepository.save(todo);
        }, () -> {
            throw new BadRequestException("Todo %d não existe! ".formatted(id));
        });

        return list();
    }

    public List<Todo> delete(Long id){
        todoRepository.findById(id).ifPresentOrElse(todoRepository::delete, () ->{
            throw new BadRequestException("Todo %d não existe! ".formatted(id));
        } );

        return list();
    }

}
