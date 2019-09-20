package com.example.imp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class start_screen extends AppCompatActivity {
    Boolean can;
    String status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        final Button keys;
        final Button exit;
        final ImageView imageView;
        final EditText editText;
        can = true;
        keys = findViewById(R.id.key);
        imageView = findViewById(R.id.status_img);
        editText = findViewById(R.id.status);
        exit = findViewById(R.id.end);


        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(start_screen.this, Main2Activity.class);
                start_screen.this.startActivity(intent);
            }
        });

        keys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (can) {
                    editText.setText("Unlocked");
                    imageView.setImageResource(R.drawable.ul);
                    can = false;
                    status = "unlock1";
                    try {
                        call_me(status);
                        Toast.makeText(start_screen.this, "successful", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(start_screen.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(start_screen.this, "sucessful", Toast.LENGTH_SHORT).show();
                    editText.setText("Locked");
                    imageView.setImageResource(R.drawable.lock);
                    can = true;
                    status = "lock1";
                    try {
                        call_me(status);
                        Toast.makeText(start_screen.this, "successful", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(start_screen.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }




            }
        });
    }

    public void call_me(String data) throws Exception {
        final OkHttpClient client = new OkHttpClient();

        final Request request = new Request.Builder()
                .url("https://bernard.fun/QSLock/sender.php?command="+data)
                .get()
                .addHeader("User-Agent", "PostmanRuntime/7.11.0")
                .addHeader("Accept", "*/*")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Host", "bernard.fun")
                .addHeader("accept-encoding", "gzip, deflate")
                .addHeader("Connection", "keep-alive")
                .addHeader("cache-control", "no-cache")
                .build();


        new Thread(new Runnable(){
            @Override
            public void run() {

                try {
                    Response response = client.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
