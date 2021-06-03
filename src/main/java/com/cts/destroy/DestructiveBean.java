package com.cts.destroy;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import lombok.Setter;

public class DestructiveBean implements InitializingBean {
	private static final Logger logger = LoggerFactory.getLogger(DestructiveBean.class);

	private File file;
	@Setter
	private String filePath;

	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("Initializing  Bean");
		if (filePath == null) {
			throw new IllegalArgumentException("You must specify the filePath property of" + DestructiveBean.class);
		}
		this.file = new File(filePath);
		this.file.createNewFile();
		logger.info("File exists: " + file.exists());

	}

	public void destroy() {
		logger.info("Destroying  Bean");
		/*if (!file.delete()) {
			logger.error("ERROR: failed  to delete file.");
		}*/
		logger.info("File exists: " + file.exists());
	}

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("app-context.xml");
		DestructiveBean bean = (DestructiveBean) ctx.getBean("destructiveBean");
		System.out.println("Calling destroy()");
		((AbstractApplicationContext) ctx).destroy();
		System.out.println("Called destroy()");

	}
}
