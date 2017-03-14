package com.dubizzle.bffdemo2;


import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by hishambakr .
 */
public class RetrofitUtil {
    public static Retrofit getDefaultRetrofit() {
        return getDefaultRetrofit(true);
    }

    public static Retrofit getDefaultRetrofit(boolean rx) {




        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            ///  HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            /// interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            clientBuilder
                    ///      .addInterceptor(interceptor)
                    .addInterceptor(logging);
        }

        OkHttpClient client = clientBuilder

                .build();


        Retrofit.Builder builder = new Retrofit.Builder()
                .client(client)//

                .baseUrl("https://8c9gihumre.execute-api.eu-west-1.amazonaws.com")


                .addConverterFactory(GsonConverterFactory.create());
        if (rx) {
            builder.addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create());
        }

        return
                builder.build();
    }


    public static <T> T handleRetrofitResponse(Gson gson, Call<T> call) throws AppException {
        //asynchronous calll


        try {
            Response<T> response = call.execute();
            if (response.isSuccessful()) {

                //LogUtil.d(ff.size());
                return response.body();
                // EmptyResponse d = response.body();
                //  LogUtil.d(d );
                //response.errorBody().string()


            } else {
                throw ErrorHandler.getDefaultErrorHandler().getAppException(response);


            }
        } catch (Exception e) {

            throw ErrorHandler.getDefaultErrorHandler().getAppException(e);

        }
    }

    //// TODO: 2/28/17 improvement: itroduce network checking
/*
    private static boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) ApplicationContextProvider.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }*/

}

