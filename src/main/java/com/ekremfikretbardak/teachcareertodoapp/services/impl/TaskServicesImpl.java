package com.ekremfikretbardak.teachcareertodoapp.services.impl;

import com.ekremfikretbardak.teachcareertodoapp.data.entity.TaskEntity;
import com.ekremfikretbardak.teachcareertodoapp.data.repository.ITaskRepository;
import com.ekremfikretbardak.teachcareertodoapp.dto.TaskDto;
import com.ekremfikretbardak.teachcareertodoapp.exception.ResourceNotFoundException;
import com.ekremfikretbardak.teachcareertodoapp.services.ITaskServices;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Log4j2

public class TaskServicesImpl implements ITaskServices {

    @Autowired
    ITaskRepository repository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public TaskDto entityToDto(TaskEntity taskEntity) {
        TaskDto dto = modelMapper.map(taskEntity, TaskDto.class);
        return dto;
    }

    @Override
    public TaskEntity dtoToEntity(TaskDto taskDto) {
      TaskEntity taskEntity = modelMapper.map(taskDto, TaskEntity.class);
        return taskEntity;
    }

    @Override
    @PostMapping("/save/tasks")
    public TaskDto createTask(TaskDto taskDto) {
        TaskEntity entity = dtoToEntity(taskDto);
        //entity.setStatus("undone");
        repository.save(entity);
        return taskDto;
    }

    @Override
    @GetMapping("/list/tasks")
    public List<TaskDto> getAllTasks() {
        List<TaskEntity> listem = repository.findAll();

        List<TaskDto> dtoList = new ArrayList<>();
        for(TaskEntity entity :listem) {
            TaskDto dto =  entityToDto(entity);
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    @GetMapping({"/find/tasks", "/find/tasks/{id}"})
    public ResponseEntity<TaskDto> getTaskById(@PathVariable(name="id", required = false) Long id) {
        TaskEntity entity =  repository.findById(id).orElseThrow(()->new ResourceNotFoundException(id+"id yoktur"))  ;
        TaskDto dto = entityToDto(entity);
        return ResponseEntity.ok(dto);
    }

    @Override
    @PutMapping({"/update/tasks", "/update/tasks/{id}"})
    public ResponseEntity<TaskDto> updateTask(@PathVariable(name="id", required = false) Long id, TaskDto taskDto) {
        TaskEntity entityFind =  repository.findById(id).orElseThrow(()->new ResourceNotFoundException(id+"id yoktur")) ;
        TaskEntity taskEntity = dtoToEntity(taskDto);

        entityFind.setDefinition(taskEntity.getDefinition());
        entityFind.setStatus(taskEntity.getStatus());
        TaskEntity saveEntity = repository.save(entityFind);
        TaskDto dto =  entityToDto(saveEntity);
        return ResponseEntity.ok(dto);
    }

    @Override
    @DeleteMapping({"/delete/tasks", "/delete/tasks/{id}"})
    public ResponseEntity<Map<String,Boolean>> deleteTaskById(@PathVariable(name="id", required = false) Long id) {
        TaskEntity entity =  repository.findById(id).orElseThrow(()->new ResourceNotFoundException(id+"id yoktur")) ;
        //repository.delete(entity);
        repository.deleteById(id);

        Map<String,Boolean> response = new HashMap<String,Boolean>();
        response.put("Silindi",Boolean.TRUE);

        return ResponseEntity.ok(response);
    }
}
