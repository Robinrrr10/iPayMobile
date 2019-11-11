package com.ipay.org.ipaymobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

public class My_Account extends AppCompatActivity {

    TextView t1, t2, t3, t4, t5, t6, t7, t8, t9, t10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__account);

               t1 = (TextView)findViewById(R.id.accno);
        t1.setText("ACCOUNT NO:");
        t2 = (TextView)findViewById(R.id.accname);
        t2.setText("ACCOUNT NAME:");
        t3 = (TextView)findViewById(R.id.bal);
        t3.setText("Balance Amount Available:");
        t4 = (TextView)findViewById(R.id.ifsc);
        t4.setText("IFSC Code:");
        t5 = (TextView)findViewById(R.id.imps);
        t5.setText("IMPS Code:");
        t6 = (TextView)findViewById(R.id.email);
        t6.setText("Email:");
        t7 = (TextView)findViewById(R.id.mobile);
        t7.setText("Mobile:");
        t8 = (TextView)findViewById(R.id.noloan);
        t8.setText("no. of Loans:");
        t9 = (TextView)findViewById(R.id.adh);
        t9.setText("Adhaar No:");
        t10 = (TextView)findViewById(R.id.pan);
        t10.setText("Pan No:");

    }
}
