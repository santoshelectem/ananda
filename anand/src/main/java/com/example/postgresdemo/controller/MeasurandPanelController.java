/**
 * 
 */
package com.example.postgresdemo.controller;



import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.example.postgresdemo.model.MeasurandPanel;
import com.example.postgresdemo.model.ProjectManager;
import com.example.postgresdemo.service.MeasurandPanelService;

/**
 * @author Cybertech1
 *
 */
@RestController
@RequestMapping("/api")
public class MeasurandPanelController {
	/**
	 * Logger
	 */
	static private Logger log = LoggerFactory.getLogger(MeasurandPanelController.class);

	/**
	 * measurandPanelService
	 */
	@Autowired
	private MeasurandPanelService measurandPanelService;

	/**
	 * @param measurandPanel
	 * @return
	 */
	@PostMapping("/create/panel")
	public MeasurandPanel creatPanel(final @RequestBody MeasurandPanel measurandPanel) {
		log.info("MeasurandPanelController creatPanel :");
		MeasurandPanel savePanel = null;
		try {
			if (measurandPanel != null) {
				savePanel = measurandPanelService.savePanel(measurandPanel);
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.error("MeasurandPanelController creatPanel :" + e.getMessage());
		}
		return savePanel;

	}

	/**
	 * @param panelId
	 * @param measurandPanel
	 * @return
	 */
	// creating panel
	@PutMapping("/panel/update/{id}")
	public ResponseEntity<MeasurandPanel> updatePanel(final @PathVariable(value = "id") Integer panelId,
			final @Valid @RequestBody MeasurandPanel measurandPanel) {
		ResponseEntity<MeasurandPanel> updatePanel = null;
		log.info("start MeasurandPanelController updatePanel :");
		try {
			if (panelId != null) {
				updatePanel = measurandPanelService.updatePanel(panelId, measurandPanel);
			}

		} catch (Exception exception) {
			// TODO: handle exception
			log.error("error MeasurandPanelController updatePanel :" + exception.getMessage());
			throw new ResourceNotFoundException("id not match");
		}
		log.info("end MeasurandPanelController updatePanel :");
		return updatePanel;

	}

	/**
	 * @param panelId
	 * @return creating new panel
	 */
	@GetMapping("/panel/new/created/{id}")
	public MeasurandPanel getById(final @PathVariable(value = "id") Integer panelId) {
		log.info("start MeasurandPanelController updatePanel :");
		MeasurandPanel fetchByPanelId = null;
		try {
			if (panelId != null) {
				fetchByPanelId = measurandPanelService.fetchByPanelId(panelId);
				return fetchByPanelId;
			}
		} catch (Exception exception) {
			log.error("error MeasurandPanelController getById :" + exception.getMessage());
		}
		log.info("end MeasurandPanelController getById :");
		return fetchByPanelId;
	}

}
