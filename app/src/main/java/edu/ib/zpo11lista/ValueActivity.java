package edu.ib.zpo11lista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.audiofx.Equalizer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Value activity class calculating value of Polynomial object
 */
public class ValueActivity extends AppCompatActivity {
    public static final String EQUATION_EXTRA="equation";

    /**
     * Method onCreate
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value);
    }

    /**
     * Method calculating value of Polynomial expression
     * @param view
     */
    public void calculateOnClick(View view) {
        Intent intent=getIntent();

        Polynomial p=(Polynomial) intent.getSerializableExtra(EQUATION_EXTRA);

        EditText input=(EditText) findViewById(R.id.etxtInputX);
        TextView out=(TextView) findViewById(R.id.txtOutput);

        StringBuilder response =new StringBuilder();
        String url= "https://newton.now.sh/api/v2/simplify/"+p.replaceXWithValue(input.getText().toString());
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