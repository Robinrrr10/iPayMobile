package com.ipay.org.ipaymobile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Activity_Transfer extends AppCompatActivity {





  static  String[] hj;
    String bens;
    String acc=null, namee=null, ifsc=null;
    TextView t11, t12, t13;
    EditText e1, e2, e3;
    Button bsav;
    ArrayAdapter spadp;
    Spinner selBen;
    public static String tname, taccno, tifsc, tamount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__transfer);



         t11 = (TextView)findViewById(R.id.textView7);
         t12 = (TextView)findViewById(R.id.textView11);
         t13 = (TextView)findViewById(R.id.textView13);

        t11.setVisibility(View.INVISIBLE);
        t12.setVisibility(View.INVISIBLE);
        t13.setVisibility(View.INVISIBLE);

        e1 = (EditText)findViewById(R.id.textView10);
        e2 = (EditText)findViewById(R.id.textView12);
        e3 = (EditText)findViewById(R.id.textView14);
        e1.setVisibility(View.INVISIBLE);
        e2.setVisibility(View.INVISIBLE);
        e3.setVisibility(View.INVISIBLE);

        bsav = (Button)findViewById(R.id.buttosav);
        bsav.setVisibility(View.INVISIBLE);



        selBen = (Spinner)findViewById(R.id.spinBeni);
      //  ArrayAdapter spadp = new ArrayAdapter(this, R.layout.list_text, a);
          spadp = new ArrayAdapter(this, R.layout.list_text);

        GetBens n = new GetBens();
        n.execute(getString(R.string.get_all_ben_api));




        spadp.add("SELECT");
        spadp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);



        selBen.setAdapter(spadp);
        selBen.setSelection(spadp.getCount()-1);
        selBen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(selBen.getSelectedItem().equals("SELECT")){
                    acc=null;
                    namee=null;
                    ifsc=null;
                    t11.setVisibility(View.INVISIBLE);
                    t12.setVisibility(View.INVISIBLE);
                    t13.setVisibility(View.INVISIBLE);
                    e1.setVisibility(View.INVISIBLE);
                    e2.setVisibility(View.INVISIBLE);
                    e3.setVisibility(View.INVISIBLE);
                }else{
                    Toast.makeText(getApplicationContext(),"you have selected"+selBen.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                    //String clicked = selBen.getSelectedItem().toString();
                    //Send api to get benificary
                    GetBenificaryDetail gb = new GetBenificaryDetail();
                    gb.execute(getString(R.string.get_ben_api));




                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button create_new_ben = (Button)findViewById(R.id.but_trans_conf);
        create_new_ben.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bsav.setVisibility(View.VISIBLE);
                selBen.setVisibility(View.INVISIBLE);
                acc=null;
                namee=null;
                ifsc=null;
                e1.setText(acc);
                e2.setText(namee);
                e3.setText(ifsc);
                e1.setEnabled(true);
                e2.setEnabled(true);
                e3.setEnabled(true);
                t11.setVisibility(View.VISIBLE);
                t12.setVisibility(View.VISIBLE);
                t13.setVisibility(View.VISIBLE);
                e1.setVisibility(View.VISIBLE);
                e2.setVisibility(View.VISIBLE);
                e3.setVisibility(View.VISIBLE);
            }
        });

    }

    public class GetBenificaryDetail extends AsyncTask<String, String, String>{

        ProgressDialog ppd;
        String rr;
        @Override
        protected String doInBackground(String... strings) {
            URL ur;
            HttpURLConnection htuc;
            try {
                ur = new URL(strings[0]);
                htuc = (HttpURLConnection)ur.openConnection();
                InputStream iss = htuc.getInputStream();
                BufferedReader brr = new BufferedReader(new InputStreamReader(iss));
                StringBuilder sbb = new StringBuilder();
                while((rr=brr.readLine())!=null){
                    sbb.append(rr);
                }
                //Thread.sleep(1000);
                JSONObject jjb = new JSONObject(sbb.toString());
                String ben = jjb.getString("get_ben_status");
                namee = jjb.getString("ben_name");
                acc = jjb.getString("ben_acc");
                ifsc = jjb.getString("ben_ifs");
                System.out.println("---------------------------->"+namee+acc+ifsc);
                return ben;
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            ppd = new ProgressDialog(Activity_Transfer.this);
            ppd.setTitle("Please wait...");
            ppd.setMessage("Getting benfisary account details....");
            ppd.show();
        }

        @Override
        protected void onPostExecute(String s) {
            if(s.equals("true")) {
                ppd.dismiss();
                e1.setEnabled(false);
                e2.setEnabled(false);
                e3.setEnabled(false);
                e1.setText(namee);
                e2.setText(acc);
                e3.setText(ifsc);
                t11.setVisibility(View.VISIBLE);
                t12.setVisibility(View.VISIBLE);
                t13.setVisibility(View.VISIBLE);
                e1.setVisibility(View.VISIBLE);
                e2.setVisibility(View.VISIBLE);
                e3.setVisibility(View.VISIBLE);
            }else{
                Toast.makeText(getApplicationContext(),"Unable to get benificary detail", Toast.LENGTH_SHORT).show();

            }

        }
    }







    public class GetBens extends AsyncTask<String, String, String>{

       // String[] hj;
        ProgressDialog bb;
        String ru ;
        @Override
        protected String doInBackground(String... strings) {
            URL ul;
            HttpURLConnection ht;
            try{
                ul = new URL(strings[0]);
                ht = (HttpURLConnection)ul.openConnection();
                InputStream ii = ht.getInputStream();
                BufferedReader bb = new BufferedReader(new InputStreamReader(ii));
                StringBuilder sn = new StringBuilder();
                while((ru=bb.readLine())!=null){
                    sn.append(ru);
                }
                JSONObject nn = new JSONObject(sn.toString());
                bens = nn.getString("bens");
                System.out.println("=================================>"+bens);
                JSONArray bls = nn.getJSONArray("bens");
                String bjk = bls.getString(0);

                hj = new String[bls.length()];
                        for(int i=0;i<bls.length();i++){
                            hj[i] = bls.getString(i);
                                    System.out.println("-------------------------"+hj[i]);
                        }
                //bls.getString(0);
               // System.out.println("mmmmmmmmmmmmmmmmmmmmmmmmmm"+nnl[5]);
                spadp.addAll(hj);    //This will assign all benifisary to the adapter of the spinner
                return "";
            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPreExecute() {
            bb = new ProgressDialog(Activity_Transfer.this);
            bb.setIndeterminate(true);
            bb.setTitle("Please wait...");
            bb.setMessage("Getting all benifisaries....");
            bb.show();
        }

        @Override
        protected void onPostExecute(String s) {
            bb.dismiss();

        }
    }

    public void confirmTrans (View view){
        EditText te1 = (EditText)findViewById(R.id.textView10);
        EditText te2 = (EditText)findViewById(R.id.textView12);
        EditText te3 = (EditText)findViewById(R.id.textView14);
        EditText te4 = (EditText)findViewById(R.id.tamount);


        tname = te1.getText().toString();
        taccno = te2.getText().toString();
        tifsc = te3.getText().toString();
        tamount = te4.getText().toString();

        Intent id = new Intent(Activity_Transfer.this, Activity_Confirm_Transaction.class);
        startActivity(id);
    }

    public void dissav(View view){
        Intent i6 = new Intent(Activity_Transfer.this, Activity_Transfer.class);
        startActivity(i6);
    }
}
