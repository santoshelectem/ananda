/**
 * 
 */
package com.example.postgresdemo.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.postgresdemo.exception.FileStorageException;
import com.example.postgresdemo.exception.MyFileNotFoundException;
import com.example.postgresdemo.model.UploadFileResponse;
import com.example.postgresdemo.service.FileStorageService;

/**
 * @author Cybertech1
 *
 */
@RestController
public class FileController {
	
	 @Autowired
	  MessageSource messageSource;
	/**
	 * Logger
	 */
	private Logger log = LoggerFactory.getLogger(FileController.class);

	/**
	 * fileStorageService
	 */
	@Autowired
	private FileStorageService fileStorService;

	/**
	 * @param file
	 * @return
	 * @throws FileStorageException
	 */
	@PostMapping("/uploadFile")
	public UploadFileResponse uploadFile(final @RequestParam("file") MultipartFile file) {
		log.info("Start of FileController :: uploadFile ");
		String fileName = null;
		try {
			if (file != null) {
				fileName = fileStorService.storeFile(file);
			}
		} catch (FileStorageException e) {
			log.error("FileController :: uploadFile" + e.getMessage());
		}
		
		final String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
				.path(fileName).toUriString();
		log.info("end of FileController :: uploadFile ");
		log.info(messageSource.getMessage("file.controller.upload.show.success", null, LocaleContextHolder.getLocale()));
		return new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
	}

	/**
	 * @param fileName
	 * @param request
	 * @return
	 * @throws MyFileNotFoundException
	 */
	@GetMapping("/downloadFile/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(final @PathVariable String fileName, final HttpServletRequest request)
			throws MyFileNotFoundException {
		// Load file as Resource
		log.info("Start of FileController :: downloadFile ");
		final Resource resource = fileStorService.loadFileAsResource(fileName);

		// Try to determine file's content type
		String contentType = null;
		try {
			
		//	File file = resource.getFile()
		//	String fileName = file.getAbsolutePath()
		//	ServletContext ctx = request.getServletContext();
		//	contentType = ctx.getMimeType(fileName)
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			log.error("FileController :: downloadFile :: Could not determine file type.");
		}

		// Fallback to the default content type if type could not be determined
		if (contentType == null) {
			contentType = "application/octet-stream";
		}
		log.info("end of FileController :: downloadFile ");
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

}
