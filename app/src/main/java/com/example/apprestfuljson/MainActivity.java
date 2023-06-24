package com.example.apprestfuljson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import WerbService.Asynchtask;
import WerbService.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void btmostar (View view){

        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService(
                "https://jsonplaceholder.typicode.com/users"
                        ,datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");


    }

    public void btlimpiar (View view){


        TextView txtmensaje = (TextView)findViewById(R.id.txtresult);
        txtmensaje.setText("");

    }

    @Override
    public void processFinish(String result) throws JSONException {

        String lstEmail="";
        JSONArray listaJson = new JSONArray(result);

        for (int i=0; i<listaJson.length();i++)
        {
            JSONObject email=listaJson.getJSONObject(i);
            lstEmail=lstEmail+"\n"+email.getString("email").toString();
        }
        
        TextView txtmensaje = (TextView)findViewById(R.id.txtresult);
        txtmensaje.setText("Este es el resultado"+"\n"
        +lstEmail);
    }
}