package com.example.user.clubmahindra.activity;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.user.clubmahindra.R;
import com.example.user.clubmahindra.adapter.ViewPageAdapters;
import com.example.user.clubmahindra.constants.Constants;
import com.example.user.clubmahindra.fragment.AboutFragment;
import com.example.user.clubmahindra.fragment.ReviewFragment;
import com.example.user.clubmahindra.fragment.ThingsToDoInFragment;
import com.example.user.clubmahindra.fragment.WayToReachFragment;
import com.example.user.clubmahindra.model.ResortDetailsModel;
import com.google.gson.JsonObject;
import com.kelltontech.ui.IScreen;
import com.kelltontech.volley.ext.GsonObjectRequest;
import com.kelltontech.volley.ext.RequestManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class ResortsDetailActivity extends AppCompatActivity implements IScreen {
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPageAdapters viewPagerAdapter;
    private String token;
    private String uniqueKey;
    private int resortID;
    private ImageView setResortImageView;
    private ResortDetailsModel resortDetailModel;
    private TextView mAboutResortTextView;
    private TextView mAddressTextView;
    private TextView mEmailTextView;
    private TextView mPhoneTextView;
    private TextView mSummerTextView;
    private TextView mWinterTextView;
    private TextView mBestTimeVisitTextView;
    private TextView mResortNameTextView;
    private String titleReview = "Review";
    private String titleAbout = "About";
    private String titleWayToReach = "Way To Reach";
    private String titleThingsTODo = "Things TO DO";
    private Animator mCurrentAnimator;
    private int mShortAnimationDuration;
    private       Uri uri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resorts_detail);
        getIntentData();

        setId();
        setViewPager();

        getData(Constants.actionIdOfResortDetails);
        mShortAnimationDuration = getResources().getInteger(
                android.R.integer.config_shortAnimTime);

    }

    private void setViewPager() {
        viewPagerAdapter = new ViewPageAdapters(getSupportFragmentManager());
        viewPagerAdapter.addFragments(new AboutFragment(), titleAbout);
        viewPagerAdapter.addFragments(new ThingsToDoInFragment(), titleThingsTODo);
        viewPagerAdapter.addFragments(new WayToReachFragment(), titleWayToReach);
        viewPagerAdapter.addFragments(new ReviewFragment(), titleReview);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setId() {

        setResortImageView = (ImageView) findViewById(R.id.showImageOfResortId);

        mSummerTextView = (TextView) findViewById(R.id.summerTextViewId);
        mWinterTextView = (TextView) findViewById(R.id.winterTextViewId);
        mBestTimeVisitTextView = (TextView) findViewById(R.id.bestTimeToVisitTextView);
        mResortNameTextView = (TextView) findViewById(R.id.resortNameTextViewId);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

    }

    private void getIntentData() {

        Intent intent = getIntent();
        if (intent != null) {
            token = (String) intent.getSerializableExtra("token");
            uniqueKey = (String) intent.getSerializableExtra("uniqueIdKey");
            resortID = (int) intent.getSerializableExtra("resortId");


        }

    }


    @Override
    public void updateUi(boolean status, int actionID, Object serviceResponse) {
        if (serviceResponse instanceof ResortDetailsModel) {
            resortDetailModel = (ResortDetailsModel) serviceResponse;
            ArrayList<String> showImageArrayList = resortDetailModel.getData().getAboutImgURL();
            uri = Uri.parse(String.valueOf(showImageArrayList.get(0)));
            Picasso.with(ResortsDetailActivity.this).load(uri).into(setResortImageView);
            setResortImageView.setColorFilter(Color.rgb(200, 200, 200), PorterDuff.Mode.DARKEN);
            setResortImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in_animation);
                  /* zoomImageFromThumb(setResortImageView,uri);*/
                }
            });
            mSummerTextView.setText(String.valueOf(resortDetailModel.getData().getTemperature().get(0).getSummer()));
            mWinterTextView.setText(String.valueOf(resortDetailModel.getData().getTemperature().get(0).getWinter()));
            mBestTimeVisitTextView.setText(String.valueOf(resortDetailModel.getData().getBestTimetoTravel()));
            mResortNameTextView.setText(String.valueOf(resortDetailModel.getData().getResortShortname()));
          /*  Toast.makeText(ResortsDetailActivity.this,resortDetailModel.getResponseCode()+"",Toast.LENGTH_LONG).show();*/
            showAllData();
        }
    }

    private void showAllData() {


        mAddressTextView = (TextView) findViewById(R.id.addressTextViewID);
        mEmailTextView = (TextView) findViewById(R.id.emailAddressEdtiText);
        mPhoneTextView = (TextView) findViewById(R.id.phoneNumberTextViewId);
        mAboutResortTextView = (TextView) findViewById(R.id.aboutResortDataId);
        String aboutResort = resortDetailModel.getData().getAboutResort();
        mAboutResortTextView.setText(aboutResort);
        mAddressTextView.setText(String.valueOf(resortDetailModel.getData().getResortAddress()));
        mEmailTextView.setText(String.valueOf(resortDetailModel.getData().getEmail()));
        mPhoneTextView.setText(String.valueOf(resortDetailModel.getData().getPhone()));

    }

    @Override
    public void onEvent(int eventId, Object eventData) {

    }

    @Override
    public void getData(int actionID) {
        switch (actionID) {
            case 3:
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("resortId", resortID);
                HashMap<String, String> headerHashMap = new HashMap<>();
                headerHashMap.put("token", token);
                headerHashMap.put("unique_id_key", uniqueKey);
                RequestManager.initializeWith(getApplicationContext(), new RequestManager.Config("data/data/DSD/pics", 5242880, 4));
                RequestManager.addRequest(new GsonObjectRequest<ResortDetailsModel>(Constants.getUrlOfResortDetails, headerHashMap, jsonObject.toString(), ResortDetailsModel.class, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }) {
                    @Override
                    protected void deliverResponse(ResortDetailsModel response) {
                        updateUi(true, Constants.actionIdOfResortDetails, response);
                    }
                });
                break;
        }
    }


  /*  private void zoomImageFromThumb(final View thumbView, Uri imageResId) {
        // If there's an animation in progress, cancel it
        // immediately and proceed with this one.
        if (mCurrentAnimator != null) {
            mCurrentAnimator.cancel();
        }

        // Load the high-resolution "zoomed-in" image.
        final ImageView expandedImageView = (ImageView) findViewById(
                R.id.expanded_image);
        expandedImageView.setImageResource(imageResId);

        // Calculate the starting and ending bounds for the zoomed-in image.
        // This step involves lots of math. Yay, math.
        final Rect startBounds = new Rect();
        final Rect finalBounds = new Rect();
        final Point globalOffset = new Point();

        // The start bounds are the global visible rectangle of the thumbnail,
        // and the final bounds are the global visible rectangle of the container
        // view. Also set the container view's offset as the origin for the
        // bounds, since that's the origin for the positioning animation
        // properties (X, Y).
        thumbView.getGlobalVisibleRect(startBounds);
        findViewById(R.id.container)
                .getGlobalVisibleRect(finalBounds, globalOffset);
        startBounds.offset(-globalOffset.x, -globalOffset.y);
        finalBounds.offset(-globalOffset.x, -globalOffset.y);

        // Adjust the start bounds to be the same aspect ratio as the final
        // bounds using the "center crop" technique. This prevents undesirable
        // stretching during the animation. Also calculate the start scaling
        // factor (the end scaling factor is always 1.0).
        float startScale;
        if ((float) finalBounds.width() / finalBounds.height()
                > (float) startBounds.width() / startBounds.height()) {
            // Extend start bounds horizontally
            startScale = (float) startBounds.height() / finalBounds.height();
            float startWidth = startScale * finalBounds.width();
            float deltaWidth = (startWidth - startBounds.width()) / 2;
            startBounds.left -= deltaWidth;
            startBounds.right += deltaWidth;
        } else {
            // Extend start bounds vertically
            startScale = (float) startBounds.width() / finalBounds.width();
            float startHeight = startScale * finalBounds.height();
            float deltaHeight = (startHeight - startBounds.height()) / 2;
            startBounds.top -= deltaHeight;
            startBounds.bottom += deltaHeight;
        }

        // Hide the thumbnail and show the zoomed-in view. When the animation
        // begins, it will position the zoomed-in view in the place of the
        // thumbnail.
        thumbView.setAlpha(0f);
        expandedImageView.setVisibility(View.VISIBLE);

        // Set the pivot point for SCALE_X and SCALE_Y transformations
        // to the top-left corner of the zoomed-in view (the default
        // is the center of the view).
        expandedImageView.setPivotX(0f);
        expandedImageView.setPivotY(0f);

        // Construct and run the parallel animation of the four translation and
        // scale properties (X, Y, SCALE_X, and SCALE_Y).
        AnimatorSet set = new AnimatorSet();
        set
                .play(ObjectAnimator.ofFloat(expandedImageView, View.X,
                        startBounds.left, finalBounds.left))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.Y,
                        startBounds.top, finalBounds.top))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X,
                        startScale, 1f)).with(ObjectAnimator.ofFloat(expandedImageView,
                View.SCALE_Y, startScale, 1f));
        set.setDuration(mShortAnimationDuration);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mCurrentAnimator = null;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                mCurrentAnimator = null;
            }
        });
        set.start();
        mCurrentAnimator = set;

        // Upon clicking the zoomed-in image, it should zoom back down
        // to the original bounds and show the thumbnail instead of
        // the expanded image.
        final float startScaleFinal = startScale;
        expandedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrentAnimator != null) {
                    mCurrentAnimator.cancel();
                }

                // Animate the four positioning/sizing properties in parallel,
                // back to their original values.
                AnimatorSet set = new AnimatorSet();
                set.play(ObjectAnimator
                        .ofFloat(expandedImageView, View.X, startBounds.left))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.Y,startBounds.top))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.SCALE_X, startScaleFinal))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.SCALE_Y, startScaleFinal));
                set.setDuration(mShortAnimationDuration);
                set.setInterpolator(new DecelerateInterpolator());
                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        mCurrentAnimator = null;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        mCurrentAnimator = null;
                    }
                });
                set.start();
                mCurrentAnimator = set;
            }
        });
    }*/
}