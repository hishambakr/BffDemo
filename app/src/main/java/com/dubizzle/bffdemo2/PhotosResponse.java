package com.dubizzle.bffdemo2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by hishambakr on 3/14/17.
 */

public class PhotosResponse {
    @SerializedName("response")
    @Expose
    private List<String> response = null;

    public List<String> getResponse() {
        return response;
    }

    public void setResponse(List<String> response) {
        this.response = response;
    }

}
