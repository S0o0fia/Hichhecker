


package com.example.safaanabil.hichhecker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HickerLogin extends AppCompatActivity {

    EditText user,pass;
    Button login;
    Boolean status;
    String name,email,token;
    int id,group_id;
    final String url = "http://18.219.152.157/api/v1/users/login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hicker_login);
        user=(EditText) findViewById(R.id.email);
        pass=(EditText) findViewById(R.id.password);
        login=(Button) findViewById(R.id.login);

        final LinearLayout layout = (LinearLayout)findViewById(R.id.loginlayout);
        final float desnisty = getResources().getDisplayMetrics().density;
        final Drawable profile = getResources().getDrawable(R.drawable.profile);
        final Drawable password = getResources().getDrawable(R.drawable.password);
        final int width = Math.round(24*desnisty);
        final int height = Math.round(24*desnisty);

        profile.setBounds(0, 0 , width , height);
        user.setCompoundDrawables(profile , null , null , null);

        password.setBounds(0, 0 , width , height);
        pass.setCompoundDrawables(password , null , null , null);

    }

    public void gotoprofile (View view)
    {
        if(view.getId() == R.id.login) {
            Map<String, String> params = new HashMap<>();
            params.put("email", user.getText().toString());
            params.put("password", pass.getText().toString());
            //   Toast.makeText(login.this, "clicker", Toast.LENGTH_SHORT).show();

            RequestQueue queue = Volley.newRequestQueue(HickerLogin.this);
            JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // Display the response string.

                            try {


                                status = response.getBoolean("status");

                                if (status) {
                                    Toast.makeText(HickerLogin.this, "username and password is correct", Toast.LENGTH_SHORT).show();
                                    Intent inte = new Intent(getApplicationContext(), HickerHome.class);
                                    JSONObject heroArray = response.getJSONObject("data");
                                    token = heroArray.getString("token");
                                    name = heroArray.getString("name");
                                    email = heroArray.getString("email");
                                    id = heroArray.getInt("id");
                                    group_id = heroArray.getInt("group_id");
                                    //Toast.makeText(HickerLogin.this, "" + token + "    " + name + "   " + id+"   "+group_id, Toast.LENGTH_SHORT).show();
                                    SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("token", token);
                                    editor.putString("user_name", name);
                                    editor.putString("user_email", email);
                                    editor.putInt("user_id", id);
                                    editor.putInt("group_id", group_id);
                                    //Toast.makeText(login.this, ""+account_num.getText(), Toast.LENGTH_SHORT).show();
                                    //editor.putInt("account_num", Integer.parseInt(account_num.getText().toString()));

                                    editor.apply();

                                    user.setText("");
                                    pass.setText("");
                                    startActivity(inte);

                                } else {
                                    Toast.makeText(HickerLogin.this, "username and password is false", Toast.LENGTH_SHORT).show();
                                }

                                //account_num.setText("");

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //_response.setText("That didn't work!");
                    Toast.makeText(HickerLogin.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            // Add the request to the RequestQueue.
            queue.add(stringRequest);
        }

    }
    }



