package edu.escuelaing.arem.ASE.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;

public class RestAPIFacade {

    private static final String API = "http://www.omdbapi.com/?i=tt3896198&apikey=6754e60&t=";
    private static MovieAPI movieSearcher = new MovieAPI();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(Integer.parseInt(env.PORT.getValue()));
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        boolean running = true;
        while (running) {
            Socket clientSocket = null;

            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
            String inputLine, outputLine;
            URL urlWithTitle = null;

            while ((inputLine = in.readLine()) != null) {
                if (inputLine.contains("Referer")) {
                    String[] url = inputLine.split(" ");
                    urlWithTitle = new URL(url[1]);
                }
                if (!in.ready()) {
                    break;
                }
            }

            // Search the movie with the API
            String movieData = null;
            String movieTitle = null;
            try {
                movieTitle = urlWithTitle.getQuery();
                System.out.println(movieTitle);
                movieData = movieSearcher.queryMovie(movieTitle);
                System.out.println(movieData);
            } catch (NullPointerException nullE) {
                System.out.println("Error");
            }

            outputLine = "HTTP/1.1 200 OK\r\n"
                    + "Content-Type:text/html; charset=ISO-8859-1\r\n"
                    + "\r\n"
                    + "<!DOCTYPE html>"
                    + "<html>"
                    + "<head>"
                    + "<meta charset=\"UTF-8\">"
                    + "<title>Title of the document</title>\n"
                    + "</head>"
                    + "<body>"
                    + "My Web Site"
                    + movieSearcher.queryMovie(movieTitle)
                    + "</body>"
                    + "</html>";
            out.println(outputLine);

            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }
}