/**
 * 
 */
package com.example.postgresdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.postgresdemo.model.MeasurandPanel;

/**
 * @author Cybertech1
 *
 */
@Repository
public interface MeasuandPanelRepository extends JpaRepository<MeasurandPanel, Integer>{

	
	@Query(value = "select m.metric_id,mp.panel_name from metric m INNER JOIN measurand_panel mp on m.metric_id=mp.panel_id where mp.panel_name=''", nativeQuery = true)
	public boolean search(String panelName);
}
