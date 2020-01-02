package com.anggun.mongodbnodejs;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import server.ConfigUrl;

public class inputmkactivity extends AppCompatActivity {

    private RequestQueue mRequestQueue;
    private TextView txtData;
    private EditText edtkodemk, edtnamamk, edtjam, edthari, edtruangan, edtdosen;

    private Button btnbuat;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputmkactivity);

        mRequestQueue = Volley.newRequestQueue(this);

        edtkodemk = (EditText) findViewById(R.id.edtkodemk);
        edtnamamk = (EditText) findViewById(R.id.edtnamamk);
        edtjam = (EditText) findViewById(R.id.edtjam);
        edthari = (EditText) findViewById(R.id.edthari);
        edtruangan= (EditText) findViewById(R.id.edtruangan);
        edtdosen= (EditText) findViewById(R.id.edtdosen);

        btnbuat = (Button) findViewById(R.id.btnbuat);


        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        btnbuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strkodemk = edtkodemk.getText().toString();
                String strnamamk = edtnamamk.getText().toString();
                String strjam = edtjam.getText().toString();
                String strhari = edthari.getText().toString();
                String strruangan = edtruangan.getText().toString();
                String strdosen = edtdosen.getText().toString();


                if (strkodemk.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "kodemk Tidak Boleh Kosong",
                            Toast.LENGTH_LONG).show();
                } else if (strnamamk.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Nama Tidak Boleh Kosong",
                            Toast.LENGTH_LONG).show();
                } else if (strjam.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "jam Tidak Boleh Kosong",
                            Toast.LENGTH_LONG).show();
                } else if (strhari.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "hari Tidak Boleh Kosong",
                            Toast.LENGTH_LONG).show();
                } else if (strruangan.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "ruangan Tidak Boleh Kosong",
                            Toast.LENGTH_LONG).show();
                } else if (strdosen.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "dosen Tidak Boleh Kosong",
                            Toast.LENGTH_LONG).show();
                } else {
                    inputData(strkodemk, strnamamk, strjam, strhari, strruangan, strdosen);
                }
            }
        });

        getSupportActionBar().hide();

//        fetchJsonResponse();
    }

    private void inputData(String kodemk, String namamk, String jam, String hari, String ruangan, String dosen){

// Post params to be sent to the server
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("kodemk", kodemk);
        params.put("nama", namamk);
        params.put("jam", jam);
        params.put("hari", hari);
        params.put("ruangan", ruangan);
        params.put("dosen", dosen);

        pDialog.setMessage("Mohon Tunggu");
        showDialog();

        JsonObjectRequest req = new JsonObjectRequest(ConfigUrl.inputDataMhs, new JSONObject(params),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            boolean status = response.getBoolean("eror");
                            String msg;
                            if (status == true) {
                                msg = response.getString("pesan");
                            } else {
                                msg = response.getString("pesan");
                                Intent i = new Intent(inputmkactivity.this,inputmkactivity.class);
                                startActivity(i);
                                finish();
                            }
                            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                            VolleyLog.v("Response:%n %s", response.toString(4));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hideDialog();

                VolleyLog.e("Error: ", error.getMessage());
            }
        });

// add the request object to the queue to be executed
        //ApplicationController.getInstance().addToRequestQueue(req);

        mRequestQueue.add(req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }
    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
    }

