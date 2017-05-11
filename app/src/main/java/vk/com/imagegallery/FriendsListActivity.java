package vk.com.imagegallery;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import io.reactivex.disposables.CompositeDisposable;
import vk.com.imagegallery.component.DaggerFriendsListComponent;
import vk.com.imagegallery.component.FriendsListComponent;
import vk.com.imagegallery.modules.FriendsListModule;
import vk.com.imagegallery.presenter.FriendsListPresenter;
import vk.com.imagegallery.view.AlbumView;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class FriendsListActivity extends AppCompatActivity implements AlbumView{

    private static final String TAG = FriendsListActivity.class.getSimpleName();
    @Inject
    FriendsListPresenter presenter;

    @Inject
    ExecutorService executorService;

    @Inject
    GalleryAdapter galleryAdapter;

    RecyclerView recyclerView;

    CompositeDisposable disposable = new CompositeDisposable();

    FriendsListComponent friendsListComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeViews();

        friendsListComponent = DaggerFriendsListComponent.builder()
                .friendsListModule(new FriendsListModule(this))
                .appComponent(((ImageGalleryApplication) getApplication()).getAppComponent())
                .build();

        friendsListComponent.inject(this);

        executorService.submit(new Runnable() {
            @Override
            public void run() {
                presenter.fetchFriendsList();
            }
        });

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if(accessToken != null) {
            String userId = accessToken.getUserId();
            new GraphRequest(
                    AccessToken.getCurrentAccessToken(),
                    "/"+userId+"/friends",
                    null,
                    HttpMethod.GET,
                    new GraphRequest.Callback() {
                        public void onCompleted(GraphResponse response) {
                        /* handle the result */
                            Log.e(TAG, response.getRawResponse());
                        }
                    }
            ).executeAsync();
        }
    }

    private void initializeViews() {
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.gallery);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
        if(isFinishing()){
            Log.e(TAG, "Activity is getting really finished!!!");
        }
    }

    @Override
    public void showProgress() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(FriendsListActivity.this, "Showing Progress...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void hideProgress() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(FriendsListActivity.this, "Hiding Progress...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void showList(final List<String> list) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                galleryAdapter.setDataSource(list);
                recyclerView.setAdapter(galleryAdapter);
            }
        });

    }

    @Override
    public void showError(final Error error) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(FriendsListActivity.this, "Error: "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, FriendsListActivity.class);
        context.startActivity(intent);
    }
}
