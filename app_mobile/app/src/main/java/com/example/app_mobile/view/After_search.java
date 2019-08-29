package com.example.app_mobile.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.app_mobile.R;
import com.example.app_mobile.jakson.JSON1;
import com.example.app_mobile.storage.Storage;
import java.util.ArrayList;

public class After_search extends Activity
{
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_search);
        try
        {
            final ArrayList<JSON1> Items = new ArrayList<JSON1>();
            for (int i = 0; i < Storage.list2.size(); i++)
            {
                Items.add(Storage.list2.get(i));
            }
            final MyCustomAdapter myadpter = new MyCustomAdapter(Items);

            ListView ls = (ListView) findViewById(R.id.list1);
            ls.setAdapter(myadpter);
        }
        catch (Exception e)
        {
            Toast.makeText(After_search.this,"please try again",Toast.LENGTH_SHORT).show();
            Intent i=new Intent(After_search.this,Navigation.class);
            startActivity(i);
        }
    }


    class MyCustomAdapter extends BaseAdapter
    {
        ArrayList<JSON1> Items=new ArrayList<JSON1>();
        MyCustomAdapter(ArrayList<JSON1> Items )
        {
            this.Items=Items;
        }


        @Override
        public int getCount()
        {
            return Items.size();
        }

        @Override
        public String getItem(int position)
        {
            return Items.get(position).getId();
        }

        @Override
        public long getItemId(int position)
        {
            return  position;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup)
        {
            LayoutInflater linflater =getLayoutInflater();
            View view1=linflater.inflate(R.layout.item1, null);
            final TextView txtIDSE=(TextView)view1.findViewById(R.id.txtIDSE1);
            final TextView txtID =(TextView) view1.findViewById(R.id.txtID1);
            final TextView txtNom_Carrefour =(TextView) view1.findViewById(R.id.txtNom_Carrefour1);
            final TextView txtAnnee =(TextView) view1.findViewById(R.id.txtAnnee1);
            final TextView txtMois =(TextView) view1.findViewById(R.id.txtMois1);
            final TextView txtSemaine =(TextView) view1.findViewById(R.id.txtSemaine1);
            txtID.setText(Items.get(i).getId());
            txtNom_Carrefour.setText(Items.get(i).getNom_carrefour());
            txtAnnee.setText(Items.get(i).getAnnee());
            txtMois.setText(Items.get(i).getMois());
            txtSemaine.setText(Items.get(i).getSemaine());
            txtIDSE.setText(Items.get(i).getIdEn_Se());
            bt=(Button)view1.findViewById(R.id.btTest1);

            bt.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Toast.makeText(getApplicationContext(), "Hello " + txtID.getText().toString(), Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < Storage.list.size(); i++)
                    {
                        Toast.makeText(getApplicationContext(), "The value is " + Storage.list.get(i), Toast.LENGTH_SHORT).show();
                    }
                }
            });
            return view1;

        }
    }
}