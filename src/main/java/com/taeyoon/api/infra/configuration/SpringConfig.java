package com.taeyoon.api.infra.configuration;

import org.modelmapper.AbstractCondition;
import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NamingConventions;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
