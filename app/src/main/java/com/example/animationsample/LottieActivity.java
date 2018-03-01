package com.example.animationsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.animationsample.Util.Animation.Lottie.LottieAssets;

public class LottieActivity extends AppCompatActivity {

    private TextView textLottieName = null;
    private LottieAnimationView animationView = null;
    private LottieAssets lottieAssets;
    private Button btnNextLottie, btnPrevLottie, btnDoLottieTest;

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

        btnNextLottie = (Button) findViewById(R.id.btnNextLottie);
        btnPrevLottie = (Button) findViewById(R.id.btnPrevLottie);
        btnDoLottieTest = (Button) findViewById(R.id.btnDoLottieTest);

        btnNextLottie.setOnClickListener(onClickListener);
        btnPrevLottie.setOnClickListener(onClickListener);
        btnDoLottieTest.setOnClickListener(onClickListener);

        this.AnimationStart();
    }

    /*
     * Animation Contoller
     */
    public void AnimationCancel(){
        animationView.loop(false);
        animationView.cancelAnimation();
    }
    public void AnimationPause(){
        animationView.loop(false);
        animationView.pauseAnimation();
    }
    public void AnimationStart(){
        animationView.postInvalidate();
        textLottieName.setText(lottieAssets.getlottieJsonName(lottieAssets.currentLottie));
        animationView.setAnimation(lottieAssets.getlottieJsonName(lottieAssets.currentLottie));
        animationView.playAnimation();
        animationView.loop(true);
    }
    public void AnimationPrev(){
        this.AnimationCancel();
        if(lottieAssets.currentLottie == 0) {
            lottieAssets.currentLottie = lottieAssets.getLottieCount() - 1;
        } else {
            --lottieAssets.currentLottie;
        }
        this.AnimationStart();
    }
    public void AnimationNext(){
        this.AnimationCancel();
        if(lottieAssets.currentLottie + 1 == lottieAssets.getLottieCount()) {
            lottieAssets.currentLottie = 0;
        } else {
            ++lottieAssets.currentLottie;
        }
        this.AnimationStart();
    }
    public void AnimationClear(){
        animationView.loop(false);
        animationView.cancelAnimation();
        animationView.clearAnimation();
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
                case R.id.btnPrevLottie:
                    AnimationPrev();
                    break;
                case R.id.btnNextLottie:
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
        this.AnimationClear();
        finish();
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.AnimationClear();
        finish();
    }
}
