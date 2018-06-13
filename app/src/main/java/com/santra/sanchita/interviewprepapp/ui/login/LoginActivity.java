package com.santra.sanchita.interviewprepapp.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.Task;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.santra.sanchita.interviewprepapp.R;
import com.santra.sanchita.interviewprepapp.data.db.model.InterviewItem;
import com.santra.sanchita.interviewprepapp.ui.base.BaseActivity;
import com.santra.sanchita.interviewprepapp.ui.main.MainActivity;
import com.santra.sanchita.interviewprepapp.utils.Constants;

import java.io.IOException;
import java.io.InputStreamReader;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by sanchita on 13/6/18.
 */

public class LoginActivity extends BaseActivity implements LoginMvpView {

    @Inject
    LoginMvpPresenter<LoginMvpView> presenter;

    private GoogleSignInClient mGoogleSignInClient;

    private GoogleSignInAccount account;

    @BindView(R.id.signInButton)
    SignInButton signInButton;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
                    .requestServerAuthCode(clientSecrets.getDetails().getClientId(), true)
                    .requestIdToken(clientSecrets.getDetails().getClientId())
                    .requestEmail()
                    .build();

            mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.signInButton)
    public void signInButtonClick() {
        if(mGoogleSignInClient != null) {
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, Constants.REQUEST_CODE_SIGN_IN);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == Constants.REQUEST_CODE_SIGN_IN) {
            if(resultCode == RESULT_OK) {
                showLoading();
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                handleSignInResult(task);
            }
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            account = completedTask.getResult(ApiException.class);
            if(account.getAccount() != null) {
                presenter.saveOnlineAuthCode(account.getServerAuthCode());
            }
        } catch(ApiException apiException) {
            if(mGoogleSignInClient != null) {
                mGoogleSignInClient.signOut();
            }
        }
    }

    @Override
    public void signUpSuccessful() {
        if(account != null) {
            Intent mainActivityIntent = MainActivity.getStartIntent(this);
            String userName = account.getDisplayName();
            if(userName == null || userName.isEmpty()) {
                userName = account.getId();
            }
            mainActivityIntent.putExtra(Constants.LOGGED_IN_USER_NAME, userName);
            startActivity(mainActivityIntent);
        }
        else {
            onError(R.string.default_error);
        }
    }
}
