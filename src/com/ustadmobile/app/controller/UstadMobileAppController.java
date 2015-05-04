/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ustadmobile.app.controller;

import java.io.InputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;

/**
 *
 * @author varuna
 */
public class UstadMobileAppController {
    
    /**
     * Dir to contain app data (e.g. logs, settings etc)
     */
    public static String appDataDir = null;
    
    public static String getAppDataDir(){
        String baseFolder = System.getProperty("fileconn.dir.photos") + "umobiledata";
        try {
            FileConnection bCon = (FileConnection)Connector.open(baseFolder);
            if(!bCon.isDirectory()) {
                bCon.mkdir();
                bCon.close();
                return("Created Directory");
            }
            bCon.close();
            appDataDir = baseFolder;
            String appDataURI = appDataDir;
            String settingsDataURI = appDataURI +"/test-settings.xml";
            FileConnection fCon = (FileConnection)Connector.open(settingsDataURI,
                Connector.READ);
            InputStream is = null;
            String str = null;
            if(fCon.exists()) 
                {
                    int size = (int)fCon.fileSize();
                    is= fCon.openInputStream();
                    byte bytes[] = new byte[size];
                    is.read(bytes, 0, size);
                    str = new String(bytes, 0, size);
                    String returnStr = (String) settingsDataURI.toString() + " File exists";
                    return (returnStr);
                }else{
                    String returnStr = (String) settingsDataURI.toString() + " File does NOT exists";
                    return (returnStr);
            }
            //return baseFolder;//all OK
        }catch(Exception e3) {
            e3.printStackTrace();
        }
        return ("Fail2?");
        //return (baseFolder);
    }
           
    /**
     * Find out where we should put the base folder by finding the root folder
     * with the maximum amount of space (this should be the memory card generally)
     */
    public static String setupAppDataDir() {
        String baseFolder = System.getProperty("fileconn.dir.photos") + "umobiledata";
        
        try {
            FileConnection bCon = (FileConnection)Connector.open(baseFolder);
            if(!bCon.isDirectory()) {
                bCon.mkdir();
            }
            bCon.close();
            appDataDir = baseFolder;
            return baseFolder;//all OK
        }catch(Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }
    
    public final static String getBaseDir() {
        return appDataDir;
    }
    
}
