package org.esupportail.cookingapp.domain.config;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.InternetAddress;

import org.esupportail.commons.services.smtp.AsynchronousSmtpServiceImpl;
import org.esupportail.commons.services.smtp.SmtpServer;
import org.esupportail.commons.services.smtp.SmtpService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
@Lazy
public class SmtpConfig {

	@Value("${smtp.host}")
	private String host;

	@Value("${smtp.port}")
	private int port;

	@Value("${smtp.user}")
	private String user;

	@Value("${smtp.password}")
	private String password;

	@Value("${smtp.charset}")
	private String charset;

	@Value("${smtp.fromEmail}")
	private String fromEmail;

	@Value("${smtp.fromName}")
	private String fromName;

	@Value("${smtp.testEmail}")
	private String testEmail;

	@Value("${smtp.testName}")
	private String testName;

	@Value("${smtp.interceptEmail}")
	private String interceptEmail;

	@Value("${smtp.interceptName}")
	private String interceptName;

	@Value("${smtp.notInterceptedAddresses}")
	private String notInterceptedAddresses;

	@Value("${smtp.interceptAll}")
	private boolean interceptAll;
	
	@Bean
	public SmtpService smtpService() throws UnsupportedEncodingException {
		@SuppressWarnings("serial")
		final List<SmtpServer> servers = new ArrayList<SmtpServer>() {{
			add(smtpServer());
		}};
		
		AsynchronousSmtpServiceImpl service = new AsynchronousSmtpServiceImpl();
		service.setCharset(charset);
		service.setInterceptAll(interceptAll);
		service.setNotInterceptedAddresses(notInterceptedAddresses);
		service.setInterceptAddress(smtpIntercept());
		service.setTestAddress(smtpTestAddress());
		service.setFromAddress(smtpFromAddress());
		service.setServers(servers);
		return service;
	}
	
	@Bean
	public SmtpServer smtpServer() {
		SmtpServer server = new SmtpServer();
		server.setHost(host);
		server.setPort(port);
		server.setUser(user);
		server.setPassword(password);
		return server;
	}

	@Bean
	public InternetAddress smtpFromAddress() throws UnsupportedEncodingException {
		InternetAddress address = new InternetAddress();
		address.setAddress(fromEmail);
		address.setPersonal(fromName);
		return address;
	}

	@Bean
	public InternetAddress smtpIntercept() throws UnsupportedEncodingException {
		InternetAddress address = new InternetAddress();
		address.setAddress(interceptEmail);
		address.setPersonal(interceptName);
		return address;
	}

	@Bean
	public InternetAddress smtpTestAddress() throws UnsupportedEncodingException {
		InternetAddress address = new InternetAddress();
		address.setAddress(testEmail);
		address.setPersonal(testName);
		return address;
	}
	
}
