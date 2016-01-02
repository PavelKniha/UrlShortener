package com.knihapaul.urlshortener.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import com.github.dandelion.core.web.DandelionFilter;
import com.github.dandelion.core.web.DandelionServlet;
import com.github.dandelion.datatables.core.web.filter.DatatablesFilter;

public class ApplicationInitializer implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(RootConfig.class);

        AnnotationConfigWebApplicationContext mvcContext = new AnnotationConfigWebApplicationContext();
        mvcContext.register(WebConfig.class);

        servletContext.addListener(new ContextLoaderListener(rootContext));
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcherServlet", new DispatcherServlet(mvcContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

        FilterRegistration.Dynamic springSecurityFilterChain = servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy("springSecurityFilterChain"));
        springSecurityFilterChain.addMappingForUrlPatterns(null, false, "/*");
        
        // 
        FilterRegistration.Dynamic dandelionFilter = servletContext.addFilter("dandelionFilter", new DandelionFilter());
        dandelionFilter.addMappingForUrlPatterns(null, false, "/*"); 
//        FilterRegistration.Dynamic datatables = servletContext.addFilter("datatables", new DatatablesFilter());
//        datatables.addMappingForUrlPatterns(null, false, "/*");
        ServletRegistration.Dynamic dandelionServlet = servletContext.addServlet("dandelionServlet", new DandelionServlet());
        dandelionServlet.setLoadOnStartup(2);
        dandelionServlet.addMapping("/dandelion-assets/*");	
	}

}
