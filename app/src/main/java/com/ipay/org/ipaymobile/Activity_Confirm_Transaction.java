package com.ipay.org.ipaymobile;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Activity_Confirm_Transaction extends AppCompatActivity {

    TextView tc1, tc2, tc3, tc4;
    String dtrans_acc_name, dtrans_acc_no, dtrans_acc_ifsc, dtrans_amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__confirm__transaction);

        tc1 = (TextView)findViewById(R.id.tcnam);
        tc2 = (TextView)findViewById(R.id.tcacc);
        tc3 = (TextView)findViewById(R.id.tcifs);
        tc4 = (TextView)findViewById(R.id.tcamount);
        tc1.setText(Activity_Transfer.tname);
        tc2.setText(Activity_Transfer.taccno);
        tc3.setText(Activity_Transfer.tifsc);
        tc4.setText(Activity_Transfer.tamount);
    }

    public void cancel_trans(View view){
        Intent i7 = new Intent(Activity_Confirm_Transaction.this, Activity_Transfer.class);
        startActivity(i7);
    }

    public void do_transfer(View view){
        dtrans_acc_name = tc1.getText().toString();
        dtrans_acc_no = tc2.getText().toString();
        dtrans_acc_ifsc = tc3.getText().toString();
        dtrans_amount = tc4.getText().toString();

        Transfer_Amount tdo = new Transfer_Amount();
        tdo.execute(getString(R.string.transfer_money_api));
    }

    public class Transfer_Amount extends AsyncTask<String, String, String>{

        ProgressDialog pdtr;
        String rt;
        @Override
        protected String doInBackground(String... strings) {
            URL turl ;
            HttpURLConnection thturcon;
            try {
                turl = new URL(strings[0]);
                thturcon = (HttpURLConnection)turl.openConnection();
                InputStream inst = thturcon.getInputStream();
                BufferedReader brr = new BufferedReader(new InputStreamReader(inst));
                StringBuilder ssbb = new StringBuilder();
                while((rt=brr.readLine())!=null){
                    ssbb.append(rt);
                }
                System.out.println("ReceivedData():"+ssbb);
                JSONObject jrs = new JSONObject(ssbb.toString());
                String trs_result = jrs.getString("transfer_result");
                return trs_result;
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
                pdtr = new ProgressDialog(Activity_Confirm_Transaction.this);
                pdtr.setIndeterminate(true);
                pdtr.setTitle("Transferring Amount");
                pdtr.setMessage("Please wait........");
                pdtr.show();
        }

        @Override
        protected void onPostExecute(String s) {
            pdtr.dismiss();
            AlertDialog.Builder trrs = new AlertDialog.Builder(Activity_Confirm_Transaction.this);
            trrs.setTitle("Transfer result");
            if(s.equals("successfull")){
                trrs.setMessage("Successfull");
            }else {
                trrs.setMessage("Failed");
            }
            trrs.setPositiveButton("Home", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent ih = new Intent(Activity_Confirm_Transaction.this, Activity_Home.class);
                    startActivity(ih);
                }
            });
            trrs.show();

        }
    }
}
