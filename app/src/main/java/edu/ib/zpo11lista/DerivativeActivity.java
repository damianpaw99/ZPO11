package edu.ib.zpo11lista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.URL;
import java.util.Map;

public class DerivativeActivity extends AppCompatActivity {

    public static final String EQUATION_EXTRA="equation";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_derivative);
        Intent intent=getIntent();
        Polynomial p=(Polynomial) intent.getSerializableExtra(EQUATION_EXTRA);

        TextView out=(TextView) findViewById(R.id.txtDerivative);

        StringBuilder response =new StringBuilder();
        String url= "https://newton.now.sh/api/v2/derive/"+p.toString();

        //StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        //StrictMode.setThreadPolicy(policy);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    URL obj = new URL(url);
                    HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
                    connection.setRequestMethod("GET");
                    int responseCode = connection.getResponseCode();
                    System.out.println("Response "+ responseCode);
                    BufferedReader in =new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String inputLine;
                    while ((inputLine = in.readLine())!=null) response.append(inputLine);
                    in.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Gson gson=new Gson();
        System.out.println(response.toString());
        Map m=gson.fromJson(response.toString(),Map.class);
        String result=m.get("result").toString();
        out.setText(result);
    }
}