package org.mavenproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        URL url=null;
        HttpURLConnection connection = null;
        int rspon=0;
        String urlString = "https://api.nationalize.io/?name=nathaniel";

        try {
            url=new URL(urlString);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        try {
            connection=(HttpURLConnection)url.openConnection();
            rspon=connection.getResponseCode();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(rspon==200){
            BufferedReader rawdata=null;
            try {
                rawdata= new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            StringBuilder data=new StringBuilder();
            String readLine = null;

            while(true)
            {
                try {
                    if((readLine = rawdata.readLine()) == null) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                data.append(readLine);
            }
            String[] datas=data.toString().split(",");
            Arrays.stream(datas).forEach(dat-> System.out.println(dat));

        }

    }
}