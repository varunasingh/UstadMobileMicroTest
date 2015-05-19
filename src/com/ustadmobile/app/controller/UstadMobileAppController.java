/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ustadmobile.app.controller;

import com.ustadmobile.app.DeviceRoots;
import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import com.ustadmobile.app.FileUtils;
/**
 *
 * @author varuna
 */
public class UstadMobileAppController {
    
    /**
     * Dir to contain app data (e.g. logs, settings etc)
     */
    public static String appDataDir = null;
    
    
    /**
     * Find out where we should put the base folder by finding the root folder
     * with the maximum amount of space (this should be the memory card generally)
     */
    public static String getAppDataDir(){
        DeviceRoots bestRoot = FileUtils.getBestRoot();
        if (bestRoot==null){
            return null;
        }
        String baseFolder = bestRoot.path + "umobiledata";
        try{
            FileConnection bCon = (FileConnection)Connector.open(baseFolder);
            if (!bCon.isDirectory()){
                bCon.mkdir();
            }
            bCon.close();
            appDataDir = baseFolder;
            return appDataDir;
        }catch (Exception ce){
            return null;
        }
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
        }
        return null;
    }
    
    public final static String getBaseDir() {
        return appDataDir;
    }
   
}
