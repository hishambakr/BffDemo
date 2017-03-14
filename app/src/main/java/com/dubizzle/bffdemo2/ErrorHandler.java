package com.dubizzle.bffdemo2;


import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by hishambakr on 9/27/16.
 */

public class ErrorHandler {
    private Gson gson = new Gson();


    public static ErrorHandler getDefaultErrorHandler() {
        return defaultErrorHandler;
    }

    private static ErrorHandler defaultErrorHandler = new ErrorHandler();

    protected ErrorHandler() {

    }

    public AppException getAppException(Throwable ex) {
        AppException result = null;
        if (ex instanceof AppException) {
            result = (AppException) ex;
        } else if (ex instanceof SocketTimeoutException || ex instanceof SocketException) {
            result = new AppException(ErrorCodes.NO_INTERNET);

        } else if (ex instanceof HttpException) {
            HttpException httpException = (HttpException) ex;

            Response response = httpException.response();


            //===

            ResponseBody errorBody = httpException.response().errorBody();
            if (errorBody == null) {
                result = new AppException(ErrorCodes.GENERIC_ERROR);

            } else {
                try {
                    String errorBodyString = errorBody.string();


                    String errorMessage = "";///errorMessageResponse.getMessageEn();


                    result = new AppException(response.code(), errorMessage);//// TODO: make error code be app related
                } catch (Exception e) {
                    result = new AppException(ErrorCodes.GENERIC_ERROR);

                }


            }
            //==


        }else if (ex instanceof NullPointerException) {



        } else {
            //result = new AppException(CloudtechErrorCodes.GSON_ERROR, CloudtechApplication.getInstance().getString(R.string.error_generic));
            result = new AppException(ErrorCodes.GENERIC_ERROR);
        }
        return result;
    }

    public <T> AppException getAppException(Response<T> response) {
        AppException result = null;
        ResponseBody errorBody = response.errorBody();
        if (errorBody == null) {
            result = new AppException(ErrorCodes.GENERIC_ERROR);
        } else {

            String errorBodyString = null;
            try {
                errorBodyString = errorBody.string();
            } catch (IOException e) {
                e.printStackTrace();
            }

            ErrorMessageResponse errorMessageResponse = gson.fromJson(errorBodyString, ErrorMessageResponse.class);

            String errorMessage = errorMessageResponse.getMessage();


            result = new AppException(response.code(), errorMessage);//// TODO: 7/20/16 crash
        }
        return result;
    }


}
