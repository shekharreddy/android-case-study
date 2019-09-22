package com.target.dealbrowserpoc.dealbrowser.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.target.dealbrowserpoc.dealbrowser.R;
import com.target.dealbrowserpoc.dealbrowser.mvvm.DealListRepository;
import com.target.dealbrowserpoc.dealbrowser.utils.Constants;
import com.target.dealbrowserpoc.dealbrowser.utils.ViewPagerTransformer;


/**
 * @author shekharreddy
 * Fragment to show Product details using viewpager adapter.
 */
public class ProductsInfoSlidingFragment extends Fragment {

    static final String TAG = "ProductsInfoSlidingFragment";

    private ViewPager mViewPager;

    /**
     * Inflates the {@link View} which will be displayed by this {@link Fragment}, from the app's
     * resources.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // BEGIN_INCLUDE (setup_viewpager)
        // Get the ViewPager and set it's PagerAdapter so that it can display items
        int currentPosition = 0;
        // Inflate the layout for this fragment
        if(getActivity().getIntent() != null) {
            Bundle bundle = getActivity().getIntent().getExtras();
            if (bundle != null) {
                currentPosition = bundle.getInt(Constants.BUNDLE_PRODUCT_POSITION);
            }
        }
        mViewPager = view.findViewById(R.id.viewpager);
        mViewPager.setAdapter(new ProductInfoPagerAdapter(DealListRepository.getInstance().getDealsData().getValue()));
        mViewPager.setPageTransformer(true, new ViewPagerTransformer());
        // Move the mViewPager position to selected product position.
        mViewPager.setCurrentItem(currentPosition);
        // END setup_viewpager
    }

}
