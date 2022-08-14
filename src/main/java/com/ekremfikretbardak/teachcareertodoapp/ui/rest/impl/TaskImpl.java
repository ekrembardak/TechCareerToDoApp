package com.ekremfikretbardak.teachcareertodoapp.ui.rest.impl;

import com.ekremfikretbardak.teachcareertodoapp.dto.TaskDto;
import com.ekremfikretbardak.teachcareertodoapp.services.ITaskServices;
import com.ekremfikretbardak.teachcareertodoapp.ui.rest.ITaskRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class TaskImpl implements ITaskRest {

    @Autowired
    ITaskServices services;

    //http://localhost:8080/api/v1
    //http://localhost:8080/api/v1/index
    @GetMapping({"/","/index"})
    public String getRoot(){
        return "index";
    }


    //http://localhost:8080/api/v1/tasks
    @Override
    @PostMapping("/tasks")
    public TaskDto createTask(@RequestBody TaskDto taskDto) {
        services.createTask(taskDto);
        return taskDto;
    }

    //http://localhost:8080/api/v1/tasks
    @Override
    @GetMapping("/tasks")
    public List<TaskDto> getAllTasks() {
        List<TaskDto> list = services.getAllTasks();
        return list;
    }

    //http://localhost:8080/api/v1/tasks/1
    @Override
    @GetMapping("/tasks/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable(name = "id")  Long id) {
        ResponseEntity<TaskDto> dto =  services.getTaskById(id);
        return dto;
    }

    //http://localhost:8080/api/v1/tasks/1
    @Override
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteTask(@PathVariable(name = "id")  Long id) {
        services.deleteTaskById(id);
        Map<String,Boolean> response = new HashMap<>();
        response.put("silindi", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    //http://localhost:8080/api/v1/tasks/1
    @Override
    @PutMapping("/tasks/{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable(name = "id")   Long id, @RequestBody TaskDto taskDto) {
        services.updateTask(id,taskDto);
        return ResponseEntity.ok(taskDto);
    }
}
