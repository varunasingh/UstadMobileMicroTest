/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ustadmobile.app.tests;

import com.ustadmobile.app.opf.UstadJSOPF;
import j2meunit.framework.TestCase;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.kxml2.io.KXmlParser;
/**
 *
 * @author varuna
 */
public class TestOPFParse extends TestCase{
    
    public TestOPFParse(){
        setName("OPFParse Test");
    }
    
    public void runTest() throws Throwable{
        
        InputStream bais = TestUtils.getFileBytes(
                TestUtils.testSettings.get("appDataURI").toString() + "/" +
                TestUtils.testSettings.get("opfxml").toString());
        
        /*ByteArrayInputStream bais = 
                TestUtils.getHTTPBytes(
                    TestUtils.testSettings.get("opfxml").toString());*/
        KXmlParser parser = new KXmlParser();
        parser = (KXmlParser) TestUtils.parseXml(bais);
        UstadJSOPF feed = UstadJSOPF.loadFromOPF(parser);
        
        assertEquals("Spine made successfully", 
                "cover.xhtml", feed.spine[0].href);
        assertEquals("Mime Exception Stored successfully", 
                "application/mime+ex", 
                feed.getMimeType("mime_exception.mex"));
        assertEquals("Mime Default test ok", 
                feed.DEFAULT_MIMETYPE, 
                feed.getMimeType("blahfile.txt"));
    }
    
}
