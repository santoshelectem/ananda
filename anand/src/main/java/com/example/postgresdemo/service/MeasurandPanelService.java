/**
 * 
 */
package com.example.postgresdemo.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.postgresdemo.exception.ResourceNotFoundException;
import com.example.postgresdemo.model.MeasurandPanel;
import com.example.postgresdemo.model.ProjectManager;
import com.example.postgresdemo.repository.MeasuandPanelRepository;

/**
 * @author Cybertech1
 *
 */
@Service
public class MeasurandPanelService {

	@Autowired
	private MeasuandPanelRepository measuandPanelRepository;

	/**
	 * @param measurandPanel
	 * @return
	 */
	//creating the panel 
	public MeasurandPanel savePanel(final MeasurandPanel measurandPanel) {
		// TODO Auto-generated method stub
		return measuandPanelRepository.save(measurandPanel);
	}

	/**
	 * @param panelId
	 * @param measurandPanelUp
	 * @return
	 */
	//Updating thje panel
	public ResponseEntity<MeasurandPanel> updatePanel(final Integer panelId, @Valid final MeasurandPanel measurandPanelUp) {
		// TODO Auto-generated method stub
		MeasurandPanel measurandPanel = measuandPanelRepository.findById(panelId)
				.orElseThrow(() -> new ResourceNotFoundException("panel  not exist with id: " + panelId));

		measurandPanel.setPanelDescription(measurandPanelUp.getPanelDescription());
		measurandPanel.setMetric(measurandPanelUp.getMetric());
		measurandPanel.setPanelName(measurandPanelUp.getPanelName());

		measuandPanelRepository.save(measurandPanelUp);

		return ResponseEntity.ok(measurandPanelUp);
		
		
	}

	/**
	 * @param panelId
	 * @return
	 */
	//fetch by panel id
	public MeasurandPanel fetchByPanelId(final Integer panelId) 
	{
		// TODO Auto-generated method stub
		return measuandPanelRepository.findById(panelId)
				.orElseThrow(() -> new ResourceNotFoundException("MeasuandPanelRepository"));
	}
}
