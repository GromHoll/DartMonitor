package edu.nntu.dart.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class UrlDataLoader implements DataLoader {

    private static final String EMPTY_STRING = "";
    private static final String NEW_LINE = "\n";

    private static final int CONNECT_TIMEOUT = 5000;
    private static final int READ_TIMEOUT    = 30000;

    private final String urlAddress;

    private boolean serverAvailable = false;

    private BufferedReader in;

    public UrlDataLoader(String urlAddress) {
        this.urlAddress = urlAddress;
        testConnection();
    }

    @Override
    public String getData() {
        connect();
        return isAvailable() ? readData() : EMPTY_STRING;
    }

    @Override
    public boolean isAvailable() {
        return serverAvailable;
    }

    private void testConnection() {
        getData();
    }

    private void connect() {
        try {
            URL url = new URL(urlAddress);
            URLConnection connection = url.openConnection();
            connection.setConnectTimeout(CONNECT_TIMEOUT);
            connection.setReadTimeout(READ_TIMEOUT);
            in = new BufferedReader(new InputStreamReader(url.openStream()));
            serverAvailable = true;
        } catch(IOException exc) {
            serverAvailable = false;
            exc.printStackTrace();
            close();
        }
    }

    private void close() {
        try {
            in.close();
        } catch(Exception exc) {
            exc.printStackTrace();
        }
    }

    private String readData() {

        String tempLine;
        StringBuilder sb = new StringBuilder(EMPTY_STRING);
        try {
            while ((tempLine = in.readLine()) != null) {
                sb.append(tempLine);
                sb.append(NEW_LINE);
            }
        } catch(IOException exc) {
            exc.printStackTrace();
        } finally {
            close();
        }
        return sb.toString();
    }
}
