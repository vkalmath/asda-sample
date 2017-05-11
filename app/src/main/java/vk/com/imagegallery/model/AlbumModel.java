package vk.com.imagegallery.model;


import io.reactivex.Observable;

import java.util.List;


public interface AlbumModel {

    Observable<ImageList> getImagList(String albumId);

    void getImageList(String albumId);
}
