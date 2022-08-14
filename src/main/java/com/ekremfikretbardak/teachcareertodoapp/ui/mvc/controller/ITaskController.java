package com.ekremfikretbardak.teachcareertodoapp.ui.mvc.controller;

import com.ekremfikretbardak.teachcareertodoapp.dto.TaskRestDto;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

public interface ITaskController {

    //SpeedDataInsert
    public String speedDataInsert();

    //SAVE
    public String taskControllerSaveGetForm(Model model);
    public String taskControllerSavePostForm(TaskRestDto taskRestDto, BindingResult bindingResult);

    //LIST
    public String taskControllerList(Model model);

    //FIND
    public String taskControllerFind(Long id,  Model model);

    //DELETE
    public String taskControllerDelete(Long id,  Model model);

    //UPDATE
    public String taskControllerUpdateGetForm(Long id,  Model model);
    public String taskControllerUpdatePostForm(TaskRestDto taskRestDto, Long id,  BindingResult bindingResult);
}
