package com.example.animationsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.animationsample.Util.Animation.Lottie.LottieAssets;

public class SampleActivity extends AppCompatActivity {

    private LottieAnimationView animationView;
    private LottieAssets lottieAssets;
    private Button btnNextSample, btnPrevSample, btnDoSampleTest;
    private TextView textLottieName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anime_sample);
        this.InitLottiView();

    }
    // Init & Set Lotti Views in Layout
    public void InitLottiView(){
        textLottieName = (TextView) findViewById(R.id.textLottieName);

        lottieAssets = new LottieAssets();

        animationView = (LottieAnimationView) findViewById(R.id.animation_view);
        animationView.setAnimation(lottieAssets.getlottieJsonName(lottieAssets.currentLottie));

        btnNextSample = (Button) findViewById(R.id.btnNextSample);
        btnPrevSample = (Button) findViewById(R.id.btnPrevSample);
        btnDoSampleTest = (Button) findViewById(R.id.btnDoSampleTest);

        btnNextSample.setOnClickListener(onClickListener);
        btnPrevSample.setOnClickListener(onClickListener);
        btnDoSampleTest.setOnClickListener(onClickListener);

        this.AnimationStart();
    }

    public void AnimationPause(){
        animationView.loop(false);
        animationView.pauseAnimation();
    }
    public void AnimationStart(){
        textLottieName.setText(lottieAssets.getlottieJsonName(lottieAssets.currentLottie));
        animationView.setAnimation(lottieAssets.getlottieJsonName(lottieAssets.currentLottie));
        animationView.loop(true);
        animationView.playAnimation();
    }
    public void AnimationPrev(){
        this.AnimationPause();
        if(lottieAssets.currentLottie == 0) {
            lottieAssets.currentLottie = lottieAssets.getLottieCount() - 1;
        } else {
            --lottieAssets.currentLottie;
        }
        this.AnimationStart();
    }
    public void AnimationNext(){
        this.AnimationPause();
        if(lottieAssets.currentLottie + 1 == lottieAssets.getLottieCount()) {
            lottieAssets.currentLottie = 0;
        } else {
            ++lottieAssets.currentLottie;
        }
        this.AnimationStart();
    }

    /*
   Define Event OnClickListener
    */
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btnDoSampleTest:
                    if(animationView.isAnimating()) {
                        AnimationPause();
                    } else {
                        AnimationStart();
                    }
                    break;
                case R.id.btnPrevSample:
                    AnimationPrev();
                    break;
                case R.id.btnNextSample:
                    AnimationNext();
                    break;
            }
        }
    };

    /**
     * Take care of popping the fragment back stack or finishing the activity
     * as appropriate.
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

}
