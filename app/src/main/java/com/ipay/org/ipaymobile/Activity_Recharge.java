package com.ipay.org.ipaymobile;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.zip.Inflater;

public class Activity_Recharge extends AppCompatActivity {

    int[] imgid = { R.drawable.cell_phone, R.drawable.dth, R.drawable.electricity, R.drawable.internet, R.drawable.travel, R.drawable.gas};

    String rech[] = {"Mobile", "DTH", "Electricity", "Broadband", "Travel", "Gas"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__recharge);



        GridView gvrech = (GridView)findViewById(R.id.grid_recharge);
        ArrayAdapter<String> grechadp = new ArrayAdapter<String>(this, R.layout.list_text, rech);
        gvrech.setAdapter(grechadp);



    }


}
