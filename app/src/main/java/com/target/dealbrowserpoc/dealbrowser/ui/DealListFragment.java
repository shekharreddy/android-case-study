package com.target.dealbrowserpoc.dealbrowser.ui;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;


import com.airbnb.lottie.LottieAnimationView;
import com.target.dealbrowserpoc.dealbrowser.R;
import com.target.dealbrowserpoc.dealbrowser.deals.DealItem;
import com.target.dealbrowserpoc.dealbrowser.mvvm.DealListViewModel;
import com.target.dealbrowserpoc.dealbrowser.utils.RecyclerViewListDecoration;

import java.util.List;

import static com.target.dealbrowserpoc.dealbrowser.ui.DealsRecyclerViewAdapter.VIEW_TYPE_GRID;
import static com.target.dealbrowserpoc.dealbrowser.ui.DealsRecyclerViewAdapter.VIEW_TYPE_LIST;


/**
 * A fragment representing a list of Products.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class DealListFragment extends Fragment {

    private LottieAnimationView progressBar;
    private TextView mErrorView;

    private OnListFragmentInteractionListener mListener;
    private RecyclerView dealListRecyclerView;
    private DealsRecyclerViewAdapter dealListAdapter;
    private RecyclerViewListDecoration gridDecoration;
    private RecyclerViewListDecoration listDecoration;
    private boolean isGridViewDecorated = false;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DealListFragment() {
    }

    @Override
    public void onCreateOptionsMenu(Menu menu,  MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_list_to_grid) {
            if (!((Animatable) item.getIcon()).isRunning()) {
                if(dealListAdapter.getCurrentViewType() == VIEW_TYPE_LIST) {
                    item.setIcon(AnimatedVectorDrawableCompat.create(getContext(), R.drawable.avd_grid_to_list));
                } else {
                    item.setIcon(AnimatedVectorDrawableCompat.create(getContext(), R.drawable.avd_list_to_grid));
                }
                ((Animatable) item.getIcon()).start();
            }
            switchView();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    /**
     *  Switch between list view and grid view
     */
    private void switchView(){
        if(dealListAdapter.getCurrentViewType() == VIEW_TYPE_LIST) {
            dealListAdapter.setViewType(VIEW_TYPE_GRID);
            if(!isGridViewDecorated) {
                dealListRecyclerView.addItemDecoration(gridDecoration);
                isGridViewDecorated = true;
            }
            dealListRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), getContext().getResources().getInteger(R.integer.grid_column_count)));
        } else {
            dealListAdapter.setViewType(VIEW_TYPE_LIST);
            dealListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_deallistfragment_list, container, false);
        dealListRecyclerView = (RecyclerView) view;
        dealListAdapter = new DealsRecyclerViewAdapter(mListener);
        gridDecoration = new RecyclerViewListDecoration(getContext().getResources().getDimensionPixelSize(R.dimen.list_item_separator), VIEW_TYPE_GRID);
        listDecoration = new RecyclerViewListDecoration(getContext().getResources().getDimensionPixelSize(R.dimen.list_item_separator), VIEW_TYPE_LIST);
        dealListRecyclerView.setAdapter(dealListAdapter);
        dealListRecyclerView.addItemDecoration(listDecoration);
        dealListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final DealListViewModel viewModel = ViewModelProviders.of(this).get(DealListViewModel.class);
        progressBar = getActivity().findViewById(R.id.progressBar);
        mErrorView = getActivity().findViewById(R.id.errorView);
        observeViewModel(viewModel);
    }

    /**
     * Update the products list info on UI
     * @param products
     */
    private void updateDealsList(@Nullable List<DealItem> products){
        dealListAdapter.setProducts(products);
    }

    private void showError(String errorMessage){
        progressBar.setVisibility(View.GONE);
        mErrorView.setText(errorMessage);
        mErrorView.setVisibility(View.VISIBLE);
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();

        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();

    }


    // Observe API response with ViewModel to receive products info.
    private void observeViewModel(DealListViewModel viewModel) {
        // Update the list when the data changes
        viewModel.getDealsListObservable().observe(this, new Observer<List<DealItem>>() {
            @Override
            public void onChanged(@Nullable List<DealItem> products) {
                if (products != null && !products.isEmpty()) {
                    updateDealsList(products);
                    if(progressBar.getVisibility() == View.VISIBLE){
                        progressBar.setVisibility(View.GONE);
                    }
                    if(mErrorView.getVisibility() == View.VISIBLE){
                        mErrorView.setVisibility(View.GONE);
                    }
                    ((AppCompatActivity)getActivity()).getSupportActionBar().show();
                } else {
                    showError(getContext().getResources().getString(R.string.error_msg));
                }
            }
        });
        viewModel.getDealsListErrorObservable().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String errorMessage) {
                showError(errorMessage);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(DealItem item, int position);
    }
}
