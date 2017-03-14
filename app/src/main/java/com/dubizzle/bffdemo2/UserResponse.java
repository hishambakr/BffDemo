package com.dubizzle.bffdemo2;

/**
 * Created by hishambakr on 3/14/17.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserResponse {
    @SerializedName("response")
    @Expose
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }


    public class Response {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("items_live")
        @Expose
        private Integer itemsLive;
        @SerializedName("avatar")
        @Expose
        private String avatar;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getItemsLive() {
            return itemsLive;
        }

        public void setItemsLive(Integer itemsLive) {
            this.itemsLive = itemsLive;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }
}
