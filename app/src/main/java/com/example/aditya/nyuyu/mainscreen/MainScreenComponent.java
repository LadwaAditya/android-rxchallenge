package com.example.aditya.nyuyu.mainscreen;


import com.example.aditya.nyuyu.data.CustomScope;
import com.example.aditya.nyuyu.data.component.NetComponent;

import dagger.Component;


@CustomScope
@Component(dependencies = NetComponent.class, modules = MainScreenModule.class)
public interface MainScreenComponent {
    void inject(MainActivity activity);
}
