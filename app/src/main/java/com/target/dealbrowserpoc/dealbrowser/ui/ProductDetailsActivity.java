package com.target.dealbrowserpoc.dealbrowser.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.target.dealbrowserpoc.dealbrowser.R;

/**
 * Activity to show product details
 */
public class ProductDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        getSupportActionBar().hide();
    }

}
