package com.example.user.clubmahindra.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.user.clubmahindra.R;
import com.example.user.clubmahindra.model.LoginModel;
import com.example.user.clubmahindra.model.TokenDataModel;
import com.google.gson.JsonObject;
import com.kelltontech.ui.IScreen;
import com.kelltontech.volley.ext.GsonObjectRequest;
import com.kelltontech.volley.ext.RequestManager;

import java.util.HashMap;

import static android.view.View.VISIBLE;
import static com.example.user.clubmahindra.constants.Constants.actionIdOfLogin;
import static com.example.user.clubmahindra.constants.Constants.urlOfLogin;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, IScreen {

    int mShortAnimationDuration = 1234;
    private EditText mEditTextLoginId;
    private EditText mEditTextLoginPassword;
    private Button mLoginButton;
    private String loginId;
    private String password;
    private String token;
    private String uniqueIdKey;
    private ProgressBar mProgressBar;
    private LinearLayout mLinearLayoutContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setId();
        setListenersOnView();

    }

    private void setListenersOnView() {
        mLoginButton.setOnClickListener(this);
    }

    private void setId() {
        mEditTextLoginId = (EditText) findViewById(R.id.loginEditTextId);
        mEditTextLoginPassword = (EditText) findViewById(R.id.loginPasswordEditTextIdId);
        mLoginButton = (Button) findViewById(R.id.loginButtonId);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mLinearLayoutContent = (LinearLayout) findViewById(R.id.contentLinearLayoutofLoginScreen);

    }

    @Override
    public void onClick(View view) {
        loginId = mEditTextLoginId.getText().toString();
        password = mEditTextLoginPassword.getText().toString();
        if (loginId.equals("admin") && password.equals("admin")) {
            /*Toast.makeText(LoginActivity.this, loginId + password, Toast.LENGTH_LONG).show();*/
            getData(actionIdOfLogin);
            mProgressBar.setVisibility(View.VISIBLE);
            // Retrieve and cache the system's default "short" animation time.
            mShortAnimationDuration = getResources().getInteger(
                    android.R.integer.config_shortAnimTime);
            crossFade();


        } else if (!loginId.equals("admin")) {
            mEditTextLoginId.requestFocus();
            mEditTextLoginId.setError("Enter Valid Name  ");

        } else if (!password.equals("admin")) {
            mEditTextLoginPassword.requestFocus();
            mEditTextLoginPassword.setError("Please Enter Valid Password");

        }


    }

    @Override
    public void updateUi(boolean status, int actionID, Object serviceResponse) {
        if (serviceResponse instanceof LoginModel) {
            LoginModel loginModel = (LoginModel) serviceResponse;
            TokenDataModel tokenDataModel = loginModel.getData();
            token = tokenDataModel.getAuthToken();
            uniqueIdKey = tokenDataModel.getUserUniqueId();

            Intent goToResortsListActivityIntent = new Intent(LoginActivity.this, ResortsListActivity.class);
            goToResortsListActivityIntent.putExtra("token", token);
            goToResortsListActivityIntent.putExtra("uniqueIdKey", uniqueIdKey);

            startActivity(goToResortsListActivityIntent);
            finish();
        }

    }

    @Override
    public void onEvent(int eventId, Object eventData) {

    }

    @Override
    public void getData(int actionID) {
        switch (actionID) {
            case 1:
                loginRequestFunction();

                break;

        }

    }

    public void loginRequestFunction() {
        JsonObject jsonObjectToSendData = new JsonObject();
        jsonObjectToSendData.addProperty("userId", loginId);
        jsonObjectToSendData.addProperty("password", password);
        RequestManager.initializeWith(getApplicationContext(), new RequestManager.Config("data/data/DSD/pics", 5242880, 4));
        RequestManager.addRequest(new GsonObjectRequest<LoginModel>(urlOfLogin, new HashMap<String, String>(), jsonObjectToSendData.toString(), LoginModel.class, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected void deliverResponse(LoginModel response) {
                updateUi(true, actionIdOfLogin, response);
            }
        });

    }

    private void crossFade() {
        // Set the content view to 0% opacity but visible, so that it is visible
        // (but fully transparent) during the animation.

        mLinearLayoutContent.setAlpha(0f);
        mLinearLayoutContent.setVisibility(VISIBLE);
        mProgressBar.animate().alpha(0f).setDuration(mShortAnimationDuration).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mProgressBar.setVisibility(View.GONE);
            }
        });
    }

}
