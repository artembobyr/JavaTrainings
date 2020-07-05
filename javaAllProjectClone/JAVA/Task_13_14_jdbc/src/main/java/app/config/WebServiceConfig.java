package app.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(servlet, "/booking/*");
	}

	@Bean(name = "hospital")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema tvShowsSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("HospitalPort");
		wsdl11Definition.setLocationUri("/booking");
		wsdl11Definition.setTargetNamespace("http://preprod/qa/soap");
		wsdl11Definition.setSchema(tvShowsSchema);
		return wsdl11Definition;
	}

	@Bean
	public XsdSchema hospitalShema() {
		return new SimpleXsdSchema(new ClassPathResource("task_hospital.xsd"));
	}
}
