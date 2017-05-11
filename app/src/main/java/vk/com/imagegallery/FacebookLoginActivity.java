package vk.com.imagegallery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class FacebookLoginActivity extends AppCompatActivity {

    private static final String TAG = FacebookLoginActivity.class.getSimpleName();
    com.facebook.login.widget.LoginButton loginButton;
    private CallbackManager callbackManager = CallbackManager.Factory.create();
    private LoginManager loginManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email", "user_friends", "public_profile");
        // If using in a fragment
        // Other app specific specialization

        AccessToken accessToken = AccessToken.getCurrentAccessToken();

        if(accessToken != null) {
            FriendsListActivity.start(this);
            finish();
        } else {
            loginManager = LoginManager.getInstance();
            loginManager.registerCallback(callbackManager,
                    new FacebookCallback<LoginResult>() {
                        @Override
                        public void onSuccess(LoginResult loginResult) {
                            // App code
                            Log.e(TAG, loginResult.toString());
                            FriendsListActivity.start(FacebookLoginActivity.this);
                            finish();
                        }

                        @Override
                        public void onCancel() {
                            // App code
                        }

                        @Override
                        public void onError(FacebookException exception) {
                            // App code
                            Log.e(TAG, exception.getMessage());
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(FacebookLoginActivity.this, "Make sure internet is On else som error happened on FB-SDK", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
