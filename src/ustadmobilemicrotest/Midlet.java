/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ustadmobilemicrotest;

//For Exit Command button:

//import com.ustadmobile.app.DeviceRoots;
//import com.ustadmobile.app.controller.UstadMobileAppController;
//import com.ustadmobile.app.FileUtils;

import com.sun.lwuit.Form;
import com.ustadmobile.app.tests.AllTestCases;
import com.ustadmobile.app.tests.TestUtils;
import java.io.IOException;
import java.util.Hashtable;

//import javax.microedition.lcdui.Command;
//import javax.microedition.lcdui.CommandListener;

//To Display on screen
//import javax.microedition.lcdui.Display;
import com.sun.lwuit.Display;
import com.sun.lwuit.Label;
import com.sun.lwuit.TextField;
import com.sun.lwuit.layouts.BoxLayout;
//import javax.microedition.lcdui.Gauge;
//import javax.microedition.lcdui.List;
//import javax.microedition.lcdui.Displayable;

//TextBox for screen:
import javax.microedition.lcdui.TextBox;
//import javax.microedition.midlet.*;

import com.ustadmobile.app.forms.TestForm;

/**
 * @author varuna
 */
public class Midlet extends j2meunit.midletui.TestRunner{ //extends MIDlet {// implements CommandListener {

    private TextBox tbox;
    //private Command exitCommand;
    
    public Midlet() {

        tbox = new TextBox("App Dir", 
                "Hows it going?", 100, 0 );
        tbox = new TextBox("microedition.platform", 
                TestUtils.getPlatform().toString(), 100, 0); 
  
        /*
        //Exit Command.
        exitCommand = new Command("Exit", Command.EXIT, 1);
        tbox.addCommand(exitCommand);
        tbox.setCommandListener(this);
        */
    }
    
    //To Display on Screen
    
    /*public void startApp() {
        //Display.getDisplay(this).setCurrent(tbox);
        Display.init(this);                      
        Form f = TestForm.loadTestForm();
        f.show();

    }*/
    
    //Start the tests:
    public void startApp(){  
        //start(new String[] { com.ustadmobile.app.tests.AllTestCases.class.getName() });
        
        
        Display.init(this);
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
                        testResult.put("device", TestUtils.getPlatform().toString());
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
        //j2meunit.textui.TestRunner.main(new String[] {com.ustadmobile.app.tests.AllTestCases.class.getName()});
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
