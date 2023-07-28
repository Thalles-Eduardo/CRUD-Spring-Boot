package br.com.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.demo.exception.BadRequestException;
import br.com.demo.model.TaskModel;
import br.com.demo.repository.TaskRepository;

@Service
public class TaskService {
    
    @Autowired
    private TaskRepository taskRepository;

    public List<TaskModel> create(TaskModel task){
        task.setDate(LocalDateTime.now());
        taskRepository.save(task);
        return list();
    }

    public List<TaskModel> list(){
        Sort sort = Sort.by("priority").descending();
        return taskRepository.findAll(sort);
    }

    public List<TaskModel> update(Long id, TaskModel task){
        taskRepository.findById(id).ifPresentOrElse((existingTask) -> {
            task.setId(id);
            task.setDate(LocalDateTime.now());
            taskRepository.save(task);
        }, () -> {
            throw new BadRequestException("Task %d does not exist! ".formatted(id));
        });

        return list();
    }

    public List<TaskModel> delete(Long id){
        taskRepository.findById(id).ifPresentOrElse((existingTask) -> taskRepository.delete(existingTask),
        () -> {
            throw new BadRequestException("Task %d does not exist! ".formatted(id));
        });

        return list();
    }
}
