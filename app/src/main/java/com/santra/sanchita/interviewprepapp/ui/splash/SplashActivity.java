package com.santra.sanchita.interviewprepapp.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.Scope;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.santra.sanchita.interviewprepapp.R;
import com.santra.sanchita.interviewprepapp.ui.base.BaseActivity;
import com.santra.sanchita.interviewprepapp.ui.login.LoginActivity;
import com.santra.sanchita.interviewprepapp.ui.main.MainActivity;
import com.santra.sanchita.interviewprepapp.utils.Constants;
import com.santra.sanchita.interviewprepapp.utils.EspressoIdlingResource;

import java.io.IOException;
import java.io.InputStreamReader;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sanchita on 8/12/17.
 */

public class SplashActivity extends BaseActivity implements SplashMvpView {

    @Inject
    SplashMvpPresenter<SplashMvpView> presenter;

    @BindView(R.id.imageLogoLauncher)
    ImageView imageLogoLauncher;

    private GoogleSignInClient mGoogleSignInClient;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, SplashActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        presenter.onAttach(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        hideActionBar();

        setUp();
    }

    @Override
    protected void onDestroy() {
        presenter.onDetach();

        super.onDestroy();
    }

    @Override
    protected void setUp() {

        try {
            GoogleClientSecrets clientSecrets =
                    GoogleClientSecrets.load(
                            JacksonFactory.getDefaultInstance(), new InputStreamReader(getAssets().open(Constants.CLIENT_SECRET_FILE)));

            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestScopes(new Scope("https://mail.google.com/"))
                    .requestServerAuthCode(clientSecrets.getDetails().getClientId(), true)
                    .requestIdToken(clientSecrets.getDetails().getClientId())
                    .requestEmail()
                    .build();

            mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Handler().postDelayed(() -> presenter.syncDatabase(), Constants.SPLASH_DELAY);
    }

    @Override
    public void nextActivity() {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        if(account == null) {
            if(mGoogleSignInClient != null) {
                mGoogleSignInClient.signOut().addOnCompleteListener(task -> startActivity(LoginActivity.getStartIntent(this)));
            }
        }
        else {
            Pair<View, String> sharedElement = Pair.create(imageLogoLauncher, ViewCompat.getTransitionName(imageLogoLauncher));

            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(SplashActivity.this, sharedElement);

            Intent mainActivityIntent = MainActivity.getStartIntent(this);
            String userName = account.getDisplayName();
            if(userName == null || userName.isEmpty()) {
                userName = account.getId();
            }
            mainActivityIntent.putExtra(Constants.LOGGED_IN_USER_NAME, userName);
            finish();
            startActivity(mainActivityIntent, optionsCompat.toBundle());
        }
    }

    @Override
    public void errorSyncingDatabase() {
        onError(R.string.default_error);
        finish();
    }

    @VisibleForTesting
    public IdlingResource getCountingIdlingResource() {
        return EspressoIdlingResource.getIdlingResource();
    }
}
