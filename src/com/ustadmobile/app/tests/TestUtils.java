/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ustadmobile.app.tests;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
//import org.kxml2.io.KXmlParser;
//import org.xmlpull.v1.XmlPullParser;
//import org.xmlpull.v1.XmlPullParserException;
import com.ustadmobile.app.controller.UstadMobileAppController;
import javax.microedition.io.file.FileConnection;

/**
 *
 * @author varuna
 */
public class TestUtils {
    
    public static Hashtable testSettings;
    
    public static String loadTestSettingsFile() throws Exception{
        //load from the file
        String appDataURI = UstadMobileAppController.setupAppDataDir();
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
                return str;
            }
        return null;
    }
    
    /*
    public static void loadTestSettings() throws Exception{
        //load from the file
        String appDataURI = UstadMobileAppController.setupAppDataDir();
        String settingsDataURI = appDataURI +"/test-settings.xml";
        FileConnection fCon = (FileConnection)Connector.open(settingsDataURI,
            Connector.READ);
        InputStream is = fCon.openInputStream();
        XmlPullParser xpp = parseXml(is);
        testSettings = new Hashtable();
        testSettings.put("appDataURI", appDataURI);
        int evtType = 0;
        //skip over root element tag
        xpp.nextTag();
        
        do {
            evtType = xpp.next();
            if(evtType == XmlPullParser.START_TAG) {
                String key = xpp.getName();
                String value = xpp.nextText();
                testSettings.put(key, value);
            }
        }while(evtType != XmlPullParser.END_DOCUMENT);
        
        is.close();
        fCon.close();
        
    }*/
    
    public static InputStream getFileBytes(String fileURI) 
            throws IOException{
        FileConnection fCon = (FileConnection)Connector.open(fileURI,
            Connector.READ);
        InputStream is = fCon.openInputStream();
        return is;
    }
    
    public static ByteArrayInputStream getHTTPBytes(String url) throws IOException{
        HttpConnection c = null;
        InputStream is = null;
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        c = (HttpConnection) Connector.open(
                url, Connector.READ, true);
        c.setRequestMethod(HttpConnection.GET); //Get method
        
        
        if (c.getResponseCode() != HttpConnection.HTTP_OK) {
            throw new IOException("Request to " + url + " is not HTTP 200 OK");
        }

        is = c.openInputStream();
        byte[] buf = new byte[1024];
        int bytesRead = -1;
        while ((bytesRead = is.read(buf)) != -1) {
            bout.write(buf, 0, bytesRead);
        }
        is.close();
        is = null;

        ByteArrayInputStream bais = new ByteArrayInputStream(
                bout.toByteArray());

        bout.close();
        return bais;
    }
    
    /*
    public static XmlPullParser parseXml(InputStream is) throws XmlPullParserException, IOException{
        KXmlParser parser = new KXmlParser();
        parser.setInput(is, "utf-8");
        return parser;
        
    }*/
}
