package com.megatravel.mikroserviskorisnici.xml.endpointi;

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

    @Bean(name = "korisnici")
    public DefaultWsdl11Definition defaultWsdl11DefinitionKorisnici(XsdSchema korisniciSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("PortZaKorisnike");
        wsdl11Definition.setLocationUri("/servisi");
        wsdl11Definition.setTargetNamespace("http://www.megatravel.com/mikroserviskorisnici/xml");
        wsdl11Definition.setSchema(korisniciSchema);
        return wsdl11Definition;
    }

    @Bean(name = "korisniciSchema")
    public XsdSchema korisniciSchema() {
        return new SimpleXsdSchema(new ClassPathResource("korisnici.xsd"));
    }
    
}
