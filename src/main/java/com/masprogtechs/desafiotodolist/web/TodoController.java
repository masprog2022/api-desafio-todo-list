package com.masprogtechs.desafiotodolist.web;

import com.masprogtechs.desafiotodolist.entity.Todo;
import com.masprogtechs.desafiotodolist.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
@Tag(name = "Todo", description = "Endpoints para gerenciar Todo" )
public class TodoController {
     @Autowired
     private TodoService todoService;

    @Operation(summary = "Registar uma nova tarefa", description = "Registar uma nova tarefa",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Tarefa criado com sucesso",
                            content = @Content(mediaType = "application/json"))
    })
     @PostMapping
     public ResponseEntity<List<Todo>> create(@Valid @RequestBody Todo todo){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(todoService.create(todo));
     }
    @Operation(summary = "Listar todas tarefas", description = "Listar todas tarefas",

            responses = {
                    @ApiResponse(responseCode = "200", description = "Todas tarefas localizados com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Todo.class)))
            })
     @GetMapping
     public List<Todo> list(){
         return todoService.list();
     }
    @Operation(summary = "Actualizar uma tarefa", description = "Actualizar uma tarefa",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Tarefa actualizada com sucesso",
                            content = @Content(mediaType = "application/json"))
            })
     @PutMapping("/{id}")
     public List<Todo> update(@PathVariable Long id, @RequestBody Todo todo){
         return todoService.update(id, todo);
     }
    @Operation(summary = "Deletar uma tarefa", description = "Deletar uma tarefa",

            responses = {
                    @ApiResponse(responseCode = "204", description = "Tarefa apagada com sucesso",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "404", description = "tarefa não encontrada",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Todo.class))),
            })
     @DeleteMapping("/{id}")
     public List<Todo> delete(@PathVariable Long id){
         return todoService.delete(id);
     }
}
