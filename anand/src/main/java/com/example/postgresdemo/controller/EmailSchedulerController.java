/**
 * 
 */
package com.example.postgresdemo.controller;

import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.postgresdemo.exception.ResourceNotFoundException;
import com.example.postgresdemo.model.EmailScheduler;
import com.example.postgresdemo.repository.EmailschedulRepository;
import com.example.postgresdemo.service.EmailSchedulerService;

/**
 * @author Cybertech1
 *
 */
@RestController
@RequestMapping("/api/email")
public class EmailSchedulerController {

	/**
	 * Logger
	 */
	static private org.slf4j.Logger log = LoggerFactory.getLogger(EmailSchedulerController.class);

	/**
	 * emailSchedulerService
	 */
	@Autowired
	private EmailSchedulerService emailSchedulerService;

	@Autowired
	private EmailschedulRepository emailschedulRepository;

	/**
	 * @param emailScheduler
	 * @return
	 */
	@PostMapping("/save")
	public EmailScheduler createEmailScheduler(final @Valid @RequestBody EmailScheduler emailSchedulerData) {
		EmailScheduler emailScheduler = new EmailScheduler();
		log.info("Start of EmailSchedulerController : createEmailScheduler : started.... :");
		try {
			if (emailSchedulerData != null) {
				emailScheduler = emailSchedulerService.saveUpdate(emailScheduler);
				log.info("EmailSchedulerController createEmailScheduler :" + emailScheduler.getEmailScheduleId());
			}
		} catch (Exception e) {
			log.error("Error in EmailSchedulerController :: saveActual" + emailScheduler + "Error message : "+ e.getMessage());
		}
		log.info("end of EmailSchedulerController :createEmailScheduler : started.... :");
		return emailScheduler;
	}

	/**
	 * @param emailScheduleId
	 * @return
	 */
	@GetMapping("/emailScheduler")
	public void sendEmailSchedular() {
		log.info("Start of sendEmailSchedular : started.... :");
		try {
			
			emailSchedulerService.findByStatus();
		} catch (ResourceNotFoundException e) {
			log.error("Error in sendingEmailSchedulerController :  EmailScheduler Error message :" + e.getMessage());

		}
		log.info("End of EmailSchedulerController : sendEmailSchedular : emailScheduler sent successfully... ");

	}

}
