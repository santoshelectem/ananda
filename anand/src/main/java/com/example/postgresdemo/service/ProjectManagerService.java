/**
 * 
 */
package com.example.postgresdemo.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.postgresdemo.exception.ResourceNotFoundException;
import com.example.postgresdemo.model.Developer;
import com.example.postgresdemo.model.Product;
import com.example.postgresdemo.model.ProjectManager;
import com.example.postgresdemo.repository.DeveloperRepository;
import com.example.postgresdemo.repository.ProductRepository;
import com.example.postgresdemo.repository.ProjectManagerRepository;

/**
 * @author Cybertech1
 *
 */
@Service
public class ProjectManagerService {
	/**
	 * ProjectManagerRepository
	 */
	@Autowired
	private ProjectManagerRepository projectManagerRepository;
	
	/**
	 * developerRepository
	 */
	@Autowired
	private DeveloperRepository developerRepository;
	
	/**
	 * @param projectManager
	 * @return
	 */
	public ProjectManager saveUpdate(final @Valid ProjectManager projectManager) {
		// TODO Auto-generated method stub
		return projectManagerRepository.save(projectManager);
	}

	/**
	 * @param pid
	 * @return
	 */
	public ProjectManager fetchById(final Integer pid) {
		// TODO Auto-generated method stub
		return projectManagerRepository.findById(pid)
				.orElseThrow(() -> new ResourceNotFoundException("ProjectManager"));
	}
	
	/**
	 * @param pid
	 * @param managerDetails
	 * @return
	 */
	public ResponseEntity<ProjectManager> updateManager(@PathVariable final Integer pid, @RequestBody final ProjectManager managerDetails) {
		ProjectManager updateManager = projectManagerRepository.findById(pid)
				.orElseThrow(() -> new ResourceNotFoundException("product not exist with id: " + pid));

		updateManager.setName(managerDetails.getName());
		updateManager.setEnailId(managerDetails.getEnailId());
		updateManager.setCountry(managerDetails.getCountry());
		updateManager.setProjects(managerDetails.getProjects());

		projectManagerRepository.save(updateManager);

		return ResponseEntity.ok(updateManager);
	}

	/**
	 * @return
	 */
	//finding the highest task completed 
	public ProjectManager findProjectmanager() {
		
		return projectManagerRepository.findProjectManager();
	}

	
	/**
	 * @return developer having a lest task
	 */
	public Developer developerLesTask() {
		// TODO Auto-generated method stub
		final Developer developer = developerRepository.findDeveloperLesCount();
		return developer;
	}
}
