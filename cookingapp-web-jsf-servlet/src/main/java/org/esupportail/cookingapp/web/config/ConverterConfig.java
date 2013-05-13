package org.esupportail.cookingapp.web.config;

import org.esupportail.commons.web.converters.LocaleConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
@Lazy
public class ConverterConfig {

	@Bean
	public LocaleConverter localeConverter() {
		return new LocaleConverter();
	}
}
