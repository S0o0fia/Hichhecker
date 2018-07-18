package com.example.safaanabil.hichhecker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.*;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Alltrip extends AppCompatActivity {

    String date1,price1,phone1;
    int user_id,from_city,to_city,hicker_id;
    Boolean status;
    String url="http://18.219.152.157/api/v1/trip/search";
    RecyclerView recyclerView;
    TripAdapter adapter;
    String [] cities = { "City","Assuit" , "Cairo" , "Mina"};
    ArrayList<Trip> data = new ArrayList<>();
    LinearLayout datarequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


            SharedPreferences sharedPreferences = getSharedPreferences("trip", Context.MODE_PRIVATE);
            from_city = sharedPreferences.getInt("from_city", 0);
            to_city = sharedPreferences.getInt("to_city", 0);
            date1 = sharedPreferences.getString("date", "");


        Map<String, String> params = new HashMap<>();
        params.put("date", date1);
        params.put("from_id",String.valueOf(from_city) );
        params.put("to_id",String.valueOf(to_city) );

        //Toast.makeText(login.this, ""+account_num.getText(), Toast.LENGTH_SHORT).show();
        //editor.putInt("account_num", Integer.parseInt(account_num.getText().toString()));


        // params.put("user_id",String.valueOf(user_id));
        //   Toast.makeText(login.this, "clicker", Toast.LENGTH_SHORT).show();

        RequestQueue queue = Volley.newRequestQueue(Alltrip.this);
        JsonObjectRequest stringRequest = new JsonObjectRequest(com.android.volley.Request.Method.POST, url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Display the response string.

                        try {
                           // Toast.makeText(Alltrip.this , response.toString() , Toast.LENGTH_LONG).show();


                            status = response.getBoolean("status");

                            if (status) {
                               // Toast.makeText(Alltrip.this, "username and password is correct", Toast.LENGTH_SHORT).show();
                                JSONArray heroArray = response.getJSONArray("data");
                                for(int i=0;i<heroArray.length();i++)
                                {
                                   // Toast.makeText(Alltrip.this, "ualllllllll", Toast.LENGTH_SHORT).show();
                                    JSONObject heroObject=heroArray.getJSONObject(i);
                                    phone1 = heroObject.getString("phone");
                                    price1 = heroObject.getString("price");
                                    date1 = heroObject.getString("date");
                                    user_id = heroObject.getInt("id");
                                    from_city = heroObject.getInt("from_id");
                                    to_city = heroObject.getInt("to_id");
                                    hicker_id = heroObject.getInt("user_id");
                                    data.add(new Trip(phone1 ,price1 ,date1 ,cities[from_city] ,cities[to_city] ,"11 A.M"));




                                }

                               // Trip newreip = new Trip(phone ,price1 ,date1 ,cities[to_city] ,cities[from_city] ,"11:00A.M");




                                recyclerView = (RecyclerView) findViewById(R.id.list);
                                adapter =new TripAdapter(data , Alltrip.this);
                                recyclerView.setAdapter(adapter);
                                RecyclerView.LayoutManager layout = new GridLayoutManager(Alltrip.this , 1);
                                recyclerView.setLayoutManager(layout);


                            } else {
                                Toast.makeText(Alltrip.this, "Your result of Search is not found", Toast.LENGTH_LONG).show();
                            }



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
                            //phone.setText("");
                            //price.setText("");
                            //fromcity.setText("");
                            //tocity.setText("");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //_response.setText("That didn't work!");
                Toast.makeText(Alltrip.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        datarequest = (LinearLayout) findViewById(R.id.datarequest);


    }


 public  void requestdata (View view)
 {
      if(view.getId() == R.id.datarequest) {
          Intent i = new Intent(Alltrip.this, Request.class);


          i.putExtra("price", price1);
          i.putExtra("tocity", cities[to_city]);
          i.putExtra("fcity", cities[from_city]);
          int tid = to_city ;
          int fid = from_city ;
          int hid = hicker_id ;
          int uid = user_id;
          i.putExtra("from_id",fid+"");
          i.putExtra("to_id" , tid+"");
          i.putExtra("name", hid+"");
          i.putExtra("uid" ,uid+"");
           startActivity(i);


          SharedPreferences sharedPreferences = getSharedPreferences("fromandto", Context.MODE_PRIVATE);
          SharedPreferences.Editor editor = sharedPreferences.edit();
          editor.putInt("from_id", from_city);
          editor.putInt("to_id", to_city);
          editor.putInt("hicker_id", hicker_id);
          //Toast.makeText(Alltrip.this , to_city+" "+from_city+"" , Toast.LENGTH_LONG).show();
          //editor.putInt("user_id", id);
          //Toast.makeText(login.this, ""+account_num.getText(), Toast.LENGTH_SHORT).show();
          //editor.putInt("account_num", Integer.parseInt(account_num.getText().toString()));

          editor.apply();
      }

 }

    }

