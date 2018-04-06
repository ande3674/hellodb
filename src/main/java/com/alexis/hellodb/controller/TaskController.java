package com.alexis.hellodb.controller;

import com.alexis.hellodb.model.Task;
import com.alexis.hellodb.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Created by ce6915gp on 4/5/2018.
 */
@Controller
public class TaskController {

    /* The TaskRepository is an object that will interact with the
    Task database table, so save new Tasks, query for Tasks, etc...
     */
    private final TaskRepository tasks;

    @Autowired
    public TaskController(TaskRepository tasks){
        this.tasks = tasks;
    }

    /* Handles requests to the home page. Displays a form that's bound to a new
    * Task object. In the future, when the form is submitted from the browser,
    * (submitting the form creates a POST request to the /addTask route, below)
    * the Task object will be updated to include the data entered in the form, and this
    * Task object will be sent to the server as part of the request. */
    @RequestMapping("/")
    public ModelAndView addTask() {
        return new ModelAndView("createTask.html", "task", new Task());
    }

    /*Handles requests to the /allTasks page. The modelMap obj will be populated with
    * a list of Task objects that we'll get by asking the tasks repository to query
    * the database. Returns a View (the ThymeLeaf template) which will be combined with
    * the modelMap data to produce the HTML page. */
    @RequestMapping(value="/addTask", method=RequestMethod.POST)
    public RedirectView addNewTask(Task task){
        tasks.save(task);
        return new RedirectView(("/allTasks"));
    }

    /*Handles requests tot he /allTasks page. The modelMap object will be populated with
    * a lost of Task objects that we'll get by asking the tasks repository to query
    * the database. Returns a View (the Thymeleaf template) which will be combined with the
    * modelMap data to produce the HTML page.*/
    @RequestMapping("/allTasks")
    public ModelAndView allTasks(ModelMap modelMap){
        modelMap.addAttribute("tasks", tasks.findAllByOrderByUrgentDesc());
        return new ModelAndView("taskList.html", modelMap);
    }
}
