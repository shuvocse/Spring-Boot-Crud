package com.example.demo.Task;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TaskController {
	@Autowired
	private TaskService taskService;
	
	//Show All Task List
	@GetMapping(value = "/")
	public String taskList(Model model) {
		model.addAttribute("taskList", taskService.getAllTask());
		return "/task_list";
	}
	
	//Create Task
	@GetMapping(value = "/task/create")
	public String createTask(Model model) {
		model.addAttribute("taskEntity", new TaskEntity());
		model.addAttribute("title", "Create Task");
		model.addAttribute("url", "create");
		return "/add_task";
	}
	
	@PostMapping(value = "/task/create")
	public String createTask(Model model,@ModelAttribute TaskEntity taskEntity) {
		if(taskEntity.getId()==null){
			taskEntity.setDateCreated(new Date());
		}else{
			TaskEntity taskEntityBeforeUpdate = taskService.getTaskById(taskEntity.getId());
			taskEntity.setDateCreated(taskEntityBeforeUpdate.getDateCreated());
			taskEntity.setDateUpdated(new Date());
		}
			taskService.saveTask(taskEntity);
			return "redirect:/";
	}

	
	//Update Task
	@GetMapping(value = "/task/update/{id}")
	public String updateTask(Model model, @PathVariable int id) {
		model.addAttribute("taskEntity", taskService.getTaskById(id));
		model.addAttribute("title", "Update Task");
		model.addAttribute("url", "update");
		return "/update_task";
	}
	
	@PostMapping(value = "/task/update")
	public String updateTask(Model model, @ModelAttribute TaskEntity taskEntity) {
		if(taskEntity.getId()==null){
			taskEntity.setDateCreated(new Date());
		}else{
			TaskEntity taskEntityBeforeUpdate = taskService.getTaskById(taskEntity.getId());
			taskEntity.setDateCreated(taskEntityBeforeUpdate.getDateCreated());
			taskEntity.setDateUpdated(new Date());
		}
			taskService.saveTask(taskEntity);
			return "redirect:/";
	}


	//Delete Task
	@GetMapping(value = "/task/delete/{id}")
	public String deleteTask(@PathVariable int id, Model model) {
		taskService.deleteTask(id);
		return "redirect:/";
	}


}
