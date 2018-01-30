package com.example.animationsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnLottie, btnGrav;
    private long pressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);
        //상태 바 없애기
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //스크린 가로 모드
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        this.setViewInLayout();
    }

    /*
    Set, Init Views in Layout
     */
    public void setViewInLayout(){
        btnLottie = (Button)findViewById(R.id.btnNext);
        btnGrav = (Button)findViewById(R.id.btnGrav);

        btnLottie.setOnClickListener(this.onClickListener);
        btnGrav.setOnClickListener(this.onClickListener);
    }

    /*
    Define Event OnClickListener
     */
    View.OnClickListener onClickListener = new View.OnClickListener() {
        Intent intent;
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btnLottie:
                    intent = new Intent(getApplicationContext(), LottieActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btnGrav:
                    intent = new Intent(getApplicationContext(), GravActivity.class);
                    startActivity(intent);
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
        if (pressedTime == 0) {
            Toast.makeText(getApplicationContext(), " 한 번 더 누르면 종료됩니다.", Toast.LENGTH_LONG).show();
            pressedTime = System.currentTimeMillis();
        } else {
            int seconds = (int) (System.currentTimeMillis() - pressedTime);
            if (seconds > 2000) {
                Toast.makeText(getApplicationContext(), " 한 번 더 누르면 종료됩니다.", Toast.LENGTH_LONG).show();
                pressedTime = 0;
            } else {
                super.onBackPressed();
                moveTaskToBack(true);
                finish();
                android.os.Process.killProcess(android.os.Process.myPid());
                ActivityCompat.finishAffinity(this);
                System.runFinalizersOnExit(true);
                System.exit(0);
            }
        }
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
