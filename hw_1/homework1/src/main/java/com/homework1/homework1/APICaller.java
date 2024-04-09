package com.homework1.homework1;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;


public class APICaller {

    private Map<String, Object> currencies;

    private static final Logger logger = LoggerFactory.getLogger(APICaller.class);

    public APICaller() throws UnsupportedOperationException {
        // Call API
    }

    public void setCurrencies(Map<String, Object> currencies) {
        this.currencies = currencies;
    }

    public Map<String, Object> getCurrencies() {
        return currencies;
    }
    

    public void call() throws IOException {

            // Create URL object with the API endpoint
            URL url = new URL("https://api.frankfurter.app/latest");

            // Open connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set request method
            connection.setRequestMethod("GET");

            // Read response
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Parse JSON response
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> jsonResponse = mapper.readValue(response.toString(), Map.class);
            Map<String, Object> rates = (Map<String, Object>) jsonResponse.get("rates");

            // Set rates to currencies
            setCurrencies(rates);

            // Print response
            logger.info("Response from API: {}", jsonResponse);

            // Close connection
            connection.disconnect();
    }

}