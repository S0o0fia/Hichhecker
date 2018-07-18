package com.example.safaanabil.hichhecker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class Request extends AppCompatActivity {

    Button request;
    Boolean status;
   int user_id,from_id,to_id,hicker_id;


    TextView name , price , tocity , fromcity ;
    String url="http://18.219.152.157/api/v1/request/add";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        request=(Button)findViewById(R.id.requestbutton);
        name = (TextView) findViewById(R.id.valuedate);
        name.setText(getIntent().getStringExtra("name"));

       price = (TextView) findViewById(R.id.price_num);
       price.setText(getIntent().getStringExtra("price"));


        tocity = (TextView) findViewById(R.id.t_city);
        tocity.setText(getIntent().getStringExtra("tocity"));

        fromcity = (TextView) findViewById(R.id.f_city);
        fromcity.setText(getIntent().getStringExtra("fcity"));

    }

    public void req(View view)
    {


        if(view.getId() == R.id.requestbutton)
        {
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
         user_id = sharedPreferences.getInt("user_id", 0);
            SharedPreferences sharedPreferences1 = getSharedPreferences("fromandto", Context.MODE_PRIVATE);
            from_id = sharedPreferences.getInt("from_id", 0);
            to_id = sharedPreferences.getInt("to_id", 0);
            hicker_id = sharedPreferences.getInt("hicker_id", 0);
           /* Toast.makeText(Request.this ,
                    getIntent().getStringExtra("to_id")+
                        getIntent().getStringExtra("from_id")+
                            getIntent().getStringExtra("name")+
                            user_id+""


                    ,Toast.LENGTH_LONG).show();*/
        if(view.getId() == R.id.requestbutton) {
            Map<String, String> params = new HashMap<>();
            params.put("price", getIntent().getStringExtra("price"));
            params.put("from_id", getIntent().getStringExtra("from_id"));
            params.put("to_id", getIntent().getStringExtra("to_id"));
            params.put("requeststatus_id", "1");
            params.put("hicker_id",getIntent().getStringExtra("name"));
            params.put("user_id", user_id+"");
            // params.put("user_id",String.valueOf(user_id));
            //   Toast.makeText(login.this, "clicker", Toast.LENGTH_SHORT).show();

            /*params.put("price", "30");
            params.put("from_id", "2");
            params.put("to_id", "1");
            params.put("requeststatus_id", "1");
            params.put("hicker_id","1" );
            params.put("user_id", "2");*/

            RequestQueue queue = Volley.newRequestQueue(Request.this);
            JsonObjectRequest stringRequest = new JsonObjectRequest(com.android.volley.Request.Method.POST, url, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // Display the response string.


                            try {
                                status = response.getBoolean("status");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            if (status) {
                               // Toast.makeText(Request.this, "username and password is correct", Toast.LENGTH_SHORT).show();


                                //editor.apply();
                                Intent inte = new Intent(getApplicationContext(), Contract.class);
                                startActivity(inte);


                            } else {
                               // Toast.makeText(Request.this, "username and password is false", Toast.LENGTH_SHORT).show();
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

                        }
                    }
                    , new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //_response.setText("That didn't work!");
                    Toast.makeText(Request.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

            // Add the request to the RequestQueue.
            queue.add(stringRequest);

        }
    }
}
}

