package com.example.safaanabil.hichhecker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.*;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddTrip extends AppCompatActivity {

    EditText date,fromcity,tocity,price,phone;
    Button add;
    int user_id;
    Boolean status;
    String url="http://18.219.152.157/api/v1/trip/add";
    String phone1,date1,price1;
    int id;
    Spinner tcity , fcity;
    String [] city = {"city" , "assuit" ,"cairo" ,"mina"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);
        date=(EditText)findViewById(R.id.editdate);
       phone=(EditText)findViewById(R.id.phone_num);
        price=(EditText)findViewById(R.id.price_num);
        add=(Button)findViewById(R.id.button);
        tcity = (Spinner) findViewById(R.id.spinner2);
        fcity = (Spinner) findViewById(R.id.spinner4);
        SharedPreferences sharedPreferences=getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        user_id=sharedPreferences.getInt("id",0);

    }
    public void addd(View view)
    {

       // Toast.makeText(AddTrip.this ,  ,Toast.LENGTH_LONG).show();
        Map<String, String> params = new HashMap<>();
        params.put("date",date.getText().toString() );//
        params.put("price",price.getText().toString() );//
        params.put("phone",phone.getText().toString() );//
        params.put("user_id","3");//String.valueOf(user_id)
        //   Toast.makeText(login.this, "clicker", Toast.LENGTH_SHORT).show();



        params.put("to_id",tcity.getSelectedItemPosition()+"" );//String.valueOf(tcity.getSelectedItemId())
        params.put("from_id",fcity.getSelectedItemPosition()+"" );//String.valueOf(fcity.getSelectedItemId())

        RequestQueue queue = Volley.newRequestQueue(AddTrip.this);
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Display the response string.

                        try {


                            status = response.getBoolean("status");

                            if (status) {
                                Toast.makeText(AddTrip.this, "username and password is correct", Toast.LENGTH_SHORT).show();
                             //   Intent inte = new Intent(getApplicationContext(), profile_account.class);
                               // startActivity(inte);

                            } else {
                                //Toast.makeText(AddTrip.this, "username and password is false", Toast.LENGTH_SHORT).show();
                            }
                            JSONObject heroArray = response.getJSONObject("data");
                            phone1 = heroArray.getString("phone");
                            price1 = heroArray.getString("price");
                            date1 = heroArray.getString("date");
                            id = heroArray.getInt("id");
                            Toast.makeText(AddTrip.this, "" + phone1 + "    " + price1 + "   " + id+"  "+date1, Toast.LENGTH_SHORT).show();
                            //SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                            //SharedPreferences.Editor editor = sharedPreferences.edit();
                            //editor.putString("token", token);
                            //editor.putString("user_name", name);
                            //editor.putString("user_email", username.getText().toString());
                            //editor.putInt("user_id", id);
                            //Toast.makeText(login.this, ""+account_num.getText(), Toast.LENGTH_SHORT).show();
                            //editor.putInt("account_num", Integer.parseInt(account_num.getText().toString()));

                            //editor.apply();

                            //date.setText("");
                           // phone.setText("");
                           // price.setText("");
                           // fromcity.setText("");
                           // tocity.setText("");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //_response.setText("That didn't work!");
                Toast.makeText(AddTrip.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }


        );

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    }


