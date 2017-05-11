package vk.com.imagegallery.component;

import android.app.Application;
import com.squareup.picasso.Picasso;
import dagger.Component;
import vk.com.imagegallery.ImageGalleryApplicationScope;
import vk.com.imagegallery.api.RetrofitBuilder;
import vk.com.imagegallery.modules.AppModule;

import java.util.concurrent.ExecutorService;

/**
 *
 */
@ImageGalleryApplicationScope
@Component(modules = {AppModule.class})
public interface AppComponent {

    RetrofitBuilder retrofitBuilder();

    ExecutorService executorService();

    Application application();

    Picasso picasso();
}
