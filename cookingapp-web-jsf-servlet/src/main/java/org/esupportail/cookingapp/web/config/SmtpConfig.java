package org.esupportail.cookingapp.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Lazy;

@Configuration
@Lazy
@ImportResource("classpath:properties/smtp/smtp.xml")
public class SmtpConfig {

}
