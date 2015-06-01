/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ustadmobile.app;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
//import java.io.OutputStream;
import java.util.Hashtable;

/**
 *
 * @author varuna
 */
public class SerializedHashtable {

    public SerializedHashtable() {
    }
    
    /* Convert an input stream (could be from file, RMS, HTTP, etc)
     * to a hash table. 
     * eg: To update the default settings of the app.
     */
    public static Hashtable streamToHashtable(InputStream inputStream){
         
        return null;
    }
    
    /* Convert a hashtable to an outputstream (that can be used to make a file,
     * update RMS, http, etc)
     * eg: To Update the set app settings to RMS.
     */
    public InputStream hashTabletoStream(Hashtable hashTable){
        
        //InputStream is = getClass().getResourceAsStream();
        return null;
    }
    
}
