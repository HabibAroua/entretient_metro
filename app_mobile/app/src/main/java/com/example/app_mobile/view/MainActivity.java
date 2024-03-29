package com.example.app_mobile.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_mobile.R;
import com.example.app_mobile.route.Route;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity
{
    Button bt;
    EditText txtLogin , txtPassword;
    private String res="";
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtLogin=(EditText)findViewById(R.id.txtLogin);
        txtPassword=(EditText)findViewById(R.id.txtPassword);
        bt = (Button) findViewById(R.id.bt1);
        bt.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                if((txtLogin.getText().toString().equals("")) && (txtPassword.getText().equals("")))
                {
                    txtLogin.setError("Login vide");
                    txtPassword.setError(("Password vide"));
                }
                else
                {
                    if(txtLogin.getText().toString().equals(""))
                    {
                        txtLogin.setError("Login vide");
                    }
                    else
                    {
                        if(txtPassword.getText().toString().equals(""))
                        {
                            txtPassword.setError("Password vide");
                        }
                        else
                        {
                            progressDialog = ProgressDialog.show(MainActivity.this
                                    ,"En train de connecter","S'il vous plaît, attendez...",false,false);

                            RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                            StringRequest postRequest = new StringRequest(Request.Method.POST, Route.URL_LOGIN,
                                    new Response.Listener<String>()
                                    {
                                        @Override
                                        public void onResponse(String response)
                                        {
                                            switch (response.charAt(1))
                                            {
                                                case '1' : progressDialog.dismiss(); Toast.makeText(MainActivity.this,"Utilisateur n'existe pas",Toast.LENGTH_SHORT).show();

                                                    break;
                                                case '2' : progressDialog.dismiss(); Intent i =new Intent(MainActivity.this,Navigation.class);
                                                            i.putExtra("value1",txtLogin.getText().toString());
                                                           startActivity(i);
                                                    break;
                                                case '3' : progressDialog.dismiss(); Toast.makeText(MainActivity.this,"Mot de passe inccorect",Toast.LENGTH_SHORT).show();
                                                        break;
                                            }

                                        }
                                    },
                                    new Response.ErrorListener()
                                    {
                                        @Override
                                        public void onErrorResponse(VolleyError error)
                                        {
                                            Toast.makeText(MainActivity.this, "Le serveur est desactivé", Toast.LENGTH_LONG).show();
                                            //progressDialog.dismiss();

                                        }
                                    }
                            )
                            {
                                @Override
                                protected Map<String, String> getParams()
                                {
                                    Map<String, String> params = new HashMap<String, String>();
                                    params.put("login", txtLogin.getText().toString());
                                    params.put("password",txtPassword.getText().toString());
                                    return params;
                                }
                            };
                            queue.add(postRequest);
                            progressDialog.dismiss();
                        }
                    }
                }
            }
        });
    }
}