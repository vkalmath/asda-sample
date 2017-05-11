package vk.com.imagegallery.model;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vk.com.imagegallery.api.AlbumService;

import javax.inject.Inject;

/**
 *
 */

public class AlbumModelImpl  implements AlbumModel{

    AlbumService albumService;

    @Inject
    public AlbumModelImpl(AlbumService albumService) {
        this.albumService = albumService;
    }

    @Override
    public Observable<ImageList> getImagList(String albumId) {
        return albumService.getObservableImageList("Client-ID 0ab4b2ada8624cc", "3", albumId);
    }

    @Override
    public void getImageList(String albumId) {
        albumService.getImageList("Client-ID 0ab4b2ada8624cc", "3", albumId).enqueue(new Callback<ImageList>() {
            @Override
            public void onResponse(Call<ImageList> call, Response<ImageList> response) {

            }

            @Override
            public void onFailure(Call<ImageList> call, Throwable t) {

            }
        });
    }
}
