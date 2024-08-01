import org.apache.cxf.Bus
import org.apache.cxf.endpoint.EndpointImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import soap.crud.BookService
import soap.crud.WSSecurityInterceptor

@Configuration
class cxfConfig {
    Bus bus
    BookService bookService
    WSSecurityInterceptor wsSecurityInterceptor

    @Bean
    EndpointImpl bookServiceEndpoint() {
        def endpoint = new EndpointImpl(bus, bookService, javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
        endpoint.publish("/book")
        endpoint.getInInterceptors().add(wsSecurityInterceptor)
        return endpoint

    }
}
