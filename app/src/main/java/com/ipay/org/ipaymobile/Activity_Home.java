package com.ipay.org.ipaymobile;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Activity_Home extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__home);

        TextView tvh = (TextView)findViewById(R.id.namehome);
        tvh.setText("Name:  "+Activity_password.name);
        TextView tvp = (TextView)findViewById(R.id.acchome);
        tvp.setText("Account no:  "+Activity_password.acc_no);

        ListView lh = (ListView)findViewById(R.id.listhome);

        ArrayList<String> arls = new ArrayList<String>();
        arls.add("My Account");
        arls.add("Transfer Money");
        arls.add("Recharge");
        arls.add("Account Statement");
        arls.add("Loan");
        arls.add("Offers");
       // ArrayAdapter<String> adp = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arls);
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, R.layout.list_text, arls);

        lh.setAdapter(adp);
        lh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                    {
                        Intent i7 = new Intent(Activity_Home.this, My_Account.class);
                        startActivity(i7);
                        break;
                    }
                    case 1:
                    {
                        Intent i8 = new Intent(Activity_Home.this, Activity_Transfer.class);
                        startActivity(i8);
                        break;
                    }
                    case 2:
                    {
                        Intent i9 = new Intent(Activity_Home.this, Activity_Recharge.class);
                        startActivity(i9);
                        break;
                    }
                }
            }
        });




    }


    public void logout(View view){
        //call an api to logout and to delete the session and time id
        Intent i7 = new Intent(Activity_Home.this, MainActivity.class);
        startActivity(i7);
    }


}
