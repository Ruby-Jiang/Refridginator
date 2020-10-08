package com.refridginator.app.di;

import com.refridginator.app.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RoomModule.class, NetworkModule.class, ViewModelModule.class})
public interface AppComponent {
    void inject(MainActivity mainActivity);
}
