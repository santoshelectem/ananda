/**
 * 
 */
package com.example.postgresdemo.controller;

import java.util.List;


import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.postgresdemo.exception.ResourceNotFoundException;
import com.example.postgresdemo.model.Developer;
import com.example.postgresdemo.model.Project;
import com.example.postgresdemo.model.ProjectManager;
import com.example.postgresdemo.model.Task;
import com.example.postgresdemo.service.ProjectManagerService;

/**
 * @author Cybertech1
 *
 */
@RestController
@RequestMapping("/rest/api")
public class ProjectManagerController {
	/**
	 * Logger
	 */
	//static private Logger log = LoggerFactory.getLogger(ProjectManagerController.class);
	
	private static final Logger logger = LogManager.getLogger(ProjectManagerController.class);
	/**
	 * dependancy injection
	 */
	@Autowired
	private ProjectManagerService projectManagerService;

	/**
	 * @param projectManager
	 * @return
	 */
	@PostMapping("/projectManager")
	public ProjectManager createProduct(final @Valid @RequestBody ProjectManager projectManager) {
		logger.info("Start of ProjectManagerController : createProduct .... :");
		
		try {
			if(projectManager!=null){
				final List<Project> projects = projectManager.getProjects();
				String DeveleperName;
				for (final Project project : projects) {
					final List<Task> tasks = project.getTasks();
					for (final Task task : tasks) {
						DeveleperName = task.getDeveloper().getName();
						// System.out.println(DeveleperName);
					}
				}
			}
			
		} catch (Exception e) {
			logger.error("error of ProjectManagerController : getProductById .... :" + e.getMessage());
		}
		logger.info("end of ProjectManagerController : createProduct .... :");
		return projectManagerService.saveUpdate(projectManager);
	}

	/**
	 * @param pid
	 * @return
	 */
	@GetMapping("/projectManager/{pid}")
	public ProjectManager getProductById(final @PathVariable(value = "pid") Integer pid) {
		logger.info("start of ProjectManagerController : getProductById .... :");
		try {
			if (pid != null) {
				return projectManagerService.fetchById(pid);
			}
		} catch (Exception exception) {
			logger.error("error of ProjectManagerController : getProductById .... :" + exception.getMessage());
			throw new ResourceNotFoundException("id not match");
		}
		logger.info("end of ProjectManagerController : getProductById .... :");
		return null;
	}

	/**
	 * @param projectmanagerId
	 * @param projectmanagerDetails
	 * @return
	 */
	@PutMapping("/projectManagers/{pid}")
	public ResponseEntity<ProjectManager> updateProjectManager(
			final @PathVariable(value = "pid") Integer projectManagerId,
			final @Valid @RequestBody ProjectManager projectmanagerDetails) {
		ResponseEntity<ProjectManager> updateManager = null;
		logger.info("start of ProjectManagerController : updateProjectManager .... :");
		try {
			if (projectManagerId != null) {
				updateManager = projectManagerService.updateManager(projectManagerId, projectmanagerDetails);
			}

		} catch (Exception exception) {
			// TODO: handle exception
			logger.error("error of ProjectManagerController : updateProjectManager .... :" + exception.getMessage());
			// throw new ResourceNotFoundException("id not match");
		}
		logger.info("end of ProjectManagerController : updateProjectManager .... :");
		return updateManager;

	}

	/**
	 * @param pid
	 * @return
	 */

	/**
	 * getProjectManager
	 * 
	 * @return
	 */
	// project manager highest task completed
	@GetMapping("/projectManagerHihest")
	public ProjectManager getProjectManager() {
		logger.info("start of ProjectManagerController : getProjectManager .... :");
		ProjectManager findProjectmanager;
		try {
			findProjectmanager = projectManagerService.findProjectmanager();
		} catch (Exception exception) {
			logger.error("error of ProjectManagerController : getProjectManager .... :" + exception.getMessage());
			throw new ResourceNotFoundException("id not match");
		}
		logger.info("end of ProjectManagerController : getProjectManager .... :");
		return findProjectmanager;
	}

	/**
	 * getlesNumbetask
	 * 
	 * @return
	 */
	@GetMapping("/DeveleperLesTask")
	public Developer getlesNumbeTask() {
		logger.info("start of ProjectManagerController : getlesNumbetask .... :");
		Developer developerLesTask = null;
		try {
			if (developerLesTask != null) {
				developerLesTask = projectManagerService.developerLesTask();
			}
		} catch (Exception exception) {
			logger.error("error of ProjectManagerController : getlesNumbetask .... :" + exception.getMessage());
			exception.printStackTrace();
		}
		logger.info("end of ProjectManagerController : getlesNumbetask .... :");
		return developerLesTask;
	}

}
