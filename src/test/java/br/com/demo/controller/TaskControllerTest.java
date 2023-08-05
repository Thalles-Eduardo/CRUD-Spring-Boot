package br.com.demo.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.demo.model.TaskModel;
import br.com.demo.service.TaskService;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTest {
    
    @InjectMocks
    private TaskController taskController;
    
    @Mock
    private TaskService taskService;

    private TaskModel task;


    @BeforeEach
    void setup(){
        task =  new TaskModel(1L, "Aniversário", "Fazer bolo", false, 10, LocalDateTime.now());
    }

    @Test
    public void createTest(){
        // Se não tiver um corpo de retorno.
        // var request = ResponseEntity.status(HttpStatus.CREATED).body(taskService.create(task));
        // var response = assertDoesNotThrow(() -> taskController.create(task));
        // assertEquals(request, response);
        
        List<TaskModel> taskList = new ArrayList<>();
        taskList.add(task);

        when(taskService.create(task)).thenReturn(taskList);

        ResponseEntity<List<TaskModel>> response = taskController.create(task);
        
        assertEquals(HttpStatus.CREATED, response.getStatusCode());// Verifica o status HTTP
        assertEquals(taskList, response.getBody()); // Verifica o corpo retornado
    }

    @Test
    public void listTest(){
        List<TaskModel> taskList = new ArrayList<>();
        taskList.add(task);

        when(taskService.list()).thenReturn(List.of(task));

        List<TaskModel> response = taskController.list();

        assertNotNull(response);
        assertEquals(response, taskList);
    }

    @Test
    public void updateTest(){
        TaskModel updateTask = new TaskModel(1L, "Aniversário", "Fazer bolo", true, 0, LocalDateTime.now());
        List<TaskModel> newTask = taskService.update(1L, updateTask);


        assertNotNull(newTask);
        assertNotEquals(task, updateTask);
    }

    @Test
    public void deleteTest() {  
        List<TaskModel> response = taskController.delete(anyLong());

        assertEquals(0, response.size());
        assertNotEquals(task, response);
    }

    
}
