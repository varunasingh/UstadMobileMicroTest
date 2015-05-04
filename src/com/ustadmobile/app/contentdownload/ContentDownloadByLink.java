/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ustadmobile.app.contentdownload;

import com.ustadmobile.app.opds.UstadJSOPDSEntry;
import java.util.Vector;

/**
 *
 * @author varuna
 */
public class ContentDownloadByLink {
    
     public void downloadLink(UstadJSOPDSEntry entry, String mimeType){
         //Validate link
         //Simplify Course name
         String courseName = entry.title;
         String destFolder = "file://localhost/root1/ustadmobileContent/"+courseName;
         Vector courseLinks = entry.getAcquisitionLinks(mimeType);
         String link = (String) courseLinks.elementAt(0);
         
         
     }
    
}
