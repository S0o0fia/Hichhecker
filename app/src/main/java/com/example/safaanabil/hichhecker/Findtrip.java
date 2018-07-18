package com.example.safaanabil.hichhecker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Findtrip extends AppCompatActivity {


    final String url = "http://18.219.152.157/api/v1/trip/search";
    EditText date;
    Boolean status;
    String date1, price1, phone1;
    int id, from_city, to_city, hicker_id;
    Spinner tocity, formcity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findtrip);

        date = (EditText) findViewById(R.id.datevalue2);
        tocity=(Spinner)findViewById(R.id.spinner2);
        formcity=(Spinner)findViewById(R.id.spinner4);

    }




    public void search(View view) {

        SharedPreferences sharedPreferences = getSharedPreferences("trip", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("date",date.getText().

                toString() );
        editor.putInt("from_city", formcity.getSelectedItemPosition());
        editor.putInt("to_city",tocity.getSelectedItemPosition());
        editor.apply();
        Intent inte = new Intent(getApplicationContext(), Alltrip.class);
        startActivity(inte);
    }
}



