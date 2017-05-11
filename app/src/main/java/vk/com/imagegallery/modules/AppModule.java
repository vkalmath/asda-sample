package vk.com.imagegallery.modules;

import android.app.Application;
import android.content.Context;
import com.squareup.picasso.Picasso;
import dagger.Module;
import dagger.Provides;
import vk.com.imagegallery.ImageGalleryApplicationScope;
import vk.com.imagegallery.api.RetrofitBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@ImageGalleryApplicationScope
@Module
public class AppModule {
    public static final int ACTIVITY_POOL_SIZE = 10;
    private Application application;

    public AppModule(Application context) {
        this.application = context;
    }

    @Provides
    @ImageGalleryApplicationScope
    public Application provideContext() {
        return application;
    }

    @Provides
    @ImageGalleryApplicationScope
    public RetrofitBuilder provideRetrofitBuilder() {
        return RetrofitBuilder.getInstance();
    }

    @Provides
    @ImageGalleryApplicationScope
    public ExecutorService provideThreadPool() {
        return Executors.newFixedThreadPool(ACTIVITY_POOL_SIZE);
    }

    @Provides
    @ImageGalleryApplicationScope
    public Picasso providePicassoInstance(Application application) {
        return Picasso.with(application.getApplicationContext());
    }

}
