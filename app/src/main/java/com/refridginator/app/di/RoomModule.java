package com.refridginator.app.di;

import android.app.Application;

import androidx.room.Room;

import com.refridginator.app.data.AppDatabase;
import com.refridginator.app.data.FridgeItemDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class RoomModule {

    @Singleton
    @Provides
    AppDatabase providesAppDatabase(Application application) {
        return Room.databaseBuilder(application, AppDatabase.class, "test").build();
    }

    @Singleton
    @Provides
    FridgeItemDao providesFridgeItemDao(AppDatabase appDatabase) {
        return appDatabase.fridgeItemDao();
    }
}
