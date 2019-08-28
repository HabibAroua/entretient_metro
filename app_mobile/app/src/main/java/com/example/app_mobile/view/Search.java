package com.example.app_mobile.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.app_mobile.R;
import com.example.app_mobile.storage.Storage;

import java.util.ArrayList;
import java.util.List;

public class Search extends AppCompatActivity
{
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        try
        {
            ArrayList<String> c = new ArrayList<>();
            for (int i = 0; i < Storage.list1.size(); i++)
            {
                c.add(Storage.list1.get(i).getNom());
            }
            Spinner spinner = findViewById(R.id.spinnerCar);
            ArrayAdapter<String> adapter =
                    new ArrayAdapter<String>(Search.this, android.R.layout.simple_spinner_dropdown_item, c);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
        }
        catch (Exception e)
        {
            Toast.makeText(Search.this,"please try again",Toast.LENGTH_SHORT).show();
            Intent i=new Intent(Search.this,Navigation.class);
            startActivity(i);
        }
        bt=(Button)findViewById(R.id.btSearch);

    }

}
