package vk.com.imagegallery.component;

import dagger.Component;
import vk.com.imagegallery.FriendsListActivity;
import vk.com.imagegallery.FriendsListActivityScope;
import vk.com.imagegallery.modules.FriendsListModule;

@FriendsListActivityScope
@Component(modules = {FriendsListModule.class}, dependencies = AppComponent.class)
public interface FriendsListComponent {

//   AlbumPresenter presenter();
//   AlbumModel model();
//   AlbumService albumService();

   public void inject(FriendsListActivity activity);
}
