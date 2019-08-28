package com.example.app_mobile.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;
import com.example.app_mobile.R;
import com.example.app_mobile.jakson.JSON1;
import com.example.app_mobile.jakson.JSON2;
import com.example.app_mobile.jakson.Main;
import com.example.app_mobile.route.Route;
import com.example.app_mobile.storage.Storage;

import org.codehaus.jackson.map.ObjectMapper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Navigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{
    Button bt;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Intent i=getIntent();
        if(Storage.LoginValue==null)
        {
            Storage.LoginValue= i.getStringExtra("value1");
        }


        /*
        bt=(Button)findViewById(R.id.btValider);
        bt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    Main m = new Main();
                    Toast.makeText(Navigation.this,Route.URL_ENTRETIRN,Toast.LENGTH_SHORT).show();
                    Toast.makeText(Navigation.this, m.getJSON_Entretien(Route.URL_ENTRETIRN,Navigation.LoginValue, Navigation.this), Toast.LENGTH_SHORT).show();
                    list=m.getListEntretien();
                    for(int i=0 ; i<list.size() ; i++)
                    {
                        Toast.makeText(Navigation.this,list.get(i).toString(),Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(Navigation.this,"The value of login est "+Navigation.LoginValue,Toast.LENGTH_SHORT).show();
                    Intent i =new Intent(Navigation.this,Notification.class);

                    startActivity(i);
                }
                catch (Exception e)
                {

                }
            }
        });
        */

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera)
        {
            try
            {
                Main m = new Main();
                m.getJSON_Entretien(Route.URL_ENTRETIRN,Storage.LoginValue, Navigation.this);
                Storage.list=m.getListEntretien();
                Intent i =new Intent(Navigation.this,Notification.class);
                startActivity(i);
            }
            catch (Exception e)
            {

            }
            // Handle the camera action
        }
        else
            if (id == R.id.nav_gallery)
            {
                try
                {
                    Main m = new Main();
                    m.getJSON_CARRFOUR(Route.URL_CARREFOUR,Storage.LoginValue, Navigation.this);
                    Storage.list1=m.getListCarrefour();
                    Intent i =new Intent(Navigation.this,Search.class);
                    startActivity(i);
                }
                catch (Exception e)
                {

                }
            }
            else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
