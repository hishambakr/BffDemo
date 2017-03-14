package com.dubizzle.bffdemo2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private DisposableObserver disposableObserver;
    private final CompositeDisposable disposables = new CompositeDisposable();

    private TextView txtName, txtItems, txtAvatar, txtPhotos, txtTitle, txtDesctiption, txtPrice;
    private View layPhotos, layListings, layUser,loadingViewUser,loadingViewListings,loadingViewPhotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtName = (TextView) findViewById(R.id.txtName);
        txtItems = (TextView) findViewById(R.id.txtItems);


        txtAvatar = (TextView) findViewById(R.id.txtAvatar);
        txtPhotos = (TextView) findViewById(R.id.txtPhotos);
        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtDesctiption = (TextView) findViewById(R.id.txtDesctiption);
        txtPrice = (TextView) findViewById(R.id.txtPrice);

        layUser = findViewById(R.id.layUser);
        layListings = findViewById(R.id.layListings);
        layPhotos = findViewById(R.id.layPhotos);

        loadingViewUser = findViewById(R.id.loadingViewUser);
        loadingViewListings = findViewById(R.id.loadingViewListings);
        loadingViewPhotos = findViewById(R.id.loadingViewPhotos);

        KombiApi api = RetrofitUtil.getDefaultRetrofit().create(KombiApi.class);

        disposableObserver = new DisposableObserver<PhotosResponse>() {
            @Override
            public void onComplete() {
                // callback.onCompletedBl();
            }

            @Override
            public void onError(Throwable e) {


            }

            @Override
            public void onNext(PhotosResponse response) {

                String dd = "";
                for(String ss : response.getResponse()){
                    dd = dd+ ss+"\n";
                }

                txtPhotos.setText(dd);

                layPhotos.setVisibility(View.VISIBLE);
                loadingViewPhotos.setVisibility(View.GONE);


            }


        };


        disposables.add(api.getPhotos()
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(disposableObserver));


        //===
        disposableObserver = new DisposableObserver<UserResponse>() {
            @Override
            public void onComplete() {
                // callback.onCompletedBl();
                Log.d("ddd", "");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("ddd", "");

            }

            @Override
            public void onNext(UserResponse response) {

                Log.d("ddd", "");

                txtName.setText(response.getResponse().getName());
                txtItems.setText("" + response.getResponse().getItemsLive());
                txtAvatar.setText(response.getResponse().getAvatar());
                layUser.setVisibility(View.VISIBLE);
                loadingViewUser.setVisibility(View.GONE);

            }


        };


        disposables.add(api.getUser()
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(disposableObserver));


        disposableObserver = new DisposableObserver<ListingsResponse>() {
            @Override
            public void onComplete() {
                // callback.onCompletedBl();
                Log.d("ddd", "");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("ddd", "");

            }

            @Override
            public void onNext(ListingsResponse response) {

                Log.d("ddd", "");

                txtTitle.setText(response.getResponse().getTitle());
                txtDesctiption.setText( response.getResponse().getDescription());
                txtPrice.setText(""+response.getResponse().getPrice());
                layListings.setVisibility(View.VISIBLE);
                loadingViewListings.setVisibility(View.GONE);

            }


        };


        disposables.add(api.getListings()
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(disposableObserver));
    }
}
