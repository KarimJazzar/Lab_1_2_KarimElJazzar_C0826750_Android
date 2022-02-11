package com.karim.lab_1_2_karimeljazzar_c0826750_android;

import static com.karim.lab_1_2_karimeljazzar_c0826750_android.MainActivity.databaseHelper;
import static com.karim.lab_1_2_karimeljazzar_c0826750_android.MainActivity.selectedProduct;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        final EditText editName = findViewById(R.id.addName);
        final EditText editDescription = findViewById(R.id.addDescription);
        final EditText editPrice = findViewById(R.id.addPrice);
        final EditText editLa = findViewById(R.id.addLat);
        final EditText editLo = findViewById(R.id.addLong);



        findViewById(R.id.addP).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editName.getText().toString().trim();
                String description = editDescription.getText().toString().trim();
                String price = editPrice.getText().toString().trim();
                String longitude = editLo.getText().toString().trim();
                String latitude = editLa.getText().toString().trim();


                if (name.isEmpty()) {
                    editName.setError("name field cannot be empty");
                    editName.requestFocus();
                    return;
                }

                if (description.isEmpty()) {
                    editDescription.setError("description cannot be empty");
                    editDescription.requestFocus();
                    return;
                }

                if (databaseHelper.addProduct(name, description, Double.parseDouble(price),Double.parseDouble(latitude),Double.parseDouble(longitude))){
                    Toast.makeText(getApplicationContext(), "Product Has been added!", Toast.LENGTH_SHORT).show();
                    editName.setText("");
                    editDescription.setText("");
                    editPrice.setText("");
                    editLa.setText("");
                    editLo.setText("");
                };

            }
        });
    }
}