package com.karim.lab_1_2_karimeljazzar_c0826750_android.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.karim.lab_1_2_karimeljazzar_c0826750_android.Adapter.ProductAdapter;
import com.karim.lab_1_2_karimeljazzar_c0826750_android.Helper.DatabaseHelper;
import com.karim.lab_1_2_karimeljazzar_c0826750_android.Models.ProductModel;
import com.karim.lab_1_2_karimeljazzar_c0826750_android.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView productsList;
    TextView longi,lati,desc;
    ArrayList<ProductModel> products;
    SearchView searchProduct;
    Button delete, update, add, map;
    public static ProductModel selectedProduct;
    public static DatabaseHelper databaseHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productsList = findViewById(R.id.productsLV);
        desc = findViewById(R.id.productDesc);
        longi = findViewById(R.id.longit);
        lati = findViewById(R.id.lat);
        searchProduct = findViewById(R.id.searchProduct);
        delete = findViewById(R.id.deleteProduct);
        update = findViewById(R.id.updateProduct);
        add = findViewById(R.id.add);
        map = findViewById(R.id.showMap);

        TextView textView = new TextView(this);
        textView.setText("ID                                Name                                Price");
        productsList.addHeaderView(textView);


        products = new ArrayList<>();
        databaseHelper = new DatabaseHelper(this);
        //databaseHelper.deleteAllProducts();

        Cursor cursor = databaseHelper.getProducts();

        if(cursor.moveToFirst()){
            do{
                products.add(new ProductModel(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getDouble(3),cursor.getDouble(4),cursor.getDouble(5)));
            }while (cursor.moveToNext());
        }
        cursor.close();

        if(products.isEmpty()){
            databaseHelper.addProduct("Apple","An apple is an edible fruit produced by an apple tree (Malus domestica). Apple trees are cultivated worldwide and are the most widely grown species in the genus Malus.", 6.3, 	50.15002545, 	-96.88332178);
            databaseHelper.addProduct("PlayStation 5","The PlayStation 5 (PS5) is a home video game console developed by Sony Interactive Entertainment.", 500,39.83003522,97.72999304);
            databaseHelper.addProduct("Xbox 360","The Xbox 360 is a home video game console developed by Microsoft. As the successor to the original Xbox, it is the second console in the Xbox series.", 200, 55.70900103, 9.534996498);
            databaseHelper.addProduct("Lemon","The lemon (Citrus limon) is a species of small evergreen trees in the flowering plant family Rutaceae, native to Asia, primarily Northeast India (Assam), Northern Myanmar or China.", 5, 	30.59199913, 	30.89999749);
            databaseHelper.addProduct("Strawberry","The garden strawberry (or simply strawberry; Fragaria × ananassa)[1] is a widely grown hybrid species of the genus Fragaria, collectively known as the strawberries, which are cultivated worldwide for their fruit.", 7.5, 	45.89997479, 	6.116670287);
            databaseHelper.addProduct("Watermelon","Watermelon (Citrullus lanatus) is a flowering plant species of the Cucurbitaceae family and the name of its edible fruit. A scrambling and trailing vine-like plant, it is a highly cultivated fruit worldwide, with more than 1,000 varieties.", 20, 		40.64200213, 		15.7989965);
            databaseHelper.addProduct("Peanut","The peanut, also known as the groundnut, goober (US), pindar (US) or monkey nut (UK), and taxonomically classified as Arachis hypogaea, is a legume crop grown mainly for its edible seeds.", 2.1, 	34.67202964, 	133.9170865);
            databaseHelper.addProduct("Peach","The peach (Prunus persica) is a deciduous tree native to the region of Northwest China between the Tarim Basin and the north slopes of the Kunlun Mountains, where it was first domesticated and cultivated.", 4.5, 		33.83330406, 		35.53329652);
            databaseHelper.addProduct("i7-10750H Processor", "10th Generation Intel® Core™ i7 Processors", 350, 38.91200402	, -6.337997512);
            databaseHelper.addProduct("iPhone","The iPhone is a line of smartphones designed and marketed by Apple Inc. that use Apple's iOS mobile operating system.", 900, 44.29048647	, 	-93.26801274);
            cursor = databaseHelper.getProducts();

            if(cursor.moveToFirst()){
                do{
                    products.add(new ProductModel(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getDouble(3),cursor.getDouble(4),cursor.getDouble(5)));
                }while (cursor.moveToNext());
            }
            cursor.close();
        }

        databaseHelper.getWritableDatabase();
        selectedProduct = products.get(0);


        ProductAdapter productAdapter = new ProductAdapter(this, products);
        productsList.setAdapter(productAdapter);

        desc.setText(products.get(0).getDescription());
        longi.setText(String.valueOf(products.get(0).getLongitude()));
        lati.setText(String.valueOf(products.get(0).getLatitude()));

        productsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                desc.setText(products.get(position-1).getDescription());
                longi.setText(String.valueOf(products.get(position-1).getLongitude()));
                lati.setText(String.valueOf(products.get(position-1).getLatitude()));
                selectedProduct = products.get(position-1);
            }
        });

        searchProduct.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<ProductModel> searchedProducts = new ArrayList<ProductModel>();

                for(ProductModel product: products){
                    if(product.getName().toLowerCase().contains(newText.toLowerCase()) || product.getDescription().toLowerCase().contains(newText.toLowerCase())){
                        searchedProducts.add(product);
                    }
                }
                ProductAdapter productAdapter = new ProductAdapter(getApplicationContext(), searchedProducts);
                productsList.setAdapter(productAdapter);

                productsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        desc.setText(searchedProducts.get(position-1).getDescription());
                        longi.setText(String.valueOf(searchedProducts.get(position-1).getLongitude()));
                        lati.setText(String.valueOf(searchedProducts.get(position-1).getLatitude()));
                        selectedProduct = searchedProducts.get(position-1);
                    }
                });
                return false;
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.deleteProduct(selectedProduct.getId());
                reloadProducts();
                desc.setText(products.get(0).getDescription());
                longi.setText(String.valueOf(products.get(0).getLongitude()));
                lati.setText(String.valueOf(products.get(0).getLatitude()));
                selectedProduct = products.get(0);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),UpdateActivity.class));
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddActivity.class));
            }
        });

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MapsActivity.class));
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        reloadProducts();
        desc.setText(products.get(0).getDescription());
        longi.setText(String.valueOf(products.get(0).getLongitude()));
        lati.setText(String.valueOf(products.get(0).getLatitude()));
        selectedProduct = products.get(0);
        searchProduct.setQuery("",false);
        searchProduct.clearFocus();

        productsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                desc.setText(products.get(position-1).getDescription());
                longi.setText(String.valueOf(products.get(position-1).getLongitude()));
                lati.setText(String.valueOf(products.get(position-1).getLatitude()));
                selectedProduct = products.get(position-1);
            }
        });

    }

    private void reloadProducts(){
        products.clear();
        Cursor cursor = databaseHelper.getProducts();
        if(cursor.moveToFirst()){
            do{
                products.add(new ProductModel(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getDouble(3),cursor.getDouble(4),cursor.getDouble(5)));
            }while (cursor.moveToNext());
        }
        cursor.close();
        ProductAdapter productAdapter = new ProductAdapter(this, products);
        productsList.setAdapter(productAdapter);
//        desc.setText("");
//        longi.setText("");
//        lati.setText("");
    }
}