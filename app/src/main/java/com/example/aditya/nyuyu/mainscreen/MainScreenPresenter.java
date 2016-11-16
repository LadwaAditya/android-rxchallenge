package com.example.aditya.nyuyu.mainscreen;


import android.util.Log;

import com.example.aditya.nyuyu.data.Result;
import com.example.aditya.nyuyu.data.Starship;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class MainScreenPresenter implements MainScreenContract.Presenter {

    public static final String TAG = MainScreenPresenter.class.getSimpleName();

    public Retrofit retrofit;
    MainScreenContract.View mView;

    @Inject
    public MainScreenPresenter(Retrofit retrofit, MainScreenContract.View mView) {
        this.retrofit = retrofit;
        this.mView = mView;
    }

    @Override
    public void loadPost() {
        Log.d(TAG, "LoadPost");
        PostService postService = retrofit.create(PostService.class);

        // Four pages
        Observable.range(1, 4)
                .concatMap(postService::getStarship) //Call each page
                .reduce(new ArrayList<Result>(), (results, starship) -> { //Combine all results into single result
                    results.addAll(starship.getResults());
                    return results;
                })
                .flatMap(new Func1<ArrayList<Result>, Observable<Result>>() { //Iterate through each result
                    @Override
                    public Observable<Result> call(ArrayList<Result> results) {
                        return Observable.from(results);
                    }
                })
                .filter(result -> !(result.getCostInCredits().contains("unknown"))) // remove starship with unknown value
                //Sort in decending order
                .toSortedList((result, result2) -> Long.valueOf(result2.getCostInCredits()).compareTo(Long.valueOf(result.getCostInCredits())))
                .take(15) // Take only top 15
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Result>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "OnComplete");

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, e.toString());

                    }

                    @Override
                    public void onNext(List<Result> results) {
                      mView.showPosts(results);
                    }
                });

    }

    public interface PostService {

        @GET("api/starships")
        Observable<Starship> getStarship(@Query("page") int page);
    }
}
