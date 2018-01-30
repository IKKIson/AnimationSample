package com.example.animationsample.Util.Animation.Lottie;

/**
 * Created by 손익현 on 2018-01-30.
 */

public class LottieAssets {
    private String lottieJsonName[];
    public int currentLottie;

    public LottieAssets(){
        this.setLottieJsons();
        currentLottie = 0;
    }

    public void setLottieJsons(){
       lottieJsonName = new String[]{
               "/lottie/checked_done_.json"
               , "/lottie/favourite_app_icon.json"
               , "/lottie/md_logo_animation.json"
               , "/lottie/diwali.json"
               , "/lottie/loader.json"
               , "/lottie/scanning_animation.json"
               , "/lottie/zeta_diwali.json"
       };
    }
    public int getLottieCount(){ return lottieJsonName.length; }
    public String getlottieJsonName(int jsonIndex){ return lottieJsonName[jsonIndex]; }

}