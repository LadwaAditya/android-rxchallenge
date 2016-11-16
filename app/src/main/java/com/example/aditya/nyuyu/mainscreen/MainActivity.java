package com.example.aditya.nyuyu.mainscreen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.aditya.nyuyu.Nyuyu;
import com.example.aditya.nyuyu.R;
import com.example.aditya.nyuyu.data.Result;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainScreenContract.View {

    @Inject
    MainScreenPresenter mainPresenter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private StarshipAdapter adapter;
    public static final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        DaggerMainScreenComponent.builder()
                .netComponent(((Nyuyu) getApplicationContext()).getNetComponent())
                .mainScreenModule(new MainScreenModule(this))
                .build().inject(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainPresenter.loadPost();
        Log.d(TAG, "OnCreate");
    }

    @Override
    public void showPosts(List<Result> results) {
        adapter = new StarshipAdapter(results);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showComplete() {

    }
}
