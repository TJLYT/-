package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.text.View;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.mobile.device.view.LiteDeviceDelegatingViewResolver;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

@SpringBootApplication
//通过一个URL Mapping然后不经过Controller处理直接跳转到页面上的需求
public class TableApplication extends WebMvcConfigurerAdapter{
	@Value("${spring.messages.basename}")
	private String baseName;
	@Value("${lang}")
	private String lang;
	@Autowired
	private ThymeleafViewResolver thymeleafViewResolver;
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(TableApplication.class, args);
							int count=	context.getBeanDefinitionCount();
							System.out.println(count);
							String[] name =context.getBeanDefinitionNames();
							for (String string : name) {
								System.out.println("names"+name);
							}
	}
	
	//国际化
	@Bean(name="messageSource")
	public ResourceBundleMessageSource getMessageResource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename(baseName);
		return messageSource;
	}
	
	@Bean
	public org.springframework.web.servlet.LocaleResolver localResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.US);
		return slr;
	}
	
	@Bean
	public ViewResolver content(ContentNegotiationManager manager) {
		ArrayList<ViewResolver> resolvers = new ArrayList<ViewResolver>();
		resolvers.add(liteViewResolver());
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setViewResolvers(resolvers);
		resolver.setContentNegotiationManager(manager);
		List<View> views = new ArrayList<View>();
		views.addAll(views);
	//	resolver.setDefaultViews(views);
		return resolver;
		
	}
	
	@Bean
	public LiteDeviceDelegatingViewResolver liteViewResolver() {
		LiteDeviceDelegatingViewResolver resolver = new LiteDeviceDelegatingViewResolver(thymeleafViewResolver);
		return resolver;
	}
	
	
	
	
	
}
