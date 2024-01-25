package edu.escuelaing.arem.ASE.app;

import java.net.*;

public class URLParser {
    public static void main(String[] args) throws MalformedURLException {
        URL myURL = new URL(
                "https://campusvirtual.escuelaing.edu.co:2709/moodle/pluginfile.php/222974/mod_resource/content/0/?NamesNetworkClientService.pdf#EJERCICIO1");
        System.out.println("Protocol: " + myURL.getProtocol());
        System.out.println("Auth: " + myURL.getAuthority());
        System.out.println("Host: " + myURL.getHost());
        System.out.println("Port: " + myURL.getPort());
        System.out.println("Path: " + myURL.getPath());
        System.out.println("Query: " + myURL.getQuery());
        System.out.println("File: " + myURL.getFile());
        System.out.println("Ref: " + myURL.getRef());
    }
}
