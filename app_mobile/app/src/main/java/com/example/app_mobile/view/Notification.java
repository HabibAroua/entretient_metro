package com.example.app_mobile.view;

import android.content.ClipData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.example.app_mobile.jakson.JSON1;
import com.example.app_mobile.mailing.GMailSender;
import com.example.app_mobile.mailing.SendEmail;

import java.util.ArrayList;

public class Notification extends AppCompatActivity
{
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        final   ArrayList<JSON1> Items=new  ArrayList<JSON1> ();
        for(int i=0 ;i<Navigation.list.size() ; i++)
        {
            Items.add(Navigation.list.get(i));
        }
        final MyCustomAdapter myadpter= new MyCustomAdapter(Items);

        ListView ls=(ListView)findViewById(R.id.list);
        ls.setAdapter(myadpter);
    }


    class MyCustomAdapter extends BaseAdapter
    {
        ArrayList<JSON1> Items=new ArrayList<JSON1>();
        MyCustomAdapter(ArrayList<JSON1> Items ) {
            this.Items=Items;

        }


        @Override
        public int getCount() {
            return Items.size();
        }

        @Override
        public String getItem(int position) {
            return Items.get(position).getId();

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

            final TextView txtID =(TextView) view1.findViewById(R.id.txtID);
            final TextView txtNom_Carrefour =(TextView) view1.findViewById(R.id.txtNom_Carrefour);
            final TextView txtAnnee =(TextView) view1.findViewById(R.id.txtAnnee);
            final TextView txtMois =(TextView) view1.findViewById(R.id.txtMois);
            final TextView txtSemaine =(TextView) view1.findViewById(R.id.txtSemaine);
            txtID.setText(Items.get(i).getId());
            txtNom_Carrefour.setText(Items.get(i).getNom_carrefour());
            txtAnnee.setText(Items.get(i).getAnnee());
            txtMois.setText(Items.get(i).getMois());
            txtSemaine.setText(Items.get(i).getSemaine());

            bt=(Button)view1.findViewById(R.id.btTest);

            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "Hello " + txtID.getText().toString(), Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < Navigation.list.size(); i++) {
                        Toast.makeText(getApplicationContext(), "The value is " + Navigation.list.get(i), Toast.LENGTH_SHORT).show();
                    }

                    try {
                        GMailSender sender = new GMailSender("habibha.aroua82@gmail.com",
                                "habib.aroua@hotmail.framour88");
                        sender.sendMail("Hello from JavaMail", "Body from JavaMail",
                                "habibha.aroua82@gmail.com", "habib.aroua@sesame.com.tn");
                        Toast.makeText(Notification.this, "Mail sent", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        //Log.e("SendMail", e.getMessage(), e);
                        Toast.makeText(Notification.this, "Error : " + e.getMessage(), Toast.LENGTH_SHORT).show();


                    }
                }


                });
            return view1;

        }
    }
}