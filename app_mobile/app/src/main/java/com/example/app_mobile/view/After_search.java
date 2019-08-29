package com.example.app_mobile.view;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_mobile.R;
import com.example.app_mobile.jakson.JSON1;
import com.example.app_mobile.mailing.Config;
import com.example.app_mobile.route.Route;
import com.example.app_mobile.storage.Storage;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
            ls.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
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
            ArrayList<String>types=new ArrayList<>();
            types.add("Entretien preventif");
            types.add("Entretien aiguille");
            final TextView txtIDSE=(TextView)view1.findViewById(R.id.txtIDSE1);
            final TextView txtID =(TextView) view1.findViewById(R.id.txtID1);
            final TextView txtNom_Carrefour =(TextView) view1.findViewById(R.id.txtNom_Carrefour1);
            final TextView txtAnnee =(TextView) view1.findViewById(R.id.txtAnnee1);
            final TextView txtMois =(TextView) view1.findViewById(R.id.txtMois1);
            final TextView txtSemaine =(TextView) view1.findViewById(R.id.txtSemaine1);
            final Spinner spinner=(Spinner)view1.findViewById(R.id.spinnerType1);
            final EditText txtDescription=(EditText)view1.findViewById(R.id.editTextDescription1);
            txtID.setText(Items.get(i).getId());
            txtNom_Carrefour.setText(Items.get(i).getNom_carrefour());
            txtAnnee.setText(Items.get(i).getAnnee());
            txtMois.setText(Items.get(i).getMois());
            txtSemaine.setText(Items.get(i).getSemaine());
            txtIDSE.setText(Items.get(i).getIdEn_Se());
            ArrayAdapter<String> adapter =
                    new ArrayAdapter<String>(After_search.this, android.R.layout.simple_spinner_dropdown_item, types);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            bt=(Button)view1.findViewById(R.id.btTest1);

            bt.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if(txtDescription.getText().equals(""))
                    {
                        txtDescription.setError("Description vide");
                    }
                    else
                    {
                        RequestQueue queue = Volley.newRequestQueue(After_search.this);
                        StringRequest postRequest = new StringRequest(Request.Method.POST, Route.URL_EFFECTUE,
                                new Response.Listener<String>()
                                {
                                    @Override
                                    public void onResponse(String response)
                                    {
                                        Date d=new Date();
                                        Toast.makeText(After_search.this, response, Toast.LENGTH_SHORT).show();
                                        Intent emailIntent = new Intent(Intent.ACTION_SEND);
                                        emailIntent.setType("message/rfc822");
                                        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{Config.EMAIL});
                                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Information sur entretien");
                                        emailIntent.putExtra
                                                (
                                                        Intent.EXTRA_TEXT,
                                                        "Carrefour : "+txtNom_Carrefour.getText().toString()+"\n" +
                                                                "Type d'entretien : "+spinner.getSelectedItem().toString()+"\n"+
                                                                "Description : "+txtDescription.getText().toString()+"\n" +
                                                                "Date : "+d.toString()+""
                                                );
                                        emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("content://path/attachment"));
                                        startActivity(emailIntent);
                                        finish();
                                    }
                                },
                                new Response.ErrorListener()
                                {
                                    @Override
                                    public void onErrorResponse(VolleyError error)
                                    {
                                        Toast.makeText(After_search.this, "Try again", Toast.LENGTH_LONG).show();
                                        //progressDialog.dismiss();

                                    }
                                }
                        ) {
                            @Override
                            protected Map<String, String> getParams()
                            {
                                Map<String, String> params = new HashMap<String, String>();
                                params.put("id", txtIDSE.getText().toString());
                                return params;
                            }
                        };
                        queue.add(postRequest);
                    }

                }
            });
            return view1;

        }
    }
}