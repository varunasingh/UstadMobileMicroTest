/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ustadmobile.app.controller;

import com.sun.lwuit.Image;
import com.ustadmobile.app.FileUtils;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import javax.microedition.io.file.FileSystemRegistry;

/**
 *
 * @author varuna
 */
public class UstadMobileCatalogController {
    
    public static final int MENUITEM_CATALOG = 0;
    private String noCourse_Flag = "TRUE";
    private final static String SEP_STR = "/";
    //represents normal exetoc.xml direct export
    static final int TYPE_NORM = 0;
    
    //represents execollection
    static final int TYPE_COL = 1;
    
    /**
    * Simple holder class for items in the list
    * 
    * @author mike
    */
    class ContentBrowseItem {
        int type;
        String title;
        Image icon;
        String fullpath;

        ContentBrowseItem(String fullpath, int type, String title, Image icon) {
            this.type = type;
            this.title = title;
            this.icon = icon;
            this.fullpath = fullpath;
        }
    }
    
    /**
     * 
     * @param dirName The dirname that is passed to FileConnection
     * 
     * @return ContentBrowseItem if a package or collection, null otherwise
     */
    public ContentBrowseItem getContentItemFromDir(String curSubDir, String actDir, Image defaultContentIcon) throws Exception{
        int type = -1;
        Image icon = defaultContentIcon;
        String title = null;
        String itemFullPath = null;
        
        //String actDir = dirContents.nextElement().toString();
        if(!actDir.endsWith("/")) {
            actDir += '/';
        }
        String dirFullPath = "file://localhost" + SEP_STR + curSubDir
                + SEP_STR + actDir;
        String fullPath = dirFullPath + "exetoc.xml";
        String colPath = dirFullPath + "execollection.xml";

        FileConnection dirCon = (FileConnection)Connector.open(dirFullPath);
        boolean hasEXEPkg = false;
        boolean hasEXECol = false;
        boolean hasIcon = false;
        if(dirCon.isDirectory()) {
            hasEXEPkg = dirCon.list("exetoc.xml", false).hasMoreElements();
            hasEXECol = dirCon.list("execollection.xml", false).hasMoreElements();
            hasIcon = dirCon.list("mplayicon.png", false).hasMoreElements();
        }
        dirCon.close();

        String iconPath = dirFullPath + "mplayicon.png";

        if(hasEXEPkg) {                       
            itemFullPath = dirFullPath;
            type = TYPE_NORM;
            title = actDir.substring(0, actDir.length()-1);
        }else if(hasEXECol) {
            type = TYPE_COL;
            //String collectionId = MLearnUtils.getCollectionID(dirFullPath);
            //title = MLearnUtils.getCollectionTitle(dirFullPath);
            title = "Testing";
            itemFullPath = dirFullPath;
        }

        if(type != -1) {
            if(hasIcon) {
                try {
                    FileConnection iconFileCon = (FileConnection)Connector.open(iconPath);
                    icon = Image.createImage(iconFileCon.openInputStream());
                    iconFileCon.close();
                }catch(IOException e2) {
                    e2.printStackTrace();
                }
            }

            ContentBrowseItem thisItem = new ContentBrowseItem(dirFullPath, type, title, icon);
            return thisItem;
        }
        
        return null;
    }

    /**
     * The main meat of the class is here - go through the file system roots 
     * as per the class documentation.  
     */
    public Vector makeForm() {
        Enumeration e = FileSystemRegistry.listRoots();
        String dirName = null;
        Vector menuItems = new Vector();

        Image defaultContentIcon = null;
        try {
            defaultContentIcon = Image.createImage(
                getClass().getResourceAsStream("/icons/default-content.png"));
        }catch(IOException e2) {
            e2.printStackTrace();
            defaultContentIcon = Image.createImage(22, 22, 0xf00);
        }
        
        //holds both the root and ustadMobileContent
        Vector rootsAndSubs = new Vector();
        while(e.hasMoreElements()) {
            String thisRoot = e.nextElement().toString();
            rootsAndSubs.addElement(thisRoot);
            rootsAndSubs.addElement(FileUtils.joinPath(thisRoot, 
                    "ustadmobileContent"));
        }
        
        e = rootsAndSubs.elements();
        
        Vector itemVector = new Vector();
        
        try {
            while(e.hasMoreElements()) {
                //find folders
                String curSubDir = e.nextElement().toString();
                
                //TODO: Fix ugly hack to avoid nokia bug?
                if(curSubDir.toLowerCase().indexOf("c:") != -1) {
                    continue;
                }
                
                String fileConDir = FileUtils.joinPath("file://localhost", curSubDir);
      
                FileConnection subCon = (FileConnection)Connector.open(fileConDir); 
                
                boolean dirExists = subCon.isDirectory();
                try {
                    if(curSubDir.endsWith("ustadmobileContent") && !dirExists) {
                            subCon.mkdir();
                    }
                }catch(Exception err) {
                    //oh shit!
                    System.out.println("something went wrong..");
                }
                
                dirExists = subCon.isDirectory();
                
                if(!dirExists) {
                    subCon.close();
                    subCon = null;
                    continue;//nothing to do here - it does not actually exist
                }
                Enumeration dirContents = subCon.list();
                
                Vector subDirVector = new Vector();
                //copy the enumeration + close the actual connection
                while(dirContents.hasMoreElements()) {
                    subDirVector.addElement(dirContents.nextElement());
                }
                subCon.close();
                subCon = null;
                
                
                
                dirContents = subDirVector.elements();
                
                while(dirContents.hasMoreElements()) {
                    String actDir = dirContents.nextElement().toString();
                    ContentBrowseItem thisItem = getContentItemFromDir(curSubDir, actDir, defaultContentIcon);
                    if(thisItem != null) {
                        noCourse_Flag = "FALSE";
                        itemVector.addElement(thisItem);
                    }
                }
                if (noCourse_Flag =="TRUE"){
                    return null;
                }
                return itemVector;
                
            }
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
