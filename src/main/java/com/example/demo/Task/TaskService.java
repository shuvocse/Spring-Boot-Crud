package com.example.demo.Task;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
	@Autowired
	private TaskRepository taskRepository;
	
	public List<TaskEntity> getAllTask(){
		try{
			return taskRepository.findAll();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<TaskEntity>();
	}
	
	
	public void saveTask(TaskEntity taskEntity){
		try{
			taskRepository.save(taskEntity);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public TaskEntity getTaskById(int id){
		try{
			return taskRepository.getOne(id);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new TaskEntity();
	}
	
	
	public void deleteTask(int id){
		try{
			taskRepository.delete(id);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
