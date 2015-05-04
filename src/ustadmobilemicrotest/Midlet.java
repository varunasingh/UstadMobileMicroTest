/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ustadmobilemicrotest;

//import javax.microedition.lcdui.Command;
//import javax.microedition.lcdui.CommandListener;
//import javax.microedition.lcdui.Display;
//import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.TextBox;
//import javax.microedition.midlet.*;

/**
 * @author varuna
 */
public class Midlet extends j2meunit.midletui.TestRunner {//extends MIDlet {// implements CommandListener {

    private TextBox tbox;
    //private Command exitCommand;
    
    public Midlet() {
        //exitCommand = new Command("Exit", Command.EXIT, 1);
        tbox = new TextBox("Hello world MIDlet", "Hello World!", 25, 0);
        //tbox.addCommand(exitCommand);
        //tbox.setCommandListener(this);
    }
    
    public void startApp(){   
        start(new String[] { com.ustadmobile.app.tests.AllTestCases.class.getName() });
    }
    
    /**
     * Helpful for executing tests from command line / microemulator
     */
    public static void main(String[] args){
        j2meunit.textui.TestRunner.main(new String[] {com.ustadmobile.app.tests.AllTestCases.class.getName()});
    }
    
//    public void startApp() {
//        Display.getDisplay(this).setCurrent(tbox);
//    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }

    //public void commandAction(Command c, Displayable d) {
        //if (c == exitCommand) {
        //    destroyApp(false);
        //    notifyDestroyed();
        //}
    //}
}
