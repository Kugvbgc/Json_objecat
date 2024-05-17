package com.khair.json_objecat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button buttonlode;
    ProgressBar progressBar;
    TextView tvName,tvMobile,tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonlode=findViewById(R.id.buttonlode);
        progressBar=findViewById(R.id.progressBar);
        tvName=findViewById(R.id.tvName);
        tvMobile=findViewById(R.id.tvMobile);
        tvEmail=findViewById(R.id.tvEmail);


        buttonlode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String url = "https://abulk77912.000webhostapp.com/apps/data.json";

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                               progressBar.setVisibility(View.GONE);

                                try {
                                    JSONObject jsonObject=new JSONObject(response);
                                   String mobile=jsonObject.getString("mobile");
                                    String email=jsonObject.getString("eamil");
                                    String name=jsonObject.getString("name");

                                    tvName.setText(name);
                                    tvEmail.setText(email);
                                    tvMobile.setText(mobile);

                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                       progressBar.setVisibility(View.GONE);
                       buttonlode.setText(""+error);
                    }
                });


                queue.add(stringRequest);
            }
        });

    }
}