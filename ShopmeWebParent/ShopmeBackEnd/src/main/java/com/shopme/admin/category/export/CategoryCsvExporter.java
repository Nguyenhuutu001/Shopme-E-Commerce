package com.shopme.admin.category.export;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.shopme.admin.AbstractExporter;
import com.shopme.common.entity.Category;
import com.shopme.common.entity.User;



public class CategoryCsvExporter extends AbstractExporter{
	
	public void export(List<Category> listCategories, HttpServletResponse response) throws IOException {
		super.setResponseHeader(response, "text/csv", ".csv", "users_");
		
		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		
		String [] csvHeader = {"Category ID", "Category Name"};
		String[] filedMapping = {"id", "name"};
		
		csvWriter.writeHeader(csvHeader);
		
		for(Category category : listCategories) {
			category.setName(category.getName().replace("--", " "));
			csvWriter.write(category, filedMapping);
		}
		
		csvWriter.close();
		
	}

}
