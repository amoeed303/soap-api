package soap.crud


import org.apache.cxf.binding.soap.SoapMessage
import org.apache.cxf.headers.Header
import org.apache.cxf.interceptor.Fault
import org.apache.cxf.phase.AbstractPhaseInterceptor
import org.apache.cxf.phase.Phase
import groovy.xml.XmlUtil

class WSSecurityInterceptor extends AbstractPhaseInterceptor<SoapMessage> {
    private static final String EXPECTED_SECURITY_HEADER = "secure"

    WSSecurityInterceptor() {
        super(Phase.PRE_PROTOCOL)
    }

    @Override
    void handleMessage(SoapMessage message) throws Fault {
        List<Header> headers = message.getHeaders()
        Header securityHeader = headers.find { it.name.localPart == 'Security' }
        if (securityHeader) {
            def slurper = new XmlSlurper()
            def securityXml = XmlUtil.serialize(securityHeader.getObject())
            def securityValue = slurper.parseText(securityXml).text().trim()
            println("securityValue: $securityValue")
            if (securityValue != EXPECTED_SECURITY_HEADER) {
                throw new Fault(new RuntimeException("Unauthorized Request"))
            }
        } else {
            throw new Fault(new RuntimeException('No Authorization Header Found'))
        }
    }
}
//for handling key value pairs
//def soapBody = message.getContent(XML)
//def securityKeyValue = soapBody.'**'.find { it.name() == EXPECTED_SECURITY_KEY }?.text()
//if (securityKeyValue && securityKeyValue.trim() == EXPECTED_SECURITY_VALUE) {
//    println("securityKeyValue: $securityKeyValue")
//} else {
//    throw new Fault(new RuntimeException("Unauthorized Request"))
//}