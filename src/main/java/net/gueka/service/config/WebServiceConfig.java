package net.gueka.service.config;

import javax.servlet.Servlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import net.gueka.service.endpoint.RuleEndpoint;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

	@Bean
	public ServletRegistrationBean<Servlet> messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean<Servlet>(servlet, "/ws/*");
	}

    @Bean(name = "rules")
	public Wsdl11Definition defaultWsdl11Definition(XsdSchema smrelaySchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("getRuleValidation");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace(RuleEndpoint.NAMESPACE_URI);
		wsdl11Definition.setSchema(smrelaySchema);
		return wsdl11Definition;
	}

	@Bean
	public XsdSchema smrelaySchema() {
		return new SimpleXsdSchema(new ClassPathResource("rules.xsd"));
	}
}