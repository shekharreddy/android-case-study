package com.target.dealbrowserpoc.dealbrowser.mvvm;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.target.dealbrowserpoc.dealbrowser.deals.DealItem;
import com.target.dealbrowserpoc.dealbrowser.deals.DealsContent;
import com.target.dealbrowserpoc.dealbrowser.network.GetDataService;
import com.target.dealbrowserpoc.dealbrowser.network.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *  Repository to maintain Deal list data, Data will be received by Network call
 */

public class DealListRepository {
    private static DealListRepository mInstance;
    final MutableLiveData<List<DealItem>> data = new MutableLiveData<>();
    final MutableLiveData<String> errorInfo = new MutableLiveData<>();


    /**
     * private constructor
     */
    private DealListRepository() {
        //private constructor
    }

    /**
     * Returns the singleton instance of BootUpDataRepository
     *
     * @return: instance of BootUpDataRepository
     */
    public static DealListRepository getInstance() {
        if (mInstance == null) {
            mInstance = new DealListRepository();
        }
        return mInstance;
    }

    // Clear the data on ViewModel destroy
    public void removeData(){
        data.getValue().clear();
    }

    // Get list of Deal Item
    public LiveData<List<DealItem>> getDealsData(){

        /*Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitClientInstance.getRetrofitInstance(RetrofitClientInstance.BASE_URL).create(GetDataService.class);
        Call<DealsContent> call = service.getAllDealsData();
        call.enqueue(new Callback<DealsContent>() {

            //shekhar
            @Override
            public void onResponse(Call<DealsContent> call, Response<DealsContent> response) {
                if(response.isSuccessful()) {
                    data.setValue(response.body().getData());
                } else {
                    Log.e("onFailure",   "onFailure Response " + response.message());
                    errorInfo.setValue(response.message());
                }

            }

            @Override
            public void onFailure(Call<DealsContent> call, Throwable t) {
                Log.e("onFailure",   "onFailure Response " + t.getMessage());
                errorInfo.setValue(t.getMessage());

            }
        });
        return data;
    }
}
