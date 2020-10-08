package com.refridginator.app.di;

import androidx.lifecycle.ViewModel;

import com.refridginator.app.data.FridgeItemRepository;
import com.refridginator.app.viewmodels.StorageViewModel;
import com.refridginator.app.viewmodels.ViewModelProviderFactory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

import javax.inject.Provider;

import dagger.MapKey;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

@Module
public class ViewModelModule {

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @MapKey
    @interface ViewModelKey {
        Class<? extends ViewModel> value();
    }

    @Provides
    ViewModelProviderFactory viewModelProviderFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> providerMap) {
        return new ViewModelProviderFactory(providerMap);
    }

    @Provides
    @IntoMap
    @ViewModelKey(StorageViewModel.class)
    ViewModel storageViewModel(FridgeItemRepository fridgeItemRepository) {
        return new StorageViewModel(fridgeItemRepository);
    }

}
