package com.karim.lab_1_2_karimeljazzar_c0826750_android.Views;

import static com.karim.lab_1_2_karimeljazzar_c0826750_android.Views.MainActivity.databaseHelper;
import static com.karim.lab_1_2_karimeljazzar_c0826750_android.Views.MainActivity.selectedProduct;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.karim.lab_1_2_karimeljazzar_c0826750_android.R;

public class UpdateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        final EditText editName = findViewById(R.id.editName);
        final EditText editDescription = findViewById(R.id.editDescription);
        final EditText editPrice = findViewById(R.id.editPrice);
        final EditText editLa = findViewById(R.id.editLat);
        final EditText editLo = findViewById(R.id.editLong);


        editName.setText(selectedProduct.getName());
        editDescription.setText(selectedProduct.getDescription());
        editPrice.setText(String.valueOf(selectedProduct.getPrice()));
        editLa.setText(String.valueOf(selectedProduct.getLatitude()));
        editLo.setText(String.valueOf(selectedProduct.getLongitude()));

        findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editName.getText().toString().trim();
                String description = editDescription.getText().toString().trim();
                String price = editPrice.getText().toString().trim();
                String longitude = editLo.getText().toString().trim();
                String latitude = editLa.getText().toString().trim();


                if (name.isEmpty()) {
                    editName.setError("Name field cannot be empty");
                    editName.requestFocus();
                    return;
                }

                if (description.isEmpty()) {
                    editDescription.setError("Description cannot be empty");
                    editDescription.requestFocus();
                    return;
                }

                if (price.isEmpty()) {
                    editPrice.setError("Price cannot be empty");
                    editPrice.requestFocus();
                    return;
                }

                if (longitude.isEmpty()) {
                    editLo.setError("Longitude cannot be empty");
                    editLo.requestFocus();
                    return;
                }

                if (latitude.isEmpty()) {
                    editLa.setError("Latitude cannot be empty");
                    editLa.requestFocus();
                    return;
                }

                if (databaseHelper.updateProduct(selectedProduct.getId(), name, description, Double.parseDouble(price),Double.parseDouble(latitude),Double.parseDouble(longitude))){
                    Toast.makeText(getApplicationContext(), "Product Has been updated", Toast.LENGTH_SHORT).show();
                };

            }
        });
    }
}