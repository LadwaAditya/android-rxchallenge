package com.example.aditya.nyuyu.mainscreen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.aditya.nyuyu.Nyuyu;
import com.example.aditya.nyuyu.R;
import com.example.aditya.nyuyu.data.Result;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainScreenContract.View {

    @Inject
    MainScreenPresenter mainPresenter;

    public static final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "Created", Toast.LENGTH_SHORT).show();
        DaggerMainScreenComponent.builder()
                .netComponent(((Nyuyu) getApplicationContext()).getNetComponent())
                .mainScreenModule(new MainScreenModule(this))
                .build().inject(this);
        mainPresenter.loadPost();
        Log.d(TAG, "OnCreate");
    }

    @Override
    public void showPosts(List<Result> results) {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showComplete() {

    }
}
