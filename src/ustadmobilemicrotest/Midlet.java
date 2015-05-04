/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ustadmobilemicrotest;

//For Exit Command button:
//import javax.microedition.lcdui.Command;
//import javax.microedition.lcdui.CommandListener;

//To Display on screen
//import javax.microedition.lcdui.Display;
//import javax.microedition.lcdui.Displayable;

//TextBox for screen:
//import javax.microedition.lcdui.TextBox;
//import javax.microedition.midlet.*;

//For Displaying on screen:
//import com.ustadmobile.app.controller.UstadMobileAppController;
/**
 * @author varuna
 */
public class Midlet extends j2meunit.midletui.TestRunner {//extends MIDlet {// implements CommandListener {

    //private TextBox tbox;
    //private Command exitCommand;
    
    public Midlet() {
        
        //tbox = new TextBox("App Dir", UstadMobileAppController.getAppDataDir().toString(), 100, 0 );
        //tbox = new TextBox("Ustad Mobile Micro Test", "Testing..", 25, 0);
        
        //Exit Command.
        //exitCommand = new Command("Exit", Command.EXIT, 1);
        //tbox.addCommand(exitCommand);
        //tbox.setCommandListener(this);
    }
    
    //To Display on Screen
//    public void startApp() {
//        Display.getDisplay(this).setCurrent(tbox);
//    }
    
    //Start the tests:
    public void startApp(){   
        start(new String[] { com.ustadmobile.app.tests.AllTestCases.class.getName() });
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

    //For Exit Command button:
    //public void commandAction(Command c, Displayable d) {
        //if (c == exitCommand) {
        //    destroyApp(false);
        //    notifyDestroyed();
        //}
    //}
}
