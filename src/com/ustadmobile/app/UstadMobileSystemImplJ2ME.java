/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ustadmobile.app;

import com.ustadmobile.app.controller.UstadMobileAppController;
import java.io.IOException;
import java.util.Hashtable;
import javax.microedition.io.Connection;
import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;

/**
 *
 * @author varuna
 */
public class UstadMobileSystemImplJ2ME { // extends UstadMobileSystemImpl
    

    public UstadMobileSystemImplJ2ME() {
        
    }
    
    /**
     * Provides the path to the shared content directory 
     * 
     * @return URI of the shared content directory
     */
    public String getSharedContentDir(){ 
        //This will be in something like ustadmobileContent
        //appData is different
        try{
            DeviceRoots dt = FileUtils.getBestRoot();
            String sharedContentDir = dt.path + FileUtils.FILE_SEP + 
                    FileUtils.USTAD_CONTENT_DIR;
            
            //Return null if it doens't exist.
            if (!FileUtils.checkDir(sharedContentDir)){
                return null;
            }
            
            //Check if it is created. If it isnt, create it. 
            if(FileUtils.createFileOrDir(sharedContentDir, 
                    Connector.READ_WRITE, true)){
                return sharedContentDir;
            }
        }catch (Exception e){}
        return null;
    }
    
    public String getUserContentDirectory(String username){
        try{
            DeviceRoots dt = FileUtils.getBestRoot();
            String sharedUserContentDir = dt.path + FileUtils.FILE_SEP + 
                    FileUtils.USTAD_CONTENT_DIR + FileUtils.FILE_SEP + username;
            
            //Return null if it doesn't exist
            if (!FileUtils.checkDir(sharedUserContentDir)){
                return null;
            }
            
            //Check if it is created. If it isn't, create it.
            if(FileUtils.createFileOrDir(sharedUserContentDir, 
                    Connector.READ_WRITE, true)){
                return sharedUserContentDir;
            }
            
        }catch (Exception e){}
        return null;
    }
    
    public String getSystemLocale(){
        //String device = UstadMobileAppController.getPlatform().toString();
        //String locale = UstadMobileAppController.getLocale().toString();
        return System.getProperty("microedition.locale").toString();
    }
    
    public Hashtable getSystemInfo(){
        Hashtable systemInfo = new Hashtable();
        try{
            systemInfo.put("platform", System.getProperty("microedition.platform").toString());
            systemInfo.put("encoding", System.getProperty("microedition.encoding").toString());
            systemInfo.put("configuration", System.getProperty("microedition.configuration").toString());
            systemInfo.put("profiles", System.getProperty("microedition.profiles").toString());
            systemInfo.put("locale", System.getProperty("microedition.locale").toString());
            systemInfo.put("memorytotal", Long.toString(Runtime.getRuntime().totalMemory()));
            systemInfo.put("memoryfree", Long.toString(Runtime.getRuntime().freeMemory()));
            //systemInfo.put("", System.getProperty("microedition.").toString());
            return systemInfo;
        }catch (Exception e){}
        return null;
    }
    
    public String readFileAsText(String fileURI, String encoding) throws IOException{
        try{
            String contents = FileUtils.getFileContents(fileURI);
            return contents;
        }catch(Exception e){}
        return null;
    }
    
    public String readFileAsText(String fileURI) throws IOException{
        return this.readFileAsText(fileURI, "UTF-8");
    }
    
    public int modTimeDifference(String fileURI1, String fileURI2){
        try{
            long file1LastModified = FileUtils.getLastModified(fileURI1);
            long file2LastModified = FileUtils.getLastModified(fileURI2);
            if (file1LastModified != -1 || file2LastModified != -1 ){
                long difference = file1LastModified - file2LastModified;
                return (int)difference;
            }
        }catch(Exception e){}
        return -1;
    }
    
    public void writeStringToFile(String str, String fileURI, String encoding) 
            throws IOException{
        try{
            FileUtils.writeStringToFile(str, fileURI);
            
        }catch (Exception e){}
    }
    
    public boolean fileExists(String fileURI) throws IOException{
        
        return false;
    }
    
    public void removeFile(String fileURI) throws IOException{
        
    }
    
    public String[] listDirectory(String dirURI) throws Exception{
        
        return null;
    }
    
    //public UMTransferJob downloadURLToFile(String url, String fileURI){ 
    //
    //  return null;
    //}
    
    public void renameFile(String fromFileURI, String toFileURI){
        
    }
    
    public int fileSize(String fileURI){
        try{
            return (int) FileUtils.getFileSize(fileURI);
        }catch(Exception e){}
        return -1;
    }
    
    public void makeDirectory(String dirURI) throws IOException{
        FileUtils.createFileOrDir(dirURI, Connector.READ, true);
    }
    
    public void removeRecursively(String dirURI){
        
    }
    
    public void unzipFile(String zipSrc, String distDir){
    //public UMTransferJob unzipFile(String zipSrc, String distDir){
        
    }
    
    public byte[] getHTTPResponseAsBytes(String url, Hashtable headers){
        
        return null;
    }
    
}
