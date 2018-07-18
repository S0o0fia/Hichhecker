package com.example.safaanabil.hichhecker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.*;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Track extends AppCompatActivity {

    String token,name,price;
    int from_id,to_id,id;
    Boolean status;
    String url="http://18.219.152.157/api/v1/request/pending";
    String accepturl="http://18.219.152.157/api/v1/request/accept";
    String rejecturl="http://18.219.152.157/api/v1/request/reject";
     Resultinfo resultinfo;
    RecyclerView recyclerView;
    ResultAdapter adapter;
    String [] cities = { "City","Assuit" , "Cairo" , "Mina"};
    ArrayList<Resultinfo> data = new ArrayList<>();

    Button accept , reject ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);


        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");



        RequestQueue queue = Volley.newRequestQueue(Track.this);
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, new JSONObject(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Display the response string.

                        try {


                            status = response.getBoolean("status");

                            if (status) {
                                //Toast.makeText(Track.this, "username and password is correct", Toast.LENGTH_SHORT).show();
                                JSONArray heroArray = response.getJSONArray("data");
                                for(int i=0;i<heroArray.length();i++)
                                {
                                   // Toast.makeText(Track.this, "ualllllllll", Toast.LENGTH_SHORT).show();
                                    JSONObject heroObject=heroArray.getJSONObject(i);

                                    price = heroObject.getString("price");
                                    name = heroObject.getString("name");
                                    id=heroObject.getInt("id");

                                    from_id = heroObject.getInt("from_id");
                                    to_id = heroObject.getInt("to_id");
                                    //Toast.makeText(Track.this, "" + price + "    " + name +   ""+from_id+"   "+to_id, Toast.LENGTH_SHORT).show();
                                    data.add(new Resultinfo(name , price , cities[from_id] , cities[to_id] ));



                                }



                               // data.add(new Resultinfo("admin2" , "30 L.E" , "Cairo" , "Assuit" ));
                                recyclerView = (RecyclerView) findViewById(R.id.list);
                                adapter =new ResultAdapter(data , Track.this);
                                recyclerView.setAdapter(adapter);
                                RecyclerView.LayoutManager layout = new GridLayoutManager(Track.this , 1);
                                recyclerView.setLayoutManager(layout);


                            } else {
                                Toast.makeText(Track.this, "There is no request for you", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(Track.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override

            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer " + token);
                //params.put("Accept-Language", "fr");

                return params;
            }

        };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);




    }
    //function for accept
public void accept(View view) {
    if (view.getId() == R.id.accept) {
        startActivity(new Intent(Track.this , Contract.class));

        Toast.makeText(Track.this,"Request Accepted", Toast.LENGTH_SHORT).show();

        Map<String, String> params = new HashMap<>();
        //params.put("Authorization","Bearer " +token);
        params.put("id", String.valueOf(id));



        //editor.putInt("account_num", Integer.parseInt(account_num.getText().toString()));


        // params.put("user_id",String.valueOf(user_id));
        //   Toast.makeText(login.this, "clicker", Toast.LENGTH_SHORT).show();

        RequestQueue queue = Volley.newRequestQueue(Track.this);
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, accepturl, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Display the response string.

                        try {


                            status = response.getBoolean("status");

                            if (status) {
                                //Toast.makeText(Track.this, "username and password is correct", Toast.LENGTH_SHORT).show();


                            } else {
                               // Toast.makeText(Track.this, "username and password is false", Toast.LENGTH_SHORT).show();
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
               // Toast.makeText(Track.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override

            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer " + token);
                //params.put("Accept-Language", "fr");

                return params;
            }
        };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
//function of reject
public void reject(View view) {
    if (view.getId() == R.id.reject) {
        Toast.makeText(Track.this, "Request Reject", Toast.LENGTH_SHORT).show();
        Map<String, String> params = new HashMap<>();
        //params.put("Authorization","Bearer " +token);
        params.put("id", String.valueOf(id));



        //editor.putInt("account_num", Integer.parseInt(account_num.getText().toString()));


        // params.put("user_id",String.valueOf(user_id));
        //   Toast.makeText(login.this, "clicker", Toast.LENGTH_SHORT).show();

        RequestQueue queue = Volley.newRequestQueue(Track.this);
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, rejecturl, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Display the response string.

                        try {


                            status = response.getBoolean("status");

                            if (status) {
//                            Toast.makeText(Track.this, "username and password is correct", Toast.LENGTH_SHORT).show();


                            } else {
                                //Toast.makeText(Track.this, "username and password is false", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(Track.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer " + token);
                //params.put("Accept-Language", "fr");

                return params;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);


    }
}
}

