package com.taeyoon.api.infra.configuration;

import java.util.Locale;

import org.modelmapper.AbstractCondition;
import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NamingConventions;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class SpringConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry
			.addMapping("/**")
			.allowCredentials(true)
			.allowedOrigins(
				"http://localhost:3300"
				, "http://localhost:3301"
				, "http://localhost:3500"
				, "http://localhost:3400"
				, "http://localhost:3501"
				, "http://localhost:3510"
			)
			.allowedMethods(
				HttpMethod.GET.name(),
				HttpMethod.POST.name(),
				HttpMethod.DELETE.name(),
				HttpMethod.PUT.name(),
				HttpMethod.PATCH.name(),
				HttpMethod.OPTIONS.name()
			);
	}

	@Override
	public void addInterceptors(InterceptorRegistry interceptorRegistry) {
		// interceptorRegistry.addInterceptor(logInterceptor).order(0)
		// 	// 적용할 패턴
		// 	.addPathPatterns("/**")
		// 	// 제외할 패턴
		// 	.excludePathPatterns("/error")
		// 	.excludePathPatterns("/healthy")
		// 	.excludePathPatterns("/favicon.ico")
		// 	.excludePathPatterns("/swagger")
		// 	.excludePathPatterns("/api-docs")
		// ;
		//
		// interceptorRegistry.addInterceptor(permissionInterceptor).order(1)
		// 	// 적용할 패턴
		// 	.addPathPatterns("/api/**")
		// 	// 제외할 패턴
		// 	.excludePathPatterns("/api/rental-car/*/**") // 랜트카 api 제외
		// ;
		//
		// interceptorRegistry.addInterceptor(rentalPermissionInterceptor).order(2)
		// 	// 적용할 패턴
		// 	.addPathPatterns("/api/rental-car/*/**")
		// ;
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
			.setAmbiguityIgnored(true)
			.setSkipNullEnabled(true)
			.setPropertyCondition(isStringBlank)
			.setSourceNamingConvention(NamingConventions.NONE)
			.setDestinationNamingConvention(NamingConventions.NONE)
			.setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}

	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setCacheSeconds(3600); // 1시간
		messageSource.setDefaultLocale(Locale.ENGLISH); // message_en.properties 파일을 기본값으로 지정
		return messageSource;
	}

	@Bean
	public LocaleResolver localeResolver() {
		// Accept-Language 헤더를 기반으로 로케일을 결정하는 LocaleResolver
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
		// 기본 로케일 설정 (선택 사항)
		localeResolver.setDefaultLocale(Locale.ENGLISH);
		return localeResolver;
	}

	// string blank condition
	Condition<?, ?> isStringBlank = new AbstractCondition<>() {
		@Override
		public boolean applies(MappingContext<Object, Object> context) {
			if (context.getSource() instanceof String) {
				return null != context.getSource() && !"".equals(context.getSource());
			} else {
				return context.getSource() != null;
			}
		}
	};
}
