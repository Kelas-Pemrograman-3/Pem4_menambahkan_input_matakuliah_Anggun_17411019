package com.anggun.mongodbnodejs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.circularreveal.cardview.CircularRevealCardView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import server.ConfigUrl;
import session.SessionManager;

public class MainActivity extends AppCompatActivity {

    private SessionManager session;
    private CircularRevealCardView cardKeluar,cardinput,cardbaca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        session = new SessionManager(this);

        cardKeluar = (CircularRevealCardView) findViewById(R.id.cardKeluar);
        cardinput = (CircularRevealCardView) findViewById(R.id.card);
        cardKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.setLogin(false);
                session.setSkip(false);
                session.setSessid(0);

                Intent i = new Intent(MainActivity.this,Loginactivity.class);
                startActivity(i);
                finish();
            }
        });

        cardinput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,inputmkactivity.class);
                startActivity(i);
                finish();
            }
        });



    }
    }


//    private void fetchJsonResponse() {
//        // Pass second argument as "null" for GET requests
//        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, ConfigUrl.getAllMhs, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            String result = response.getString("data");
//                            Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
//                            //Log.v("Ini data dari server", result.toString());
//
//                            JSONArray res = new JSONArray(result);
//                            for (int i = 0; i < res.length(); i++){
//                                JSONObject jobj = res.getJSONObject(i);
//                                txtData.append("Npm = " + jobj.getString("npm") + "\n"+
//                                               "Nama = " + jobj.getString("nama") + "\n\n");
//                            }
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                VolleyLog.e("Error: ", error.getMessage());
//            }
//        });
//
//        /* Add your Requests to the RequestQueue to execute */
//        mRequestQueue.add(req);
//    }

