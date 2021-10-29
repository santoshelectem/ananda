/**
 * 
 */
package com.example.postgresdemo.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBException;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.postgresdemo.model.ProjectManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Cybertech1
 *
 */
@RestController
@RequestMapping("/http")
public class HttpClientControlller {
	private Logger log = LoggerFactory.getLogger(HttpClientControlller.class);

	/**
	 * @return
	 * @throws IOException
	 * @throws JAXBException
	 * @throws org.json.simple.parser.ParseException
	 * 
	 */
	
	@GetMapping("/get/client")
	public ProjectManager httpGet() throws IOException, JAXBException, org.json.simple.parser.ParseException {
		log.info("Start of HttpClientControlller :: httpGet() ");

		String fileName = null;
		// creating object of creatteDefault
		CloseableHttpClient client = HttpClients.createDefault();
		String result = null;
		ProjectManager projectManager = null;

		try {
			HttpGet httpget = new HttpGet("http://192.168.1.6:8081/rest/api/projectManager/1");

			httpget.setHeader("Accept", "application/json, application/json");
			httpget.setHeader("Content-Type", "application/json;charset=UTF-8");
			httpget.setHeader("Authorization", "Basic YWRtaW46bmltZGE=");

			// get HttpResponseProxy
			CloseableHttpResponse response = client.execute(httpget);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				// return it as a String
				result = EntityUtils.toString(entity);
			}
			JSONParser parser = new JSONParser();
			// JSONObject json = (JSONObject) parser.parse(result);

			projectManager = new ObjectMapper().readValue(result, ProjectManager.class);

			System.out.println(projectManager);

		} catch (ParseException e) {
			log.error("HttpClientControlller :: httpGet " + e.getMessage());

		} catch (IOException e) {
			log.error("HttpClientControlller :: httpGet " + e.getMessage());
		} finally {
			client.close();
		}

		log.info("end of HttpClientControlller :: httpGet()");
		return projectManager;

	}

	/**
	 * @param url
	 * @return
	 * @throws IOException
	 */

	@PostMapping("/client/post")
	private String sendPOST() throws IOException {
		log.info("Start of HttpClientControlller :: sendPOST() ");
		String url = "http://192.168.1.4:8081/rest/api/projectManager";
		String result = "";
		HttpPost post = new HttpPost(url);
		post.setHeader("Authorization", "Basic YWRtaW46bmltZGE=");
		post.setHeader("Accept", "application/json, application/json");
		post.setHeader("Content-Type", "application/json;charset=UTF-8");
		ProjectManager projectManager = new ProjectManager();
		projectManager.setEnailId("anil");
		// Creating Object of ObjectMapper define in Jackson API
		ObjectMapper objectMapper = new ObjectMapper();
		String json = null;
			json = objectMapper.writeValueAsString(projectManager);
			post.setEntity(new StringEntity(json.toString()));
		try (CloseableHttpClient httpClient = HttpClients.createDefault();
				CloseableHttpResponse response = httpClient.execute(post)) {
			result = EntityUtils.toString(response.getEntity());
		} 


		return result;
	}

}
