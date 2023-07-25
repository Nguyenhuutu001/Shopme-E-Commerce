package com.shopme.admin;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;

public class AbstractExporter {
	public void setResponseHeader(HttpServletResponse response, String contentTytpe, String extension, String prefix) throws IOException {
		DateFormat dateFomatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		String timestamp = dateFomatter.format(new Date());
		String fileName =prefix + timestamp + extension;
		
		response.setContentType(contentTytpe);
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=" + fileName;
		response.setHeader(headerKey, headerValue);
		
		
		
	}
}
