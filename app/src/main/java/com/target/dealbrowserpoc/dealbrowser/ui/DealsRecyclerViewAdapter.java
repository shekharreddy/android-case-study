package com.target.dealbrowserpoc.dealbrowser.ui;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.target.dealbrowserpoc.dealbrowser.R;
import com.target.dealbrowserpoc.dealbrowser.deals.DealItem;
import com.target.dealbrowserpoc.dealbrowser.imagedownloader.GlideImageDownloader;
import com.target.dealbrowserpoc.dealbrowser.ui.DealListFragment.OnListFragmentInteractionListener;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DealItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class DealsRecyclerViewAdapter extends RecyclerView.Adapter<DealsRecyclerViewAdapter.ListGridViewHolder> {

    private final @Nullable List<DealItem> mProducts;
    private final OnListFragmentInteractionListener mListener;
    private int currentViewType;
    public static final int VIEW_TYPE_LIST = 0;
    public static final int VIEW_TYPE_GRID = 1;

    public DealsRecyclerViewAdapter(@Nullable List<DealItem> products, OnListFragmentInteractionListener listener) {
        mProducts = products;
        mListener = listener;
    }

    @Override
    public ListGridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_LIST) {
            View view = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.deal_list_item, parent, false);
            return new
                    ListGridViewHolder(view);
        } else {
            View layoutView = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.deal_grid_item, parent, false);
            return new
                    ListGridViewHolder(layoutView);
        }
    }

    public int getCurrentViewType() {
        return currentViewType;
    }

    public void setViewType(int viewType){
        this.currentViewType = viewType;
    }

    @Override
    public int getItemViewType (int position) {
        return currentViewType;
    }

    private void setSpannableString(TextView view, String textContent){
        SpannableString spannableString = new SpannableString(textContent);
        ForegroundColorSpan foregroundSpanGray = new ForegroundColorSpan(Color.GRAY);
        ForegroundColorSpan foregroundSpanRed = new ForegroundColorSpan(Color.RED);
        spannableString.setSpan(foregroundSpanGray, 5, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(foregroundSpanRed, 8, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        view.setText(spannableString);
    }

    @Override
    public void onBindViewHolder(final ListGridViewHolder holder, int position) {
        DealItem dealItem = mProducts.get(position);
        holder.mItem = mProducts.get(position);
        holder.mProductNameView.setText(dealItem.getTitle());
        holder.mPriceView.setText(dealItem.getPrice());

        setSpannableString(holder.mShipOrStoreView, String.format(holder.mShipOrStoreView.getContext().getResources().getString(R.string.ship_or_aisle), dealItem.getAisle()));

        GlideImageDownloader.loadImage(holder.mProductLogoView.getContext(), dealItem.getImage(), R.color.lightGray, holder.mProductLogoView, dealItem.getId());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    /**
     * View Holder for List item UI
     */
    public class ListGridViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mProductNameView;
        public final TextView mPriceView;
        public final TextView mShipOrStoreView;
        public final ImageView mProductLogoView;

        public DealItem mItem;

        public ListGridViewHolder(View view) {
            super(view);
            mView = view;
            mProductLogoView = view.findViewById(R.id.productLogo);
            mProductNameView =  view.findViewById(R.id.productName);
            mPriceView = view.findViewById(R.id.productPrice);
            mShipOrStoreView = view.findViewById(R.id.shipOrStore);
        }
    }
}
