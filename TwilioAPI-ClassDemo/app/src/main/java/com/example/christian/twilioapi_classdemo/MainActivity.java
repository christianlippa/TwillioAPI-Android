package com.example.christian.twilioapi_classdemo;

import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText txtPhone;
    private Button btnVerify;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtPhone = (EditText) findViewById(R.id.txtPhoneNumber);
        btnVerify = (Button) findViewById(R.id.btnVerify);

        btnVerify.setOnClickListener(verifyButtonClicked);

        url = "http://10.0.2.2:38000/twilio";

    }

    public void twilioHTTPRequest() {


        List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("phoneNumber", txtPhone.getText().toString()));

        ServiceHandler sh = new ServiceHandler();
        sh.makeServiceCall(url, sh.POST, params);


    }


    View.OnClickListener verifyButtonClicked = new View.OnClickListener() {

        public void onClick(View v) {

            AsyncTask<Object, Object, Object> twilioRequestTask =
                            new AsyncTask<Object, Object, Object>() {
                                @Override
                                protected Object doInBackground(Object... params) {

                                    twilioHTTPRequest(); // delete contact in the database
                                    return null;

                                }

                                @Override
                                protected void onPostExecute(Object result) {
                                    //toast here
                        }
                    };

            twilioRequestTask.execute((Object[]) null);


        } // end method onClick
    }; // end OnClickListener saveContactButtonClicked
}