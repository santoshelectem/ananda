/**
 * 
 */
package com.example.postgresdemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.postgresdemo.model.Developer;

/**
 * @author Cybertech1
 *
 */
@Repository
public interface DeveloperRepository  extends JpaRepository<Developer, Integer> {
	
	@Query(value = "SELECT d.* from developer d INNER JOIN  task t on t.developer_id = d.developer_id INNER JOIN status s on s.status_id = d.status_id order by s.to_do_count desc limit 1", nativeQuery = true)
	Developer findDeveloperLesCount();
	
	/*@Query(value = "SELECT d FROM Task t join t.developer d WHERE t.developer.developerId = d.developerId AND t.status='todo' AND t.developer.developerId= :developerId")
	@Query(value =
	List<Developer> findTodoStatus(@Param(value = "developerId") Integer developerId);*/
	


}
