package com.ipay.org.ipaymobile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    static String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    public void afterUserId(View view){
        //Here it should send API with this user id, App id, and get client id to verify in server
        //Api should send with app id, client id, email, session_id
        EditText usid = (EditText)findViewById(R.id.user_id);
        userid = usid.getText().toString();
        String expuserid = "robinrrr10";  //API should send the user id
        //we should receive session id, and one continue true or false

        String email_api = getString(R.string.email_api);
        CheckEmail getuserid = new CheckEmail();
        getuserid.execute(email_api);


    }

    public class CheckEmail extends AsyncTask<String, String, String> {

        ProgressDialog pd;
        String rs;
        @Override
        protected String doInBackground(String... strings) {
            URL url;
            HttpURLConnection htcon;
            try{
                url = new URL(strings[0]);
                htcon = (HttpURLConnection)url.openConnection();
                htcon.setRequestMethod("GET");
                htcon.setRequestProperty("app_id", "one");
                htcon.setRequestProperty("app_id_key", "two");
                InputStream is = htcon.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                StringBuilder sbd = new StringBuilder();

                while((rs=br.readLine())!=null){
                    sbd.append(rs);
                }
                Thread.sleep(1000);
                JSONObject jo = new JSONObject(sbd.toString());
                String user_continue = jo.getString("user_id_login_continue");

                return user_continue;
            }catch(Exception e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            pd = new ProgressDialog(MainActivity.this);
            pd.setIndeterminate(true);
            pd.setTitle("Please wait..");
            pd.setMessage("Checking user id...");
            pd.show();
        }

        @Override
        protected void onPostExecute(String s) {
            if(s.equals("true")){
                pd.dismiss();
                Intent i5 = new Intent(MainActivity.this, Activity_password.class);
                startActivity(i5);
            }
            else{
                pd.dismiss();
                Toast.makeText(getApplicationContext(), userid+" is Invalid user Id. Please enter correct user id", Toast.LENGTH_LONG).show();
            }
        }
    }

}
