/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import javax.print.DocFlavor.URL;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import static javafx.css.StyleOrigin.USER_AGENT;
import javax.net.ssl.HttpsURLConnection;


/**
 *
 * @author carlo
 */
public class Prueba {

    /**
     * @param args the command line arguments
     */
    private final String USER_AGENT = "Mozilla/5.0";
    private final int usuario = 147;
    
    public static void main(String[] args) throws ProtocolException, IOException, Exception {
       /* PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        DocPrintJob job = null;
        System.out.println("Number of print services: " + printServices.length);
        for(int i=0;i<printServices.length;i++){
            System.out.println(printServices[i].getName());
        }*/

        HttpURLConnectionExample http = new HttpURLConnectionExample();

        //System.out.println("Testing 1 - Send Http GET request");
        //http.sendGet();
        new vista().setVisible(true);
		
    }
	
	
}

    
    

