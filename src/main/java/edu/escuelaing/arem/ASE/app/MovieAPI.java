package edu.escuelaing.arem.ASE.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class MovieAPI {
    private static final String API = env.API.getValue();
    private Map<String, String> cache = new HashMap<>();

    public MovieAPI() {
        // Empty constructor for the movie api
    }

    public String queryMovie(String movieTitle) {
        String movie = "";

        if (cache.containsKey(movieTitle)) {
            movie = cache.get(movieTitle);
            return movie;
        }

        try {
            URL apiToQuery = new URL(API + movieTitle);
            URLConnection apiConnection = apiToQuery.openConnection();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(apiConnection.getInputStream()))) {
                String inputLine = null;
                while ((inputLine = reader.readLine()) != null) {
                    movie = inputLine;
                    cache.put(movieTitle, movie);
                }
            } catch (IOException x) {
                System.err.println(x);
            }
        } catch (IOException ioe) {
            System.err.println("Could not connect to API Server.");
            movie = "Unable to retrieve data from the API... Please try again.";
        }

        return movie;
    }
}
