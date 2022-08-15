package com.ekremfikretbardak.teachcareertodoapp.ui.mvc.controller.impl;

import com.ekremfikretbardak.teachcareertodoapp.dto.TaskDto;
import com.ekremfikretbardak.teachcareertodoapp.dto.TaskRestDto;
import com.ekremfikretbardak.teachcareertodoapp.ui.mvc.controller.ITaskController;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

@Controller
@Log4j2
public class TaskControllerImp implements ITaskController {

    //Speed Data Insert
    //http://localhost:8080/task/speedSave
    @Override
    @ResponseBody
    @GetMapping("task/speedSave")
    public String speedDataInsert() {
        String URL = "http://localhost:8080/api/v1/tasks";
        RestTemplate restTemplate= new RestTemplate();
        int i = 0;
        for (i = 1; i <= 10 ; i++) {
            TaskRestDto taskRestDto = TaskRestDto.builder().taskID(0L).definition("To Do:" + i).build();
            restTemplate.postForObject(URL, taskRestDto, TaskRestDto.class);
        }
        return i+" tane veri eklendi";
    }

    //SAVE GET
    //http://localhost:8080/task/form
    @GetMapping("task/form")
    @Override
    public String taskControllerSaveGetForm(Model model) {
        model.addAttribute("task_save", new TaskRestDto());
        return "task_save";
    }

    //SAVE POST
    //http://localhost:8080/task/form
    @PostMapping("task/form")
    @Override
    public String taskControllerSavePostForm(@Valid @ModelAttribute("task_save") TaskRestDto taskRestDto, BindingResult bindingResult) {
        RestTemplate restTemplate= new RestTemplate();
        String URL = "http://localhost:8080/api/v1/tasks";

        if(bindingResult.hasErrors()){
            return "task_save";
        }
        restTemplate.postForObject(URL, taskRestDto, TaskRestDto.class);
        return "redirect:/task/list";
    }

    //LIST
    //http://localhost:8080/task/list
    @GetMapping("task/list")
    @Override
    public String taskControllerList(Model model) {
        RestTemplate restTemplate= new RestTemplate();
        String URL = "http://localhost:8080/api/v1/tasks";
        ResponseEntity<List<TaskRestDto>> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<List<TaskRestDto>>() {
        });
        List<TaskRestDto> dtoList = responseEntity.getBody();
        model.addAttribute("task_list", dtoList);
        return "task_list";
    }

    //FIND
    //http://localhost:8080/find/task/4
    @GetMapping("find/task/{id}")
    @Override
    public String taskControllerFind(@PathVariable(name = "id") Long id, Model model) {
        RestTemplate restTemplate= new RestTemplate();
        String URL = "http://localhost:8080/api/v1/tasks"+id;
        ResponseEntity<TaskRestDto> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, HttpEntity.EMPTY, TaskRestDto.class);
        model.addAttribute("task_find", responseEntity.getBody());
        return "task_detail_pages";
    }

    //DELETE
    //http://localhost:8080/delete/task/4
    @GetMapping("delete/task/{id}")
    @Override
    public String taskControllerDelete(@PathVariable(name = "id") Long id, Model model) {
        //"http://localhost:8080/api/v1/tasks"  /1
        RestTemplate restTemplate= new RestTemplate();
        String URL = "http://localhost:8080/api/v1/tasks/"+id;
        ResponseEntity<TaskRestDto> responseEntity = restTemplate.exchange(URL, HttpMethod.DELETE, HttpEntity.EMPTY, TaskRestDto.class);
        model.addAttribute("task_delete", responseEntity.getBody());
        return "redirect:/task/list";
    }

    //UPDATE GET
    @GetMapping("update/task/{id}")
    @Override
    public String taskControllerUpdateGetForm(@PathVariable(name = "id") Long id, Model model) {
        RestTemplate restTemplate= new RestTemplate();
        String URL = "http://localhost:8080/api/v1/tasks/"+id;
        ResponseEntity<TaskRestDto> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, HttpEntity.EMPTY, TaskRestDto.class);
        model.addAttribute("task_update", responseEntity.getBody());
        return "task_update";
    }

    //UPDATE POST
    @PostMapping("update/task/{id}")
    @Override
    public String taskControllerUpdatePostForm(@Valid @ModelAttribute("task_update")  TaskRestDto taskRestDto, @PathVariable(name = "id")  Long id, BindingResult bindingResult) {
        RestTemplate restTemplate= new RestTemplate();
        String URL = "http://localhost:8080/api/v1/tasks/"+id;

        if(bindingResult.hasErrors()){
            return "task_update";
        }
        restTemplate.put(URL, taskRestDto, TaskRestDto.class);
        return "redirect:/task/list";
    }
}
