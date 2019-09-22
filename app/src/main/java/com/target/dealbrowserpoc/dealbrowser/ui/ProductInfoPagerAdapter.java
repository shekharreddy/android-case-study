package com.target.dealbrowserpoc.dealbrowser.ui;

import android.content.Context;
import android.text.Spannable;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;
import com.target.dealbrowserpoc.dealbrowser.R;
import com.target.dealbrowserpoc.dealbrowser.deals.DealItem;
import com.target.dealbrowserpoc.dealbrowser.imagedownloader.GlideImageDownloader;

import java.util.ArrayList;
import java.util.List;


/**
 * @author shekharreddy
 *
 * Simple {@link ProductInfoPagerAdapter} to show Product details which gives continuous feedback to the user
 * when scrolling.
 */

public class ProductInfoPagerAdapter extends PagerAdapter {
    private DealItem mDealItem;

    private ImageView mProductLogoView;
    private TextView mRegPriceView;
    private TextView mDealPriceView;
    private TextView mTitleView;
    private TextView mDescView;
    private Context mContext;
    private List<DealItem> dealsList;


    /**
     * @return the number of pages to display
     */
    @Override
    public int getCount() {
        return dealsList.size();
    }

    public ProductInfoPagerAdapter(List<DealItem> dealsList){
        this.dealsList = dealsList;
    }

    /**
     * @return true if the value returned from {@link #instantiateItem(ViewGroup, int)} is the
     * same object as the {@link View} added.
     */
    @Override
    public boolean isViewFromObject(View view, Object o) {
        return o == view;
    }

    /**
     * Instantiate the {@link View} which should be displayed at {@code position}. Here we
     * inflate a layout from the apps resources and then change the text view to signify the position.
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // Inflate a new layout from our resources
        mContext = container.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_product_details,
                container, false);
        // Add the newly created View to the ViewPager
        container.addView(view);
        initializeUI(view);

        mDealItem = dealsList.get(position);
        updateDataOnUI();
        // Return the View
        return view;
    }

    /**
     * Destroy the item from the {@link androidx.viewpager.widget.ViewPager}. In our case this is simply removing the
     * {@link View}.
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    // Retrieve UI widgets from xml
    private void initializeUI(View view){
        mProductLogoView = view.findViewById(R.id.productLogoView);
        mRegPriceView = view.findViewById(R.id.regPriceView);
        mDealPriceView = view.findViewById(R.id.dealPriceView);
        mTitleView = view.findViewById(R.id.productTitleView);
        mDescView = view.findViewById(R.id.productDescView);
    }

    /**
     *  Strike the Regular price view when deal available.
     * @param regPrice
     */
    private void lineStrikeThrough(String regPrice){
        mRegPriceView.setText(regPrice, TextView.BufferType.SPANNABLE);
        Spannable spannable = (Spannable) mRegPriceView.getText();
        spannable.setSpan(new StrikethroughSpan(), 5, regPrice.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
    }

    // Update the product information on UI
    private void updateDataOnUI(){
        GlideImageDownloader.loadImage(mProductLogoView.getContext(), mDealItem.getImage(), R.color.lightGray, mProductLogoView, mDealItem.getId());
        if(!TextUtils.isEmpty(mDealItem.getSalePrice())) {
            mDealPriceView.setText(mDealItem.getSalePrice());
            lineStrikeThrough(String.format(mDealPriceView.getContext().getResources().getString(R.string.regular_price), mDealItem.getPrice()));
        } else {
            mRegPriceView.setText(String.format(mRegPriceView.getContext().getResources().getString(R.string.regular_price), mDealItem.getPrice()));
        }
        mTitleView.setText(mDealItem.getTitle());
        mDescView.setText(mDealItem.getDescription());
    }

}
