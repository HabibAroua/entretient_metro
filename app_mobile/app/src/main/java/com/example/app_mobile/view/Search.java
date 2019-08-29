package com.example.app_mobile.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.app_mobile.R;
import com.example.app_mobile.jakson.JSON1;
import com.example.app_mobile.jakson.Main;
import com.example.app_mobile.route.Route;
import com.example.app_mobile.storage.Storage;
import java.util.ArrayList;

public class Search extends AppCompatActivity
{
    Button bt;
    Spinner spinner;
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
            spinner = findViewById(R.id.spinnerCar);
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
        bt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Toast.makeText(Search.this,"The value is "+spinner.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
                Toast.makeText(Search.this,"The first char is "+spinner.getSelectedItem().toString().charAt(0),Toast.LENGTH_SHORT).show();
                try
                {
                    Main m = new Main();
                    m.getJSON_CARRFOUR(Route.URL_CARREFOUR,Storage.LoginValue, Search.this);
                    Storage.list=m.getListEntretien();
                    Storage.list2=new ArrayList<JSON1>();
                    for(int i=0 ; i<Storage.list.size() ; i++)
                    {
                        if(spinner.getSelectedItem().toString().equals(Storage.list.get(i).getNom_carrefour()))
                        {
                            Storage.list2.add(Storage.list.get(i));
                        }
                    }
                    Intent i =new Intent(Search.this,After_search.class);
                    startActivity(i);
                }
                catch (Exception e)
                {
                    System.out.println("Error : "+e.getMessage());
                    finish();

                }
            }
        });
    }

}