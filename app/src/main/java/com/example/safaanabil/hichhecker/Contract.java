
package com.example.safaanabil.hichhecker;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.*;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Contract extends AppCompatActivity {


    String contractstatus, price;
    int from_id, to_id, id;
    String token;
    Boolean status;
    String url = "http://18.219.152.157/api/v1/contract/user";
    String doneurl = "http://18.219.152.157/api/v1/contract/done";
    String cancelurl = "http://18.219.152.157/api/v1/contract/cancel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract);


        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");

       // Toast.makeText(Contract.this, token + "", Toast.LENGTH_SHORT).show();
        //Map<String, String> params = new HashMap<>();
        //params.put("Authorization", "Bearer " + token);


        //Toast.makeText(login.this, ""+account_num.getText(), Toast.LENGTH_SHORT).show();
        //editor.putInt("account_num", Integer.parseInt(account_num.getText().toString()));


        // params.put("user_id",String.valueOf(user_id));
        //   Toast.makeText(login.this, "clicker", Toast.LENGTH_SHORT).show();

        RequestQueue queue = Volley.newRequestQueue(Contract.this);
        JsonObjectRequest stringRequest = new JsonObjectRequest(com.android.volley.Request.Method.GET, url, new JSONObject(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Display the response string.

                        try {


                            status = response.getBoolean("status");

                            if (status) {
                               // Toast.makeText(Contract.this, "username and password is correct", Toast.LENGTH_SHORT).show();
                                JSONArray heroArray = response.getJSONArray("data");
                                for (int i = 0; i < heroArray.length(); i++) {
                                    //Toast.makeText(Track.this, "ualllllllll", Toast.LENGTH_SHORT).show();
                                    JSONObject heroObject = heroArray.getJSONObject(i);

                                    price = heroObject.getString("price");
                                    contractstatus = heroObject.getString("contractstatus");
                                    id = heroObject.getInt("id");


                                    from_id = heroObject.getInt("from_id");
                                    to_id = heroObject.getInt("to_id");
                                   // Toast.makeText(Contract.this, "" + price + "    " + contractstatus + "" + from_id + "   " + to_id, Toast.LENGTH_SHORT).show();


                                }


                            } else {
                                //Toast.makeText(Contract.this, "username and password is false", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //_response.setText("That didn't work!");
                Toast.makeText(Contract.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
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


      public void done(View view)
        {
               if(view.getId() == R.id.button2) {
                   Toast.makeText(Contract.this, "Done", Toast.LENGTH_SHORT).show();
                   Map<String, String> params = new HashMap<>();
                   //params.put("Authorization","Bearer " +token);
                   params.put("id", String.valueOf(id));
                   //Toast.makeText(Contract.this, "" + id, Toast.LENGTH_SHORT).show();
                   RequestQueue queue = Volley.newRequestQueue(Contract.this);
                   JsonObjectRequest stringRequest = new JsonObjectRequest(com.android.volley.Request.Method.GET, doneurl, new JSONObject(params),
                           new Response.Listener<JSONObject>() {
                               @Override
                               public void onResponse(JSONObject response) {
                                   // Display the response string.

                                   try {


                                       status = response.getBoolean("status");

                                       if (status) {




                                       } else {
                                          // Toast.makeText(Contract.this, "username and password is false", Toast.LENGTH_SHORT).show();
                                       }
                                   } catch (JSONException e) {
                                       e.printStackTrace();
                                   }
                               }
                           }, new Response.ErrorListener() {
                       @Override
                       public void onErrorResponse(VolleyError error) {
                           //_response.setText("That didn't work!");
                           Toast.makeText(Contract.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
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
       public void cancel(View view)
        {

        if(view.getId() == R.id.button3) {
            Toast.makeText(Contract.this, "" +"canceled", Toast.LENGTH_SHORT).show();
            Map<String, String> params = new HashMap<>();
            //params.put("Authorization","Bearer " +token);
            params.put("id", String.valueOf(id));
            RequestQueue queue = Volley.newRequestQueue(Contract.this);
            JsonObjectRequest stringRequest = new JsonObjectRequest(com.android.volley.Request.Method.GET, cancelurl, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // Display the response string


                            try {


                                status = response.getBoolean("status");

                                if (status) {



                                } else {
                                    //Toast.makeText(Contract.this, "username and password is false", Toast.LENGTH_SHORT).show();
                                }

                } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //_response.setText("That didn't work!");
                    Toast.makeText(Contract.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
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

