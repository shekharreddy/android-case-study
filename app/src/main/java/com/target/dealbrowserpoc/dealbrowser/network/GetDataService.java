package com.target.dealbrowserpoc.dealbrowser.network;

import com.target.dealbrowserpoc.dealbrowser.deals.DealsContent;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 *  API to get Product List using retrofit
 */
public interface GetDataService {
    @Headers({"Accept: application/json"})
    @GET("/api/deals")
    Call<DealsContent> getAllDealsData();
}
