package br.com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.demo.model.TaskModel;

public interface TaskRepository extends JpaRepository<TaskModel, Long>{
    
}
