/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ustadmobile.app.tests;

import j2meunit.framework.TestCase;

/**
 *
 * @author varuna
 */
public class TestFileAccess extends TestCase {
    public TestFileAccess(){
        setName("TestFileAccess Test");
    }
    
    public void runTest() throws Throwable{
    
        String str = TestUtils.loadTestSettingsFile();
        if (str!=null){
            if (str.startsWith("<?xml version")){
                assertTrue(true);
            }
        }else{
            assertTrue(false);
        }
        
    }
    
}
