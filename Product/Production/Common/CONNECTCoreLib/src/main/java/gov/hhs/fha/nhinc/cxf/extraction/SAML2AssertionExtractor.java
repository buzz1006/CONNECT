/**
 * 
 */
package gov.hhs.fha.nhinc.cxf.extraction;

import gov.hhs.fha.nhinc.common.nhinccommon.AssertionType;

import java.util.List;

import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.headers.Header;
import org.apache.log4j.Logger;
import org.w3c.dom.Element;

/**
 * This class is used to extract the AssertionType from Saml2Assertion in the ws
 * security header. This AssertionType is provided to the responding HIO for
 * using the information for authorization.
 * 
 * @author mweaver
 *
 */
public class SAML2AssertionExtractor {

    private static final Logger LOGGER = Logger.getLogger(SAML2AssertionExtractor.class);

    /**
     * This method is used to extract the saml assertion information.
     * 
     * @param context
     *            context
     * @return AssertionType
     */
    public final AssertionType extractSamlAssertion(final WebServiceContext context) {
        LOGGER.debug("Executing Saml2AssertionExtractor.extractSamlAssertion()...");
        AssertionType target = null;
        
        MessageContext mContext = (MessageContext) context.getMessageContext();
        
        @SuppressWarnings("unchecked")
        List<Header> headers = (List<Header>) mContext.get(org.apache.cxf.headers.Header.HEADER_LIST);
        
        SoapHeader header = (SoapHeader) headers.get(0);
        Object obj = header.getObject();
        Element element = (Element) obj;
        
        SAMLExtractorDOMFactory factory = new SAMLExtractorDOMFactory();
        SAMLExtractorDOM extractor = factory.getExtractor();
        target = extractor.extractSAMLAssertion(element);
        
        return target;
    }





}
