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
import org.kxml2.io.KXmlParser;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import com.ustadmobile.app.controller.UstadMobileAppController;
import java.io.OutputStream;
import java.util.Enumeration;
import javax.microedition.io.file.FileConnection;

/**
 *
 * @author varuna
 */
public class TestUtils {
    
    public static Hashtable testSettings;
    
    private static TestUtils mainInstance;
    
    public static TestUtils getInstance() {
        if(mainInstance == null) {
            mainInstance = new TestUtils();
        }
        
        return mainInstance;
    }
    
    
    public void loadTestSettingsResource() throws Exception {
        InputStream is = getClass().getResourceAsStream(
                "/com/ustadmobile/app/tests/test-settings.xml");
        XmlPullParser xpp = parseXml(is);
        testSettings = new Hashtable();
        String appDataURI = UstadMobileAppController.getAppDataDir();
        testSettings.put("appDataURI", appDataURI);
        int evtType = 0;
        //skip over root element tag
        xpp.nextTag();
        
        do {
            evtType = xpp.next();
            if(evtType == XmlPullParser.START_TAG) {
                String key = xpp.getName();
                String value = xpp.nextText();
                System.out.println(key +":"+value);
                testSettings.put(key, value);
            }
        }while(evtType != XmlPullParser.END_DOCUMENT);
        
        is.close();
    }
    
    public static void loadTestSettingsFile() throws Exception{
        //load from the file
        String appDataURI = UstadMobileAppController.getAppDataDir();
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
    
    public static XmlPullParser parseXml(InputStream is) throws 
            XmlPullParserException, IOException{
        KXmlParser parser = new KXmlParser();
        parser.setInput(is, "utf-8");
        return parser;
    }
    
    /***  After setup, attributes of the HttpConnection object can be retrieved 
     * using various get methods.
    ***/
    public static void getConnectionInformation(HttpConnection hc) {

        System.out.println("Request Method for this connection is " + 
                hc.getRequestMethod());
        System.out.println("URL in this connection is " + hc.getURL());
        System.out.println("Protocol for this connection is " +
                hc.getProtocol()); // It better be HTTP:)
        System.out.println("This object is connected to " + hc.getHost() + 
                " host");
        System.out.println("HTTP Port in use is " + hc.getPort());
        System.out.println("Query parameter in this request are  " +
                hc.getQuery());

    }
    
    public static InputStream getFileBytes(String fileURI) 
            throws IOException{
        FileConnection fCon = (FileConnection)Connector.open(fileURI,
            Connector.READ);
        InputStream is = fCon.openInputStream();
        return is;
    }

    
    public static String sendPost(String url, Hashtable optionalParameters) 
            throws IOException {
        HttpConnection httpConn = null;
        InputStream is = null;
        OutputStream os = null;
        StringBuffer sb = new StringBuffer();
        
        if(url == null){ //Testing..
            url = "http://54.77.18.106:8621/";
            
        }

        try {
            
            // Open an HTTP Connection object
            httpConn = (HttpConnection)Connector.open(url);
            // Setup HTTP Request to POST
            httpConn.setRequestMethod(HttpConnection.POST);

            httpConn.setRequestProperty("User-Agent",
              "Profile/MIDP-1.0 Confirguration/CLDC-1.0");
            httpConn.setRequestProperty("Accept_Language","en-US");
            //Content-Type is must to pass parameters in POST Request
            httpConn.setRequestProperty("Content-Type", 
                    "application/x-www-form-urlencoded");
            
            String params = null;
            Enumeration keys = optionalParameters.keys();
            String key, value;
            boolean firstAmp = true;
            while(keys.hasMoreElements()) {
                    key = keys.nextElement().toString();
                    value = optionalParameters.get(key).toString();
                    if (firstAmp){
                        params = key + "=" + value;
                        firstAmp=false;
                    }else{
                        params = params + "&"+ key + "=" + value;
                    }
            }
            
            //Content-Length to be set
            httpConn.setRequestProperty("Content-length", 
                    String.valueOf(params.getBytes().length));
            
            // This function retrieves the information of this connection
            getConnectionInformation(httpConn);

            os = httpConn.openOutputStream();

            os.write(params.getBytes());

            /**Caution: os.flush() is controversial. It may create unexpected 
                behavior on certain mobile devices. 
                * Try it out for your mobile device **/
            

            //os.flush();
            //os.close();

            // Read Response from the Server

            int response_code=httpConn.getResponseCode();  
            if(response_code==HttpConnection.HTTP_OK){  
                sb.append("Success");
            }  

        } catch(IOException e){  
            sb.append("Network Problem : " + e.getMessage()); 
        }finally{
            if(is!=null){  
                try {  
                    is.close();  
                } catch (IOException ex) {  
                    ex.printStackTrace();  
                }  
            }  
            if(os!=null){  
                try {  
                    os.close();  
                } catch (IOException ex) {  
                    ex.printStackTrace();  
                }  
            }  
        }
        return sb.toString();

    }
}
