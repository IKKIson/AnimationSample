package com.example.animationsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.animationsample.Util.Animation.Lottie.LottieAssets;

public class LottieActivity extends AppCompatActivity {

    private TextView textLottieName = null;
    private LottieAnimationView animationView = null;
    private LottieAssets lottieAssets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anime_lottie);

        this.InitLottiView();

    }
    // Init & Set Lotti Views in Layout
    public void InitLottiView(){
        textLottieName = (TextView) findViewById(R.id.textLottieName);

        lottieAssets = new LottieAssets();

        animationView = (LottieAnimationView) findViewById(R.id.animation_view);
        animationView.setAnimation(lottieAssets.getlottieJsonName(lottieAssets.currentLottie));
        this.AnimationStart();
    }
    public void AnimationPause(){
        animationView.loop(false);
        animationView.pauseAnimation();
    }
    public void AnimationStart(){
        animationView.loop(true);
        animationView.playAnimation();
        textLottieName.setText(lottieAssets.getlottieJsonName(lottieAssets.currentLottie));
    }
    public void AnimationPrev(){
        AnimationPause();
        if(lottieAssets.currentLottie + 1 == lottieAssets.getLottieCount()) {
            lottieAssets.currentLottie = lottieAssets.getLottieCount() - 1;
        } else {
            --lottieAssets.currentLottie;
        }
        AnimationStart();
    }
    public void AnimationNext(){
        AnimationPause();
        if(lottieAssets.currentLottie + 1 == lottieAssets.getLottieCount()) {
            lottieAssets.currentLottie = 0;
        } else {
            ++lottieAssets.currentLottie;
        }
        AnimationStart();
    }

    /*
   Define Event OnClickListener
    */
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btnDoLottieTest:
                    if(animationView.isAnimating()) {
                        AnimationPause();
                    } else {
                        AnimationStart();
                    }
                    break;
                case R.id.btnPrev:
                    AnimationPrev();
                    break;
                case R.id.btnNext:
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
