package Requests;

import java.net.*;
import java.io.*;
import java.util.Properties;
import java.util.concurrent.TimeUnit;             

public class OpaqueRequests {
        public static void makeRequest(String urlRequest){
                
                try {
                        URL google = new URL(urlRequest);
                        InputStream is = google.openStream();
                        is.close();
                        String requestOutput = "Request to " + urlRequest + " successful";
                        System.out.println(requestOutput);
                } catch (MalformedURLException ex) {
                        System.out.println("MalformedURLException");
                        System.exit(0);
                } catch (IOException ex) {
                        System.out.println("IOException");
                        System.exit(0);
                } 

        }
        public static void main (String[] args) {
                while(true){
                        try {
                                Properties prop = new Properties();
                                String fileName = "requests.conf";
                                InputStream configis = null;

                                configis = new FileInputStream(fileName);
                                prop.load(configis);

                                String requestURL = prop.getProperty("REQUEST_URL");
                              
                                makeRequest(requestURL);

                                TimeUnit.SECONDS.sleep(3);
                        } catch (MalformedURLException ex) {
                                System.out.println("MalformedURLException");
                                System.exit(0);
                        } catch (IOException ex) {
                                System.out.println("IOException");
                                System.exit(0);
                        } catch (InterruptedException ex) {
                                System.out.println("InterruptedException");
                                System.exit(0);
                        }
                }
        }

}
