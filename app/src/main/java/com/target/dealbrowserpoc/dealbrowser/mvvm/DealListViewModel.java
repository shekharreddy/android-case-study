package com.target.dealbrowserpoc.dealbrowser.mvvm;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.target.dealbrowserpoc.dealbrowser.deals.DealItem;

import java.util.List;

/**
 * Deal List ViewModel for DealListFragment
 */

public class DealListViewModel extends AndroidViewModel {
    private DealListRepository mDealListRepository;

    public DealListViewModel(Application application) {
        super(application);
        mDealListRepository = DealListRepository.getInstance();
    }

    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */
    public LiveData<List<DealItem>> getDealsListObservable() {
        return mDealListRepository.getDealsData();
    }

    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */
    public LiveData<String> getDealsListErrorObservable() {
        return mDealListRepository.errorInfo;
    }

//    @Override
//    protected void onCleared() {
//        mDealListRepository.removeData();
//    }

}
