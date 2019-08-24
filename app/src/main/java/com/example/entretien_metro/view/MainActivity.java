package com.example.entretien_metro.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.entretien_metro.R;
import com.example.entretien_metro.route.Route;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity {
    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt = (Button) findViewById(R.id.bt1);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                StringRequest postRequest = new StringRequest(Request.Method.POST, Route.URL_LOGIN,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // response
                                Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
                                if (response.equals("the name is Safa")) {
                                    Toast.makeText(MainActivity.this, "Good", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(MainActivity.this, "Bad", Toast.LENGTH_LONG).show();

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
                                // error
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("login", "SafaMiri");
                        params.put("password","azerty");
                        return params;
                    }
                };
                queue.add(postRequest);
            }
        });
    }
}

