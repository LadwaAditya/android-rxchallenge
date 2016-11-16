package com.example.aditya.nyuyu.mainscreen;

import com.example.aditya.nyuyu.data.Result;

import java.util.List;

public interface MainScreenContract {
    interface View {
        void showPosts(List<Result> posts);

        void showError(String message);

        void showComplete();
    }

    interface Presenter {
        void loadPost();
    }
}

