package com.ekremfikretbardak.teachcareertodoapp.services;

import com.ekremfikretbardak.teachcareertodoapp.data.entity.TaskEntity;
import com.ekremfikretbardak.teachcareertodoapp.dto.TaskDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ITaskServices {

    //ModelMapper
    public TaskDto entityToDto(TaskEntity taskEntity);
    public TaskEntity dtoToEntity(TaskDto taskDto);

    //save
    public TaskDto createTask(TaskDto taskDto);

    //list
    public List<TaskDto> getAllTasks();

    //find
    public ResponseEntity<TaskDto> getTaskById(Long id);

    //update
    public ResponseEntity<TaskDto> updateTask(Long id,TaskDto taskDto);

    //delete
    public ResponseEntity<Map<String,Boolean>> deleteTaskById(Long id);

}
