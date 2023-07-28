package br.com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.demo.model.TaskModel;
import br.com.demo.service.TaskService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("task")
public class TaskController {
    
    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<List<TaskModel>> create(@Valid @RequestBody TaskModel task){
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.create(task));
    }

    @GetMapping
    public List<TaskModel> list(){
        return taskService.list();
    }

    @PutMapping("{id}")
    public List<TaskModel> update(@PathVariable Long id, @RequestBody TaskModel task){
        return taskService.update(id, task);
    }

    @DeleteMapping("{id}")
    public List<TaskModel> delete(@PathVariable Long id){
        return taskService.delete(id);
    }
}
