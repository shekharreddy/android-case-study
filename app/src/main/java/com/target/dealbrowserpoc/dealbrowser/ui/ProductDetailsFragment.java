package com.target.dealbrowserpoc.dealbrowser.ui;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.text.Spannable;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.target.dealbrowserpoc.dealbrowser.R;
import com.target.dealbrowserpoc.dealbrowser.deals.DealItem;
import com.target.dealbrowserpoc.dealbrowser.imagedownloader.GlideImageDownloader;

/**
 *  Product details fragment displays information of a product.
 */
public class ProductDetailsFragment extends Fragment {

    private DealItem mDealItem;

    private ImageView mProductLogoView;
    private TextView mRegPriceView;
    private TextView mDealPriceView;
    private TextView mTitleView;
    private TextView mDescView;


    public ProductDetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_details, container, false);
        mProductLogoView = view.findViewById(R.id.productLogoView);
        mRegPriceView = view.findViewById(R.id.regPriceView);
        mDealPriceView = view.findViewById(R.id.dealPriceView);
        mTitleView = view.findViewById(R.id.productTitleView);
        mDescView = view.findViewById(R.id.productDescView);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mDealItem = getActivity().getIntent().getParcelableExtra("DEAL_ITEM");
        updateDataOnUI();
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
        GlideImageDownloader.loadImage(getActivity(), mDealItem.getImage(), R.color.lightGray, mProductLogoView, mDealItem.getId());
        if(!TextUtils.isEmpty(mDealItem.getSalePrice())) {
            mDealPriceView.setText(mDealItem.getSalePrice());
            lineStrikeThrough(String.format(getString(R.string.regular_price), mDealItem.getPrice()));
        } else {
            mRegPriceView.setText(String.format(getString(R.string.regular_price), mDealItem.getPrice()));
        }
        mTitleView.setText(mDealItem.getTitle());
        mDescView.setText(mDealItem.getDescription());
    }
}
