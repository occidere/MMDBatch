package org.occidere.mmdbatch.batch;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Main {
	public static void main(String[] args) throws Exception {
		String[] config = { "application-batch.xml" };
		
		// Creating the application context object
		ApplicationContext context = new ClassPathXmlApplicationContext(config);
	}
}
