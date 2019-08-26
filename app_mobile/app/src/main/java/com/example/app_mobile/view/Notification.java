package com.example.app_mobile.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_mobile.R;

import java.util.ArrayList;

public class Notification extends AppCompatActivity
{
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        final   ArrayList<Planete> Items=new  ArrayList<Planete> ();
        Items.add(new Planete("p1",500));
        Items.add(new Planete("p2",158));
        Items.add(new Planete("p3",500));
        Items.add(new Planete("p4",500));
        final MyCustomAdapter myadpter= new MyCustomAdapter(Items);

        ListView ls=(ListView)findViewById(R.id.list);
        ls.setAdapter(myadpter);
        //ls.setOnItemClickListener(new AdapterView.OnItemClickListener()
        //{
          //  @Override
            //public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              //  TextView txtname =(TextView) view.findViewById(R.id.txtNomP);
               // TextView txtdes =(TextView) view.findViewById(R.id.txtDistance);
               // Toast.makeText(getApplicationContext(),txtname.getText(),Toast.LENGTH_LONG).show();
               // myadpter.notifyDataSetChanged();

            //}
        //});


    }


    class MyCustomAdapter extends BaseAdapter
    {
        ArrayList<Planete> Items=new ArrayList<Planete>();
        MyCustomAdapter(ArrayList<Planete> Items ) {
            this.Items=Items;

        }


        @Override
        public int getCount() {
            return Items.size();
        }

        @Override
        public String getItem(int position) {
            return Items.get(position).mNom;

        }

        @Override
        public long getItemId(int position) {
            return  position;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup)
        {
            LayoutInflater linflater =getLayoutInflater();
            View view1=linflater.inflate(R.layout.item, null);

            final TextView txtname =(TextView) view1.findViewById(R.id.txtNomP);
            txtname.setText(Items.get(i).mNom);
            bt=(Button)view1.findViewById(R.id.btTest);

            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Toast.makeText(getApplicationContext(),"Hello "+txtname.getText().toString(),Toast.LENGTH_SHORT).show();
                }
            });
            return view1;

        }
    }
}