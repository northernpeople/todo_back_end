package com.stepan.angular_back_end.web.rest;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.stepan.angular_back_end.todo.Todo;
import com.stepan.angular_back_end.todo.TodoService;

@RestController
@RequestMapping("/todo")
public class TodoController {
	
	
	@Autowired
	TodoService service;
	
	
	@GetMapping("/")
	private List<Todo> summary(
			@RequestParam(value="index",   defaultValue="0") int index,
		    @RequestParam(value="size", defaultValue="500") int size){
				return service.page(index, size);
	}
	
	@GetMapping("/generate")
	public void generate(){
		service.testData();
	}
	
	@PostMapping(value="/", consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Todo save(@RequestBody Todo dto, Principal p) {
		return service.create(dto);
	}
	
	@GetMapping("/{id}")
	public Todo byId(@PathVariable("id") String id, Principal p){
		return service.byId(id); 
	}
	
}