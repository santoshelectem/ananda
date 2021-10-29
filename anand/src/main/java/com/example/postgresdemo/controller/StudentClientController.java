/**
 * 
 */
package com.example.postgresdemo.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.postgresdemo.model.Project;
import com.example.postgresdemo.model.ProjectManager;
import com.example.postgresdemo.model.Student;
import com.example.postgresdemo.model.Subject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.qos.logback.core.util.FileUtil;

/**
 * @author Cybertech1
 *
 */
@RestController
@RequestMapping("/api")
public class StudentClientController {
	private Logger log = LoggerFactory.getLogger(StudentClientController.class);
	String result = null;
	/*@GetMapping("/get/client")
	public String httpGet() throws ClientProtocolException, IOException
	{
		log.info("Start of StudentClientController :: httpGet()");
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet httpget = new HttpGet("http://192.168.1.6:8081/student/get/all");
		httpget.setHeader("Accept", "application/json, application/json");
		httpget.setHeader("Content-Type", "application/json;charset=UTF-8");
		httpget.setHeader("Authorization", "Basic YWRtaW46bmltZGE=");
		CloseableHttpResponse response = client.execute(httpget);
		fromFileRead();
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			// return it as a String
			result = EntityUtils.toString(entity);
		}
		log.info("end of StudentClientController :: httpGet()");
		return result;
		
	}*/
	
	
	
	@GetMapping("/get/client")
	public ProjectManager fromFileRead() throws IOException
	{
		String file="E:/anand/json/student.txt";
		
		String readFileToString = FileUtils.readFileToString(new File(file));
		ProjectManager projectManager = new ObjectMapper().readValue(readFileToString, ProjectManager.class);
		String name = projectManager.getName();
		String enailId = projectManager.getEnailId();
		Integer pid = projectManager.getPid();
		List<Project> projects = projectManager.getProjects();
		objectToJson(projectManager);
		return projectManager;
		
	}
	@PostMapping("/post/client")
	public String objectToJson(ProjectManager projectManager) throws ClientProtocolException, IOException
	{
		String url = "http://192.168.1.4:8081/rest/api/projectManager";
		HttpPost post = new HttpPost(url);
		ObjectMapper objectMapper = new ObjectMapper();
		projectManager.setCountry("india");
		projectManager.setName("sunil");
		projectManager.setEnailId("adghxd@gmail.com");
		String json = objectMapper.writeValueAsString(projectManager);
		post.setEntity(new StringEntity(json.toString()));
		try (CloseableHttpClient httpClient = HttpClients.createDefault();
				CloseableHttpResponse response = httpClient.execute(post)) {
			result = EntityUtils.toString(response.getEntity());
		} 
		return json;
	}

}
