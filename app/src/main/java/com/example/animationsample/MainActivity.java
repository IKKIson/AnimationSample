package com.example.animationsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

//import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {

    //private LottieAnimationView animationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);
        //상태 바 없애기
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //스크린 가로 모드
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //this.InitLottiView();


    }
    // Init & Set Lotti Views in Layout
    /*
    public void InitLottiView(){
        animationView = (LottieAnimationView) findViewById(R.id.animation_view);
        animationView.setAnimation("hello-world.json");
        animationView.loop(true);
        animationView.playAnimation();
    }
    */
}
