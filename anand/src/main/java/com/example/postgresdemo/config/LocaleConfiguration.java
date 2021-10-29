/**
 * 
 */
package com.example.postgresdemo.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * @author Cybertech1
 *
 */
@Configuration
public class LocaleConfiguration implements WebMvcConfigurer {
	
	/**
	   * * @return default Locale set by the user
	   */
	  @Bean(name = "localeResolver")
	  public LocaleResolver localeResolver() {
	    SessionLocaleResolver slr = new SessionLocaleResolver();
	    slr.setDefaultLocale(Locale.US);
	    return slr;
	  }
	  @Override
	  public void addInterceptors(InterceptorRegistry registry) {
	    LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
	    localeChangeInterceptor.setParamName("language");
	    registry.addInterceptor(localeChangeInterceptor);
	  }

}
