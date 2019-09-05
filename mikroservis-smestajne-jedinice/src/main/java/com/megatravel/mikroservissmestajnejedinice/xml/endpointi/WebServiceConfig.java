package com.megatravel.mikroservissmestajnejedinice.xml.endpointi;

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
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<MessageDispatcherServlet>(servlet, "/servisi/*");
    }

    @Bean(name = "smestajnejedinice")
    public DefaultWsdl11Definition defaultWsdl11DefinitionSmestajneJedinice(XsdSchema smestajnejediniceSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("PortZaSmestaje");
        wsdl11Definition.setLocationUri("/servisi");
        wsdl11Definition.setTargetNamespace("http://www.megatravel.com/mikroservissmestajnejedinice/xml");
        wsdl11Definition.setSchema(smestajnejediniceSchema);
        return wsdl11Definition;
    }

    @Bean(name = "smestajnejediniceSchema")
    public XsdSchema smestajnejediniceSchema() {
        return new SimpleXsdSchema(new ClassPathResource("smestajnejedinice.xsd"));
    }
    
}