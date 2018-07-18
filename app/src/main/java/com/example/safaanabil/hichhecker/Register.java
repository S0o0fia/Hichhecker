package com.example.safaanabil.hichhecker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    EditText name , email, password , confirmpass ;
    final String url = "http://18.219.152.157/api/v1/users/add";
   Boolean status;
   String token,name1,email1;
   int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        confirmpass = (EditText) findViewById(R.id.confirmpass);

        final RelativeLayout layout = (RelativeLayout)findViewById(R.id.registerlayout);
        final float desnisty = getResources().getDisplayMetrics().density;
        final Drawable profile = getResources().getDrawable(R.drawable.profile);
        final Drawable pass = getResources().getDrawable(R.drawable.password);
        final Drawable emailimage = getResources().getDrawable(R.drawable.email);
        final int width = Math.round(24*desnisty);
        final int height = Math.round(24*desnisty);
        final int width1 = Math.round(35*desnisty);
        final int height1 = Math.round(35*desnisty);
        profile.setBounds(0, 0 , width , height);
        name.setCompoundDrawables(profile , null , null , null);

        pass.setBounds(0, 0 , width , height);
        password.setCompoundDrawables(pass , null , null , null);
        confirmpass.setCompoundDrawables(pass , null , null , null);

        emailimage.setBounds(0 , 0 , width1 , height1);
        email.setCompoundDrawables(emailimage , null , null , null);

    }


    public  void add (View view) {
        if (view.getId() == R.id.register) {
            //if(!name.getText().equals("") &&!password.getText().equals("")&&!email.getText().equals("")&&!confirmpass.getText().equals("")) {
                Map<String, String> params = new HashMap<>();
                params.put("name", name.getText().toString());
                params.put("email", email.getText().toString());
                params.put("password", password.getText().toString());
                params.put("password_confirmation", confirmpass.getText().toString());
                //   Toast.makeText(login.this, "clicker", Toast.LENGTH_SHORT).show();

                RequestQueue queue = Volley.newRequestQueue(Register.this);
                JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // Display the response string.

                                try {


                                    status = response.getBoolean("status");

                                    if (status) {
                                        Toast.makeText(Register.this, "username and password is correct", Toast.LENGTH_SHORT).show();
                                        Toast.makeText(Register.this, "is here", Toast.LENGTH_SHORT).show();
                                        Intent inte = new Intent(getApplicationContext(), HomePage.class);
                                        JSONObject heroArray = response.getJSONObject("data");
                                        id = heroArray.getInt("id");

                                        name1 = heroArray.getString("name");
                                        email1 = heroArray.getString("email");
                                        token = heroArray.getString("token");

                                        Toast.makeText(Register.this, "" + token + "    " + name + "   " + id, Toast.LENGTH_LONG).show();
                                        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString("token", token);
                                        editor.putString("user_name", name1);
                                        editor.putString("user_email", email1);
                                        editor.putInt("user_id", id);
                                        //Toast.makeText(login.this, ""+account_num.getText(), Toast.LENGTH_SHORT).show();
                                        // editor.putInt("account_num", Integer.parseInt(account_num.getText().toString()));

                                        editor.apply();

                                        name.setText("");
                                        password.setText("");
                                        email.setText("");
                                        confirmpass.setText("");
                                        startActivity(inte);



                                    } else {
                                        Toast.makeText(Register.this, "username and password is false", Toast.LENGTH_SHORT).show();
                                    }
                                   ;

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //_response.setText("That didn't work!");
                        Toast.makeText(Register.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                // Add the request to the RequestQueue.
                queue.add(stringRequest);
            }

        }
    }


