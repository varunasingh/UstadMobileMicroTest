/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ustadmobile.app.tests;
import j2meunit.framework.Test;
import j2meunit.framework.TestCase;
import j2meunit.framework.TestSuite;

/**
 *
 * @author varuna
 */
public class AllTestCases extends TestCase {

    public Test suite() {
//        try {
//            TestUtils.loadTestSettings();
//        }catch(Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException(e.toString());
//        }
        
        System.out.println("Starting Tests..");
        TestSuite allTestSuite = new TestSuite("AlltestSuites");
        
        allTestSuite.addTest(new TestSimple());
        
        return allTestSuite;

    }
}