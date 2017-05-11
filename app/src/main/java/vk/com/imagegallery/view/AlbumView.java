package vk.com.imagegallery.view;

import io.reactivex.Observable;

import java.util.List;

/**
 *
 */

public interface AlbumView {

    public void showProgress();

    public void hideProgress();

    public void showList(List<String> list);

    public void showError(Error error);
}
