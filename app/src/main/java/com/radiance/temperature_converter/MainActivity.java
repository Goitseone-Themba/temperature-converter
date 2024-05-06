package com.radiance.temperature_converter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView title, result;
    EditText editFrom, editTo, editInput;
    Button btnConvert;

    String[] item = {"Celsius", "Fahrenheit", "Kelvin"};

    AutoCompleteTextView selectFrom, selectTo;

    ArrayAdapter<String> adapterItems;

    public double convert(String f, String t, double temp) {
        String type = f + t;
        double output = 0;
        switch(type) {
            case "CelsiusFahrenheit":
                output = temp * 9/5 + 32;
                break;
            case "FahrenheitCelsius":
                output = (temp - 32) * 5/9;
                break;
            case "CelsiusKelvin":
                output = temp + 273.15;
                break;
            case "KelvinCelsius":
                output = temp - 273.15;
                break;
            case "FahrenheitKelvin":
                output = (temp - 32) * (5/9) + 273.15;
                break;
            case "KelvinFahrenheit":
                output = ((temp - 273.15) * 9/5) + 32;
                break;
            default:
                break;
        }
        return  output;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         title = findViewById(R.id.title);
         result = findViewById(R.id.result);
//         editFrom = findViewById(R.id.editFrom);
//         editTo = findViewById(R.id.editTo);
         editInput = findViewById(R.id.editInput);
         btnConvert = findViewById(R.id.btnConvert);

         selectFrom = findViewById(R.id.selectFrom);
         selectTo = findViewById(R.id.selectTo);
         adapterItems = new ArrayAdapter<String>(this, R.layout.list_item, item);

         selectFrom.setAdapter(adapterItems);
         selectTo.setAdapter(adapterItems);

         selectFrom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 String item = parent.getItemAtPosition(position).toString();
                 Toast.makeText(MainActivity.this, "Item: " + item, Toast.LENGTH_SHORT).show();
             }
         });

         selectTo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 String item = parent.getItemAtPosition(position).toString();
                 Toast.makeText(MainActivity.this, "Item: " + item, Toast.LENGTH_SHORT).show();
             }
         });

         btnConvert.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String from = String.valueOf(selectFrom.getText());
                 String to = String.valueOf(selectTo.getText());
                 double temp = Double.parseDouble(String.valueOf(editInput.getText()));

                 result.setText(String.valueOf(convert(from, to, temp)));
             }
         });
    }
}

