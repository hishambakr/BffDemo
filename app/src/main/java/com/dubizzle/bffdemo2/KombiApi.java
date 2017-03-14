package com.dubizzle.bffdemo2;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by hisham bakr.
 */

public interface KombiApi {



    @Headers({
            "Content-Type: application/json"
    })
    @GET("/demo/photos")
    Observable<PhotosResponse> getPhotos();

    @Headers({
            "Content-Type: application/json"
    })
    @GET("/demo/users/123")
    Observable<UserResponse> getUser();

//https://8c9gihumre.execute-api.eu-west-1.amazonaws.com/demo/listings/123
    @Headers({
            "Content-Type: application/json"
    })
    @GET("/demo/listings/nddfhdfbhd")
    Observable<ListingsResponse> getListings();


}