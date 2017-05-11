package vk.com.imagegallery.api;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import vk.com.imagegallery.model.ImageList;

/**
 *
 */

public interface AlbumService {

    //3/album/Cfy6A/images

    @GET("{version}/album/{album_id}/images")
    Call<ImageList> getImageList(@Header("Authorization") String authorization, @Path("version") String apiVersion, @Path("album_id") String albumId);

    @GET("{version}/album/{album_id}/images")
    Observable<ImageList> getObservableImageList(@Header("Authorization") String authorization, @Path("version") String apiVersion, @Path("album_id") String albumId);

}
