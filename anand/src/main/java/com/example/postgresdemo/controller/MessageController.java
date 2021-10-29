/**
 * 
 */
package com.example.postgresdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Cybertech1
 *
 */
@RestController
public class MessageController {
	
	 @Autowired
	  MessageSource messageSource;
	 
	  @RequestMapping(value = "/get-greeting", method = RequestMethod.GET)
	  public String greeting() {
	    /**
	     *   @LocaleContextHolder.getLocale()
	     *  Return the Locale associated with the given user context,if any, or the system default Locale otherwise.
	     *  This is effectively a replacement for Locale.getDefault(), able to optionally respect a user-level Locale setting.
	     */
		return messageSource.getMessage("good.morning", null, LocaleContextHolder.getLocale());
	  }

}
