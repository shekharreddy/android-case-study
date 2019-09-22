package com.target.dealbrowserpoc.dealbrowser.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.target.dealbrowserpoc.dealbrowser.R;
import com.target.dealbrowserpoc.dealbrowser.deals.DealItem;

/**
 *  Activity to show Products List, and launches Product details screen for a selected item
 */
public class ProductListActivity extends AppCompatActivity implements DealListFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new DealListFragment())
                    .commit();
        }
        // Hide initially to show splash
        getSupportActionBar().hide();
    }

    @Override
    public void onListFragmentInteraction(DealItem item) {
        Intent productDetailsIntent = new Intent(this, ProductDetailsActivity.class);
        productDetailsIntent.putExtra("DEAL_ITEM", item);
        startActivity(productDetailsIntent);

    }
}
