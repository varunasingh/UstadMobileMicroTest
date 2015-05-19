/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ustadmobilemicrotest;

//For Exit Command button:

//import com.ustadmobile.app.DeviceRoots;
//import com.ustadmobile.app.controller.UstadMobileAppController;
//import com.ustadmobile.app.FileUtils;

import com.ustadmobile.app.tests.AllTestCases;
import com.ustadmobile.app.tests.TestUtils;
import java.io.IOException;
import java.util.Hashtable;

//import javax.microedition.lcdui.Command;
//import javax.microedition.lcdui.CommandListener;

//To Display on screen
//import javax.microedition.lcdui.Display;
//import javax.microedition.lcdui.Gauge;
//import javax.microedition.lcdui.List;
//import javax.microedition.lcdui.Displayable;

//TextBox for screen:
import javax.microedition.lcdui.TextBox;
//import javax.microedition.midlet.*;

/**
 * @author varuna
 */
public class Midlet extends j2meunit.midletui.TestRunner {//extends MIDlet {// implements CommandListener {

    private TextBox tbox;
    //private Command exitCommand;
    
    public Midlet() {

        tbox = new TextBox("App Dir", 
                "Hows it going?", 100, 0 ); 
        /*
         * 
        //Get Best Root Name (max available size)
        DeviceRoots bestRoot = new DeviceRoots();
        bestRoot = null;
        bestRoot = FileUtils.getBestRoot();
        String bestRootName = null;
        if (bestRoot != null){
            bestRootName = bestRoot.name;
        }
        //tbox = new TextBox("App Dir", 
        //        bestRootName, 100, 0 ); 
        
        //Get All Roots Names available on the phone. 
        DeviceRoots[] allRoots = FileUtils.getAllRoots();
        String allRootsString = "All Roots are";
        for (int i = 0; i<allRoots.length;i++){
            allRootsString = allRootsString  + ", " + allRoots[i].name;
        }
        tbox = new TextBox("App Dir", 
                allRootsString, 1000, 0 ); 
        
        */
                
        /*
        //Exit Command.
        exitCommand = new Command("Exit", Command.EXIT, 1);
        tbox.addCommand(exitCommand);
        tbox.setCommandListener(this);
        */
    }
    
    //To Display on Screen
    /*public void startApp() {
        Display.getDisplay(this).setCurrent(tbox);
    }*/
    
    //Start the tests:
    public void startApp(){  
        //start(new String[] { com.ustadmobile.app.tests.AllTestCases.class.getName() });
        
        
        
        AllTestCases atc = new AllTestCases() ;
        int ctc = atc.suite().countTestCases();
        System.out.println("Number of Tests: " + ctc);
        start(new String[] {
                atc.getClass().getName()
        });
        
        while(true){
                try{
                    int rc = aResult.runCount();
                    int ac = aResult.assertionCount();
                    System.out.println("assertionCount: " + ac + "/" + rc);
                    
                    if (rc == ctc){
                        System.out.println("");
                        System.out.println("All done?");
                        int numError = aResult.errorCount();
                        String numAssert = String.valueOf(aResult.assertionCount());
                        String numFail = String.valueOf(aResult.failureCount());
                        boolean result = aResult.wasSuccessful();
                        System.out.println("Error: " + numError + ", Fail: " + 
                                numFail + ", Assert: " + numAssert + 
                                ", Result: " + result);
                        
                        
                        //POST-ing
                        
                        Hashtable testResult = new Hashtable();
                        testResult.put("numPass", numAssert);
                        testResult.put("numFail", numFail);
                        testResult.put("logtext", 
                                "Result");
                        testResult.put("device", 
                                TestUtils.testSettings.get("device"));
                        try {
                            String postResult = null;
                            postResult = TestUtils.sendPost(
                                    TestUtils.testSettings.get("testposturl").toString(), 
                                    testResult);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        
                        //Get outta here.
                        break;
                    }
                }catch(Exception e){
                    System.out.print(".");
                }
                    
        }
        
        
    }
    
    /**
     * Helpful for executing tests from command line / microemulator
     */
    public static void main(String[] args){
        j2meunit.textui.TestRunner.main(new String[] {com.ustadmobile.app.tests.AllTestCases.class.getName()});
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
    
     /* Return in this format:
     * POST:
     *      var numPass = post['numPass'];
            var numFail = post['numFail'];
            var logtext = post['logtext'];
     */
    
    /*
    //For Exit Command button:
    public void commandAction(Command c, Displayable d) {
        if (c == exitCommand) {
            destroyApp(false);
            notifyDestroyed();
        }
    }*/
    
}
