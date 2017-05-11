package vk.com.imagegallery;

import android.app.Application;
import vk.com.imagegallery.api.RetrofitBuilder;
import vk.com.imagegallery.component.AppComponent;
import vk.com.imagegallery.component.DaggerAppComponent;
import vk.com.imagegallery.modules.AppModule;

import javax.inject.Inject;

/**
 *
 */

public class ImageGalleryApplication extends Application {

    private AppComponent appComponent;

    @Inject
    RetrofitBuilder builder;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

    }

    public AppComponent getAppComponent(){
        return appComponent;
    }

}
