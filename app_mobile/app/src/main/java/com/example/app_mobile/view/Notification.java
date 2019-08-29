package com.example.app_mobile.view;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
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
import com.example.app_mobile.mailing.Config;
import com.example.app_mobile.storage.Storage;
import java.util.ArrayList;

public class Notification extends AppCompatActivity
{
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        try
        {
            final ArrayList<JSON1> Items = new ArrayList<JSON1>();
            for (int i = 0; i < Storage.list.size(); i++)
            {
                Items.add(Storage.list.get(i));
            }
            final MyCustomAdapter myadpter = new MyCustomAdapter(Items);

            ListView ls = (ListView) findViewById(R.id.list);
            ls.setAdapter(myadpter);
        }
        catch (Exception e)
        {
            Toast.makeText(Notification.this,"please try again",Toast.LENGTH_SHORT).show();
            Intent i=new Intent(Notification.this,Navigation.class);
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
        public int getCount() {
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
            View view1=linflater.inflate(R.layout.item, null);
            final TextView txtIDSE=(TextView)view1.findViewById(R.id.txtIDSE);
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
            txtIDSE.setText(Items.get(i).getIdEn_Se());

            bt=(Button)view1.findViewById(R.id.btTest);

            bt.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.setType("message/rfc822");
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{Config.EMAIL});
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Email subject");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message text");
                    emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("content://path/attachment"));
                    startActivity(emailIntent);
                }
                });
            return view1;

        }
    }
}