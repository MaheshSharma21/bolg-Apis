package com.bikkadit.blog.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bikkadit.blog.services.FileServiceI;

@Service
public class FileServiceImpl implements FileServiceI {

	private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		logger.info(" Initiated Request  for uploading Image ");
		// File Name
		String name = file.getOriginalFilename();

		// random file Name generate
		String randomId = UUID.randomUUID().toString();
		String fileName1 = randomId.concat(name.substring(name.lastIndexOf(".")));

		// full path
		String filepath = path + File.separator + fileName1;

		// create folder if not created
		File f = new File(path);
		if (!f.exists()) {
			f.mkdir();
		}

		// file copy
		Files.copy(file.getInputStream(), Paths.get(filepath));

		logger.info(" completed Request  for uploading Image  ");
		return fileName1; 
	}

	@Override
	public InputStream getresource(String path, String fileName) throws FileNotFoundException {
		logger.info(" Initiated Request  for accessing Image ");
		String fullpath = path + File.separator + fileName;
		InputStream stream = new FileInputStream(fullpath);

		// db logic to return inputstream
		logger.info(" completed Request  for accessing Image ");
		return stream;
	}

}
