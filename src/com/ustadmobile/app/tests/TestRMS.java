/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ustadmobile.app.tests;

import com.ustadmobile.app.RMSUtils;
import j2meunit.framework.TestCase;
import java.util.Hashtable;

/**
 *
 * @author varuna
 */
public class TestRMS extends TestCase {
    
    public TestRMS(){
        setName("Testing RMS functionality.");
    }
    
    public void runTest() throws Throwable{
        RMSUtils rms = new RMSUtils("UstadMobileApp");
        rms.deleteRMS();
        String string1 = "Hey, hows it going?";
        String string2 = "Oh not bad, hows the cat?";
        rms.openRMS();
        rms.writeRMS(string1);
        rms.writeRMS(string2);
        rms.closeRMS();
        rms.openRMS();
        Hashtable  rmsht;
        rmsht = rms.readRMS();
        rms.closeRMS();
        
        assertEquals("RMS read and write test success", string1,
                rmsht.get("1"));
        assertEquals("RMS read and write test success", string2,
                rmsht.get("2"));
        
        
    }
    
}
