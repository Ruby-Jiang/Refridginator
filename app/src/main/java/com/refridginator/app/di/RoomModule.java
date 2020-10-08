package com.refridginator.app.di;

import android.app.Application;

import androidx.room.Room;

import com.refridginator.app.data.AppDatabase;
import com.refridginator.app.data.FridgeItemDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {

    private AppDatabase appDatabase;

    public RoomModule(Application application) {
        appDatabase = Room.databaseBuilder(application, AppDatabase.class, "test").build();
    }

    @Singleton
    @Provides
    AppDatabase providesAppDatabase() {
        return appDatabase;
    }

    @Singleton
    @Provides
    FridgeItemDao providesFridgeItemDao(AppDatabase appDatabase) {
        return appDatabase.fridgeItemDao();
    }
}
