package vk.com.imagegallery.presenter;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import vk.com.imagegallery.model.AlbumModel;
import vk.com.imagegallery.model.Datum;
import vk.com.imagegallery.model.ImageList;
import vk.com.imagegallery.view.AlbumView;

import javax.inject.Inject;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class FriendsListPresenterImpl implements FriendsListPresenter {

    private WeakReference<AlbumView> viewRef;
    private AlbumModel model;
    private List<String> listOfFriends = new ArrayList<String>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public FriendsListPresenterImpl(AlbumView view, AlbumModel model) {
        this.viewRef = new WeakReference<AlbumView>(view);
        this.model = model;
    }

    @Override
    public void fetchFriendsList() {
        final AlbumView view = viewRef.get();

        if (view != null) {
            view.showProgress();
        }

        if(listOfFriends.size()  == 0) {

            model.getImagList("Cfy6A")
                    .flatMap(new Function<ImageList, ObservableSource<String>>() {
                        @Override
                        public ObservableSource<String> apply(@NonNull ImageList imageList) throws Exception {
                            return Observable.fromIterable(imageList.getData()).map(new Function<Datum, String>() {
                                @Override
                                public String apply(@NonNull Datum datum) throws Exception {
                                    return datum.getLink();
                                }
                            });
                        }
                    })
                    .subscribe(new Observer<String>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                            compositeDisposable.add(d);
                        }

                        @Override
                        public void onNext(@NonNull String s) {
                            listOfFriends.add(s);
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            if (view != null) {
                                view.showError(new Error(e));
                                view.hideProgress();
                            }
                        }

                        @Override
                        public void onComplete() {
                            if (view != null) {
                                view.showList(listOfFriends);
                                view.hideProgress();
                            }
                            compositeDisposable.dispose();
                        }
                    });
        } else {
            if (view != null) {
                view.showList(listOfFriends);
                view.hideProgress();
            }
        }

    }
}
