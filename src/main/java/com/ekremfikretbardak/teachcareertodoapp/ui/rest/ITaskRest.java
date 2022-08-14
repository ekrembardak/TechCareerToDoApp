package com.ekremfikretbardak.teachcareertodoapp.ui.rest;

import com.ekremfikretbardak.teachcareertodoapp.dto.TaskDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ITaskRest {

    //SAVE
    TaskDto createTask(TaskDto taskDto);

    //LIST
    List<TaskDto> getAllTasks();

    //FIND
    ResponseEntity<TaskDto> getTaskById(Long id);

    //DELETE
    ResponseEntity<Map<String,Boolean>> deleteTask(Long id);

    //UPDATE
    ResponseEntity<TaskDto> updateTask(Long id, TaskDto taskDto);

}
