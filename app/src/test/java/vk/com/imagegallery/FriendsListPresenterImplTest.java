package vk.com.imagegallery;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import vk.com.imagegallery.model.AlbumModel;
import vk.com.imagegallery.model.Datum;
import vk.com.imagegallery.model.ImageList;
import vk.com.imagegallery.presenter.FriendsListPresenter;
import vk.com.imagegallery.presenter.FriendsListPresenterImpl;
import vk.com.imagegallery.view.AlbumView;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class FriendsListPresenterImplTest {

    @Mock
    AlbumModel mockModel ;

    @Mock
    AlbumView mockView;

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void whenGetAlbumListIsCalledVerifyGetImageListCalledOnModel(){
        when(mockModel.getImagList(anyString())).thenReturn(Observable.create(new ObservableOnSubscribe<ImageList>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<ImageList> e) throws Exception {

            }
        }));

        //SUT
        FriendsListPresenter presenter = new FriendsListPresenterImpl(mockView, mockModel);
        presenter.fetchFriendsList();

        verify(mockModel, times(1)).getImagList(anyString());
    }

    @Test
    public void whenGetAlbumListIsCalledVerifyShowAlbumIsCalledOnView() {
        ImageList list = new ImageList();
        List<Datum> datumList = new ArrayList<>();
        Datum d = new Datum();
        d.setLink("test");
        datumList.add(d);


        when(mockModel.getImagList(anyString())).thenReturn(Observable.just(list));

        //SUT
        FriendsListPresenter presenter = new FriendsListPresenterImpl(mockView, mockModel);
        presenter.fetchFriendsList();

        verify(mockView, times(1)).showList((List<String>) any());
    }

    @Test
    public void whenGetImageListIsCalledVerifyShowProgressCalledOnView() {
        ImageList list = new ImageList();
        when(mockModel.getImagList(anyString())).thenReturn(Observable.just(list));

        //SUT
        FriendsListPresenter presenter = new FriendsListPresenterImpl(mockView, mockModel);
        presenter.fetchFriendsList();

        verify(mockView, times(1)).showProgress();
    }

    @Test
    public void whenGetImageListIsCalledVerifyHideProgressCalledOnView() {
        ImageList list = new ImageList();
        List<Datum> datumList = new ArrayList<>();
        Datum d = new Datum();
        d.setLink("test");
        datumList.add(d);
        when(mockModel.getImagList(anyString())).thenReturn(Observable.just(list));

        //SUT
        FriendsListPresenter presenter = new FriendsListPresenterImpl(mockView, mockModel);
        presenter.fetchFriendsList();

        verify(mockView, times(1)).hideProgress();
    }


    @Test
    public void whenGetImageListIsCalledVerifyShowErrorCalledOnViewWhenErrorThrown() {
        ImageList list = new ImageList();
        List<Datum> datumList = new ArrayList<>();
        datumList.add(null);
        datumList.add(null);
        datumList.add(null);

        when(mockModel.getImagList(anyString())).thenReturn(Observable.just(list));

        //SUT
        FriendsListPresenter presenter = new FriendsListPresenterImpl(mockView, mockModel);
        presenter.fetchFriendsList();



        verify(mockView, times(1)).showError((Error) any());
    }


}
