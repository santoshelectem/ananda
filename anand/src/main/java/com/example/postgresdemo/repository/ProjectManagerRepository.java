/**
 * 
 */
package com.example.postgresdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.postgresdemo.model.ProjectManager;

/**
 * @author Cybertech1
 *
 */
@Repository
public interface ProjectManagerRepository extends JpaRepository<ProjectManager, Integer> {

	@Query(value = "SELECT p1.* FROM project_manager p1 INNER JOIN  project_manager_project pmp on p1.pid = pmp.project_manager_fk"
			+ " INNER JOIN project p on p.project_id = pmp.project_fk"
			+ " INNER JOIN task t on t.project_id=p.project_id"
			+ " INNER JOIN developer d on d.developer_id=t.developer_id"
			+ " INNER JOIN status s on s.status_id=d.status_id"
			+ " ORDER BY s.completed_count LIMIT 1", nativeQuery = true)
	ProjectManager findProjectManager();

	

}
