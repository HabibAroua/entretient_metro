package com.example.app_mobile.view;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.app_mobile.R;

public class TestMail extends AppCompatActivity {

    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_mail);
        bt=(Button)findViewById(R.id.btSendMail);
        bt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("message/rfc822");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"habib.aroua@sesame.com.tn"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Email subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message text");
                emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("content://path/attachment"));
                startActivity(emailIntent);
            }
        });
    }
}
