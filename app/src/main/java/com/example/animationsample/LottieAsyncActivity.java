package com.example.animationsample;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.example.animationsample.Util.Animation.Lottie.LottieAssets;

public class LottieAsyncActivity extends AppCompatActivity {

    private LottieAnimationView animationView = null;
    private LottieAssets lottieAssets;
    private FrameLayout.LayoutParams LayoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anime_lottie_async);
        this.InitLottiView();
    }

    // Init & Set Lotti Views in Layout
    public void InitLottiView() {
        this.lottieAssets = new LottieAssets();
        this.animationView = (LottieAnimationView) findViewById(R.id.asyncAnimeView);
        this.animationView.setAnimation(lottieAssets.getlottieJsonName(0));
        this.LayoutParams = (FrameLayout.LayoutParams) animationView.getLayoutParams();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        DisplayMetrics metrics = getApplicationContext().getResources().getDisplayMetrics();
        LayoutParams.setMargins(
                (int) (event.getX() / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEVICE_STABLE)) - this.animationView.getLayoutParams().width / 2,
                (int) (event.getY() / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEVICE_STABLE)) - this.animationView.getLayoutParams().height / 2,
                0,
                0
        );
        this.animationView.setLayoutParams(LayoutParams);
        this.animeThread.start();
        return super.onTouchEvent(event);
    }

    /**
     * Lottie Animation Async Thread
     * Handler, Thread - Runnable
     */
    @SuppressLint("HandlerLeak")
    private Handler animeHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            try {
                StartAnimation();
                Thread.sleep(animationView.getDuration());
            } catch (InterruptedException e) {
                e.printStackTrace();
                CancelAnimation();
            } finally {
                PauseAnimation();
            }//end of catch
        }//end of handleMessage
    };//end of Handler Constructor
    private Thread animeThread = new Thread(new Runnable() {
        public void run() {
            try {
                animeHandler.sendMessage(animeHandler.obtainMessage());
            } catch (Throwable t) {
                ClearAnimation();
            } //end of catch
        }//end of run()
    });//end of Thread Constructor

    /**
     * Lottie Animation Contoller
     * Async Touch Event
     */
    public void CancelAnimation() {
        this.animationView.cancelAnimation();
        this.animationView.setVisibility(View.GONE);
    }

    public void PauseAnimation() {
        this.animationView.pauseAnimation();
        this.animationView.setVisibility(View.INVISIBLE);
    }

    public void StartAnimation() {
        this.animationView.setVisibility(View.VISIBLE);
        this.animationView.playAnimation();
    }

    public void ClearAnimation() {
        this.CancelAnimation();
        this.animationView.clearAnimation();
    }

    /**
     * Take care of popping the fragment back stack or finishing the activity
     * as appropriate.
     */
    private void ClearActivityView() {
        this.ClearAnimation();
        this.animeThread.destroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.ClearActivityView();
        finish();
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.ClearActivityView();
        finish();
    }
}
