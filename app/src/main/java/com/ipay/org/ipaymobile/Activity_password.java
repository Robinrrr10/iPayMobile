package com.ipay.org.ipaymobile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Activity_password extends AppCompatActivity {

    static String pass;
    static String acc_no, name;
    EditText ps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        TextView tv1 = (TextView)findViewById(R.id.givenEmail);
        tv1.setText(MainActivity.userid);

    }

    public void psignin(View view){
        //Api to check user name and password from server
        //Have to send the api with email, password and session id received from previous email
        ps = (EditText)findViewById(R.id.password);
        pass = ps.getText().toString();
        System.out.println("-------------------------->Given password"+pass);

        String login_api = getString(R.string.login_api);
        Login algn = new Login();
        algn.execute(login_api);

        //will get the name and account no
       // acc_no = "1000110001";
       // name = "Robin R";


    }

    public class Login extends AsyncTask<String, String, String>{
        ProgressDialog pgd, pgd2;
        String result;

        @Override
        protected String doInBackground(String... strings) {
            URL url;
            HttpURLConnection hturlcon;
            try {

                url = new URL(strings[0]);
                hturlcon = (HttpURLConnection)url.openConnection();
                InputStream ins = hturlcon.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(ins));
                StringBuilder sbb = new StringBuilder();
                while((result=br.readLine())!=null){
                    sbb.append(result);
                }
                Thread.sleep(500);
                JSONObject jsb = new JSONObject(sbb.toString());
                String login_continue = jsb.getString("login_status");
                acc_no = jsb.getString("account_no");
                name = jsb.getString("account_name");
                return login_continue;
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            pgd = new ProgressDialog(Activity_password.this);
            pgd.setIndeterminate(true);
            pgd.setTitle("Please wait..");
            pgd.setMessage("Checking user detail..");
            pgd.show();
        }

        @Override
        protected void onPostExecute(String s) {
            if(s.equals("successfull")){
                pgd.dismiss();
                pgd2 = new ProgressDialog(Activity_password.this);
                pgd2.setIndeterminate(true);
                pgd2.setTitle("Login successfull");
                pgd2.setMessage("Loading.....");
                pgd2.show();
                Intent i2 = new Intent(Activity_password.this, Activity_Home.class);
                startActivity(i2);
            }else{
                pgd.dismiss();
                Toast.makeText(getApplicationContext(), "Invalid password. Give correct password", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
