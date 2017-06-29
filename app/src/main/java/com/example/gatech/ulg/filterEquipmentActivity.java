package com.example.gatech.ulg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class filterEquipmentActivity extends AppCompatActivity {

    private String TAG = filterEquipmentActivity.class.getSimpleName();

    private TextView t1, t2;
    private Spinner s1, s2;
    private Button button;

    String[] listofSpinner = {"Item:","China","Nepal","Bhutan"};
    private  String filter;
    private  JSONArray categories;
    private String CategoryName;
    private List<String> filter1 = new ArrayList<String>();
    private List<String> filter2 = new ArrayList<String>();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_equipment);

        Bundle bundle = getIntent().getExtras();
        filter = bundle.getString("filters");
        if (filter != null) {

            try{
                JSONObject category = new JSONObject(filter);

                CategoryName = category.getString("name");

                JSONArray f1 = category.getJSONArray("filter1");
                JSONArray f2 = category.getJSONArray("filter2");

                for(int i = 0; i < f1.length(); i++){
                    filter1.add(f1.get(i).toString());
                }
                for(int j = 0; j < f2.length(); j++){
                    filter2.add(f2.get(j).toString());
                }
            }catch (JSONException e) {
                //some exception handler code.
            }


        }else {
            Log.e(TAG, "Couldn't get json from server.");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "Q____________Q", Toast.LENGTH_LONG).show();
                }
            });
        }


        t1 = (TextView) findViewById(R.id.textView1);
        t2 = (TextView) findViewById(R.id.textView2);
        s1 = (Spinner) findViewById(R.id.spinner1);
        s2 = (Spinner) findViewById(R.id.spinner2);
        button = (Button) findViewById(R.id.button);

        t1.setText(filter1.get(0));
        t2.setText(filter2.get(0));



        ArrayAdapter<String> filter1dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, filter1.subList(1, filter1.size() ));
        filter1dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> filter2dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, filter2.subList(1, filter1.size() ));
        filter2dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        s1.setAdapter(filter1dataAdapter);
        s2.setAdapter(filter2dataAdapter);


        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText( filterEquipmentActivity.this,
                        "OnClickListener : " +
                                "\nSpinner 1 : "+ String.valueOf(s1.getSelectedItem()) +
                                "\nSpinner 2 : "+ String.valueOf(s2.getSelectedItem()),
                        Toast.LENGTH_SHORT).show();
            }

        });


    }
}
