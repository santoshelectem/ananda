/**
 * 
 */
package com.example.postgresdemo.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

/**
 * @author Cybertech1
 *
 */
@Component
public class VelocityEngine extends org.apache.velocity.app.VelocityEngine implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Logger
	 */
	static private Logger log = LoggerFactory.getLogger(VelocityEngine.class);
	/**
	 * VelocityEngine
	 */
	public VelocityEngine() {
		super();

		final Properties velocityProperties = new Properties();
		try {
			velocityProperties
					.load(new ClassPathResource("velocity/velocity.properties")
							.getInputStream());
		} catch (IOException e) {
			//throw Throwables.propagate(e);
		}
		init(velocityProperties);
	}

}
