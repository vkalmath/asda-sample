package vk.com.imagegallery.modules;

import com.squareup.picasso.Picasso;
import dagger.Module;
import dagger.Provides;
import vk.com.imagegallery.GalleryAdapter;
import vk.com.imagegallery.FriendsListActivityScope;
import vk.com.imagegallery.api.AlbumService;
import vk.com.imagegallery.api.RetrofitBuilder;
import vk.com.imagegallery.model.AlbumModel;
import vk.com.imagegallery.model.AlbumModelImpl;
import vk.com.imagegallery.presenter.FriendsListPresenter;
import vk.com.imagegallery.presenter.FriendsListPresenterImpl;
import vk.com.imagegallery.view.AlbumView;

/**
 *
 */
@FriendsListActivityScope
@Module
public class FriendsListModule {

    AlbumView view;

    public FriendsListModule(AlbumView view) {
        this.view = view;
    }

    @FriendsListActivityScope
    @Provides
    public FriendsListPresenter AlbumPresenter(AlbumView view, AlbumModel albumModel){
        return new FriendsListPresenterImpl(view, albumModel);
    }

    @FriendsListActivityScope
    @Provides
    public AlbumView providesAlbumView(){
        return view;
    }

    @FriendsListActivityScope
    @Provides
    public AlbumModel providesAlbumModel(AlbumService service){
        return new AlbumModelImpl(service);
    }

    @FriendsListActivityScope
    @Provides
    public AlbumService provideAlbumService(RetrofitBuilder builder){
        return builder.getRetrofit().create(AlbumService.class);
    }

    @FriendsListActivityScope
    @Provides
    public GalleryAdapter providesGallerryAdapter(Picasso picasso){
        return new GalleryAdapter(picasso);
    }
}
