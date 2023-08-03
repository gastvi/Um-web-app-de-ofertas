package br.com.mvc.mudi;

import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import br.com.mvc.mudi.interceptor.InterceptadorDeAcessos;

public class WebConfig extends WebMvcConfigurationSupport {
	
	private static WebRequestInterceptor requestInterceptor;
	
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new InterceptadorDeAcessos(requestInterceptor)).addPathPatterns("/**");
	
	}
	
}
