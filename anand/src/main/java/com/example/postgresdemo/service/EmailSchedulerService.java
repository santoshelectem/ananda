/**
 * 
 */
package com.example.postgresdemo.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.Valid;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.velocity.app.Velocity;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.postgresdemo.model.EmailScheduler;
import com.example.postgresdemo.model.Report;
import com.example.postgresdemo.model.User;
import com.example.postgresdemo.repository.EmailschedulRepository;
import com.example.postgresdemo.repository.UserRepository;

/**
 * @author Cybertech1
 *
 */
@Service
public class EmailSchedulerService implements Serializable {

	/**
	 * log
	 */
	private static Logger log = Logger.getLogger("com.canland");

	/**
	 * sendReportTemplate
	 */
	private String sendReportTemplate = "velocity/reportTemplate.vm";

	/**
	 * emailschedulRepository
	 */
	@Autowired
	private EmailschedulRepository emailschedulRepository;

	/**
	 * entityManager
	 */
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * userRepository
	 */
	@Autowired
	private UserRepository userRepository;

	/**
	 * htmlTemplateService
	 */
	@Autowired
	private HtmlTemplateService htmlTemplateService;

	/**
	 * @param integer
	 * @return report list
	 */
	public List<Report> getReports(final Long integer) {
		List<Object[]> datas;
		final String queryStr = "SELECT r.report_id,r.created_dat,r.report_name,r.type FROM email_scheduler e "
				+ "INNER JOIN user u INNER JOIN customer c" + " INNER JOIN report r  WHERE e.user_id= u.user_id "
				+ " AND c.user_id_fk= u.user_id AND r.customer_id_fk =c.customer_id "
				+ "and u.role='develper' and u.user_id=" + integer;
		final Query query = entityManager.createNativeQuery(queryStr);
		datas = (List<Object[]>) query.getResultList();
		final List<Report> reports = convertObjectsToReport(datas);
		return reports;
	}

	/**
	 * @param datas
	 * @return
	 */
	private List<Report> convertObjectsToReport(final List<Object[]> datas) {
		final List<Report> reports = new ArrayList<>();
		final Date dateTime = new Date();
		final Report report = new Report();
		for (final Object[] obj : datas) {

			final Long value = (long) ((BigInteger) obj[0]).intValue();

			report.setReportId(value);
			report.setCreatedDat(dateTime);
			report.setReportName((String) obj[2]);
			report.setType((String) obj[3]);
			reports.add(report);
		}
		return reports;
	}

	/**
	 * @param emailScheduler
	 * @return
	 */
	public EmailScheduler saveUpdate(final @Valid EmailScheduler emailScheduler) {

		return emailschedulRepository.save(emailScheduler);
	}

	/**
	 * findByStatus
	 */
	public void findByStatus() {
		List<EmailScheduler> emailScheduler;
		List<Report> reportsAll = new ArrayList<>();
		List<Report> reports = new ArrayList<>();
		emailScheduler = emailschedulRepository.findByStatus();
		// System.out.println(emailScheduler);
		log.info("List of EmailSchedular : size :" + emailScheduler.size());
		if (emailScheduler.size() > 0) {
			for (final EmailScheduler email : emailScheduler) {
				String userEmailIds = null;
				String[] userIds = null;
				String reportUrl = null;
				// list of Report
				if (email.getFieldUser().getUserId() != null) {
					reports = getReports(email.getFieldUser().getUserId());
				}

				if (reports.size() > 0 && email.getManagerUserIds() != null) {
					if (userEmailIds != null) {
						userEmailIds = email.getManagerUserIds() + ',' + userEmailIds;
					} else {
						userEmailIds = email.getManagerUserIds();
					}
				}
				// manageUserEmails
				if (reports.size() > 0 && userEmailIds != null) {

					userIds = userEmailIds.split(",");
					final String[] withOutDuplicate = StringUtils.removeDuplicateStrings(userIds);
					String manageUserEmails = null;
					for (final String userId : withOutDuplicate) {
						if (userId != null) {
							final Integer userIdSearch = emailschedulRepository.userIdSearch(userId);
							final User user = userRepository.findByUserId(userIdSearch);
							if (manageUserEmails != null) {
								manageUserEmails = manageUserEmails + ',' + user.getEmailId();
							} else {
								manageUserEmails = user.getEmailId();
							}
						}
					}
					// extractReports
					final Map<String, Object> templateData = extractReports(reports, email.getFieldUser());
					final String message = htmlTemplateService.renderTemplate(sendReportTemplate, templateData);

					reportUrl=genarateReportPdf(message);
					htmlTemplateService.sendMailTrap(null, message, manageUserEmails, "Report test",
							"anandkukamalli@gmail.com", reportUrl);
					
					log.info("Send Email Ended ...");

				}
			}
		}

	}

	/**
	 * @param reports
	 * @param user
	 * @return userData
	 */
	private Map<String, Object> extractReports(final List<Report> reports, final User user) {
		final Map<String, Object> userData = new ConcurrentHashMap<>();
		userData.put("Report", reports);
		userData.put("User", user);
		
		return userData;
	}

	/**
	 * @param html
	 * @return
	 */
	public String genarateReportPdf(final String html) {
		final String url = "E:/anand/Reports/tempReport.pdf";
		final File pdfTempFile = new File("E:/anand/Reports", "tempReport.pdf");
		final File file = new File("E:/anand/Reports", "tempDatasheet.html");
		try {
			FileUtils.writeStringToFile(file, html);
			pdfGeneration(html, pdfTempFile);
		} catch (IOException e) {
			log.info("Error in EmailSchedular : genarateReportPdf :" + e.getMessage());
		}
		return url;
	}

	@SuppressWarnings("unused")
	private void pdfGeneration(final String html, final File localFile) {
		HttpURLConnection urlConnection = null;
		byte[] buffer = null;
		try {
			HttpClient httpClient = new DefaultHttpClient();
			final String apiEndpoint = "https://selectpdf.com/api2/convert/";
			final String key = "d098f751-40ca-4811-9069-13a42fe3209f";

			Map<String, Object> parameters = new LinkedHashMap<>();
			parameters.put("key", key);
			final String html1 = "<html><head><body><div style=\"font-size: 20px;\">hello world</div></body></head></html>";
			parameters.put("html", html1);

			String encodedParameters = encodeParameters(parameters);

			ArrayList<NameValuePair> postParameters;

			postParameters = new ArrayList<>();
			postParameters.add(new BasicNameValuePair("key", key));
			postParameters.add(new BasicNameValuePair("html", html));

			HttpPost post;
			post = new HttpPost("https://selectpdf.com/api2/convert/"); // local

			post.setEntity(new UrlEncodedFormEntity(postParameters, "UTF-8"));
			HttpResponse response = (HttpResponse) httpClient.execute(post);
			// URL apiUrl = new URL(apiEndpoint + "?" + encodedParameters);
			// urlConnection = (HttpURLConnection)apiUrl.openConnection();
			final BufferedInputStream inputStream = new BufferedInputStream(response.getEntity().getContent());
			final FileOutputStream fileOutput = new FileOutputStream(localFile);
			final BufferedOutputStream outputStream = new BufferedOutputStream(fileOutput);

			final byte[] byteC = new byte[8 * 1024];
			int read = 0;
			while ((read = inputStream.read(byteC)) > -1) {
				outputStream.write(byteC, 0, read);
			}
			outputStream.flush();
			outputStream.close();
			inputStream.close();

			// buffer = EntityUtils.toByteArray(response.getEntity());

			if (urlConnection != null) {
				if (urlConnection.getResponseCode() != 200) {
					log.info("HTTP Response Code: " + urlConnection.getResponseCode());
				}
			}
		} catch (Exception ex) {
			log.info("Error: " + ex.getMessage());
		}
	}

	/**
	 * @param params
	 * @return
	 */
	private static String encodeParameters(final Map<String, Object> params) {
		try {
			final StringBuilder data = new StringBuilder();
			for (final Map.Entry<String, Object> param : params.entrySet()) {
				if (data.length() != 0)
					data.append('&');
				data.append(URLEncoder.encode(param.getKey(), "UTF-8"));
				data.append('=');
				data.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
				final byte[] out = data.toString().getBytes(StandardCharsets.UTF_8);
				int length = out.length;
			}

			return data.toString();
		} catch (Exception ex) {

			log.info("Error in ProductPDFService : encodeParameters" + ex.getMessage());
			// throw(ex);
		}
		return "";
	}

}
