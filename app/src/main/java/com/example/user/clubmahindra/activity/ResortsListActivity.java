package com.example.user.clubmahindra.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.user.clubmahindra.R;
import com.example.user.clubmahindra.adapter.MyRecyclerAdapter;
import com.example.user.clubmahindra.constants.Constants;
import com.example.user.clubmahindra.model.ResortModel;
import com.example.user.clubmahindra.model.Resorts;
import com.kelltontech.ui.IScreen;
import com.kelltontech.volley.ext.GsonObjectRequest;
import com.kelltontech.volley.ext.RequestManager;

import java.util.ArrayList;
import java.util.HashMap;

public class ResortsListActivity extends AppCompatActivity implements IScreen, View.OnClickListener, MyRecyclerAdapter.OnItemClick {
    private ResortModel resortModel;
    private ArrayList<String> imageUriArrayList;
    private ArrayList<Resorts> resorts;
    private String token;
    private String uniqueKey;
    private ImageView backPressImageView;
    private TextView mTextViewTotalResorts;
    private RecyclerView mRecyclerView;
    private MyRecyclerAdapter mRecycleViewAdapters;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resorts_list);
        setId();
        setListenersOnView();
        getIntentAndCallApi();
    }

    private void setListenersOnView() {
        backPressImageView.setOnClickListener(this);
    }

    private void setId() {
        backPressImageView = (ImageView) findViewById(R.id.backPressImageView);
        mTextViewTotalResorts = (TextView) findViewById(R.id.totalResortsTextViewId);


    }


    @Override
    public void updateUi(boolean status, int actionID, Object serviceResponse) {
        if (serviceResponse instanceof ResortModel) {
            resortModel = (ResortModel) serviceResponse;

            mTextViewTotalResorts.setText(resortModel.getTotalCount() + String.valueOf(" Resorts"));

            setAdapter();

        }

    }


    private void setAdapter() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycleView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);      //It defines the layout of the recycler view
        mRecyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(ResortsListActivity.this, layoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);
        resorts = resortModel.getResorts();
        imageUriArrayList = resorts.get(1).getAboutImgURL();
        mRecycleViewAdapters = new MyRecyclerAdapter(imageUriArrayList, ResortsListActivity.this, resortModel);
        mRecyclerView.setAdapter(mRecycleViewAdapters);


    }

    @Override
    public void onEvent(int eventId, Object eventData) {

    }

    @Override
    public void getData(int actionID) {
        switch (actionID) {
            case 2:
                HashMap<String, String> headerHashMap = new HashMap<>();

                headerHashMap.put("token", token);
                headerHashMap.put("unique_id_key", uniqueKey);
                RequestManager.initializeWith(getApplicationContext(), new RequestManager.Config("data/data/DSD/pics", 5242880, 4));
                RequestManager.addRequest(new GsonObjectRequest<ResortModel>(Constants.urlOfResort, headerHashMap, null, ResortModel.class, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    @Override
                    protected void deliverResponse(ResortModel response) {
                        updateUi(true, Constants.actionIdOfResort, response);
                    }
                });
                break;
        }

    }

    private void getIntentAndCallApi() {
        Intent intent = getIntent();
        if (intent != null) {
            token = (String) intent.getSerializableExtra("token");
            uniqueKey = (String) intent.getSerializableExtra("uniqueIdKey");

            getData(Constants.actionIdOfResort);

        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.backPressImageView:
               /* Intent gotoLoginScreenIntent = new Intent(ResortsListActivity.this, LoginActivity.class);
                startActivity(gotoLoginScreenIntent);*/
                finish();
                break;
        }
    }


    @Override
    public void clickOnView(int resortId) {

        Intent goToResortDetailsActivityIntent = new Intent(ResortsListActivity.this, ResortsDetailActivity.class);
        goToResortDetailsActivityIntent.putExtra("token", token);
        goToResortDetailsActivityIntent.putExtra("uniqueIdKey", uniqueKey);
        goToResortDetailsActivityIntent.putExtra("resortId", resortId);
        startActivity(goToResortDetailsActivityIntent);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
  /* Intent goToLoginActivityIntent =new Intent(ResortsListActivity.this, LoginActivity.class);
        startActivity(goToLoginActivityIntent);*/
        finish();
    }
}
