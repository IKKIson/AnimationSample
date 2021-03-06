# AnimationSample
Android App Animation Test in Android Studio 3.0

## Error Report

### Version Error After Android Studio 3.0 Update
**1. AAPT2 error**
- Error Report
```markdown
Error:failed processing manifest.
Error:java.util.concurrent.ExecutionException: java.util.concurrent.ExecutionException: com.android.tools.aapt2.Aapt2Exception: AAPT2 error: check logs for details
Error:java.util.concurrent.ExecutionException: com.android.tools.aapt2.Aapt2Exception: AAPT2 error: check logs for details
Error:com.android.tools.aapt2.Aapt2Exception: AAPT2 error: check logs for details
Error:Execution failed for task ':app:processDebugResources'.
> Failed to execute aapt
```
- Aapt2 is enabled by default when you use android plugin for gradle 3.0.
- But if you are facing issues with it, you can switch back to previous version by adding this in gradle.properties
- Solution - gradle.properties
```markdown
android.enableAapt2=false
```

    - [StackOverflow](https://stackoverflow.com/questions/46988102/errorcom-android-tools-aapt2-aapt2exception-aapt2-error-check-logs-for-detail)
    - [About Android Plugin for Gradle 3.0.0](https://developer.android.com/studio/build/gradle-plugin-3-0-0.html)
    
</hr>

**2. No Show a XML layout preview**
- Error Report
```markdown
Exception raised during rendering: Could not initialize class android.graphics.Typeface
java.lang.NoClassDefFoundError: Could not initialize class android.graphics.Typeface
```
- Error Message
```markdown
android studio could not initialize class android.graphics.Typeface
```
- Problem
    - Problems with Breaking of Korean in Layout Preview
    - If you modify fonts.xml in 2.x var, you may get this error if you update to 3.0.
    - If you check the fonts.xml for errors after updating to 3.0, the xml file is broken in the middle.
    - If it is not the default fonts.xml, But the modified fonts.xml, it appears that updating the android studio 3.0 fails to update the fonts.xml.
    ```markdown
    <family lang="ko"> 
      <font weight="400" style="normal" index="1">NanumGothic.ttf</font> 
    </family>
    ```

- Solution
    - Download the modified xml file and replace it with the existing xml file.
    - [Download Link : fonts.xml](https://drive.google.com/file/d/1iUPfiV980llcwG-2NIAWoYUgHh1YsWUZ/view)

</hr>

## Device's Display - Different Screen Sizes and Densities of Anroid OS

### Using Screens

|Screen Dimensions         |Width x Height (PX : Pixel) |width x Height (DP : Density Independent Pixel) |Aspect Ratio |Density          |Directory Naming of layout            |
|--------------------------|----------------------------|------------------------------------------------|-------------|-----------------|--------------------------------------|
|10 Inch(Landscape Tablet) |2560 x 1600                 |1280 x 800                                      |16 : 10      |x2.0 xhdpi       |layout-sw720dp, drawable-xhdpi(drawable-sw720dp-xhdpi)|
|7 Inch(Tablet)            |1200 x 1920                 |600 x 960                                       |16 : 10      |x2.0 xhdpi       |layout-sw600dp, drawable-xhdpi(drawable-sw600dp-xhdpi)|
|Full HD(_Based Display_)  |1080 x 1920                 |360 x 640                                       |16 : 9       |x3.0 xxhdpi      |layout-xxhdpi, drawable-xxhdpi        |
|Full HD(_Some other models_)  |-                |-                                       |-      |x3.0 xxhdpi      |layout-sw400dp-xxhdpi, drawable-xxhdpi        |
|QHD(Quad Full HD)         |1440 x 2960                 |369 x 740                                       |18.5 : 9     |x4.0 xxxhdpi     |layout-xxxhdpi, drawable-xxxhdpi      |

### Screens Directory(in Android OS)
- For smartphones and phablets
    - layout-mdpi
    - layout-hdpi
    - layout-xhdpi
    - layout-xxhdpi
- For 7" tablets
    - layout-sw600dp
- For 10" tablets
    - layout-sw720dp

### Dp(dip), Px, Dpi

1. DPI(Dot Per Inch)

- A unit of pixels representing an inch of a pixel (2.54 cm) per inch.

- ldpi => 120dpi (120px / 1inch)

- mdpi => 160dpi (160px / 1inch)

- hdpi => 240dpi (240px / 1inch)

- xhdpi => 320dpi (320 px / 1inch)

- xxhdpi => 480dpi (480 px / 1inch)

- xxxhdpi => 640dpi (640 px / 1inch)

2. DP(DIP) - Density Independent Pixel
- It is a unit defined for various screens of Android devices regardless of pixel in pixel-independent unit.

- This unit allows you to show objects of the same size on a larger terminal or on a smaller terminal.

- You can optionally apply the layout to use in the layout resource folder.

- After reousrce folder, sw360dp can be applied by selecting xxxhdpi (resource folder supporting 360dp horizontal size xxxhpdi).
    - ex) (folder name) layout-sw360dp-xxxhdpi

3. Pixel(px)

- The actual pixel unit of the screen.

- On the mdpi screen, 1dp is 1pixel.

4. Interchange between Pixel and DP
- The relationship between DP and Pixel can be used to convert from one to another. I think it will be more memorable to understand than to memorize the formula.
```markdown
   px = dp * dpi / 160 = dp * density

   dp = dp * 160 / dpi = px / density

   density = dpi / 160
```

- dpi is a value determined by the terminal
- For example, if the terminal supports mdpi, dpi is 160, so 1dp * 160/160 = 1pixel.
   | In addition, in the case of pixel calculation of 3dp, terminal supporting xxhdpi Since 3dp * 480/160 = 9 pixels, 3dp of xxhdpi terminal becomes 9pixel.

- http://rojhw.tistory.com/23 [grow up]
- [Smart Device Display and Screens](https://material.io/devices/)
- [Material Design](https://material.io/)
- [Display Converter](https://www.pixplicity.com/dp-px-converter)
- (https://developer.android.com/reference/android/util/DisplayMetrics.html)
- (https://stackoverflow.com/questions/29138689/what-layout-folders-are-required-to-support-all-android-devices)
- (https://stackoverflow.com/questions/15090752/how-to-include-10-and-7-layouts-properly)

</hr>

## Animation Library

### Lottie - Effect, View, Check, Loading Animation

#### 1. Set this Library

1. build.gradle - Modules
```markdown
dependencies {
       implementation fileTree(dir: 'libs', include: ['*.jar'])
       implementation 'com.android.support:appcompat-v7:26.1.0'
       implementation 'com.android.support.constraint:constraint-layout:1.0.2'
       testImplementation 'junit:junit:4.12'
       androidTestImplementation 'com.android.support.test:runner:1.0.1'
       androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.1'

       /* Animation Library */
       //Lotti - Android
       //https://github.com/airbnb/lottie-android
       compile 'com.airbnb.android:lottie:2.5.0-beta3'
   }
```

2. Layout
-  Set : xmlns:app="http://schemas.android.com/apk/res-auto"
```markdown
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/activity_main"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <com.airbnb.lottie.LottieAnimationView
        android:id="@+id/animation_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:lottie_fileName="md_logo_animation.json"
        app:lottie_loop="true"
        app:lottie_autoPlay="true" />

</RelativeLayout>
```

3. Using
- 1) Simple (Synchronous)
```markdown
    <com.airbnb.lottie.LottieAnimationView
        android:id="@+id/animation_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:lottie_fileName="md_logo_animation.json"
        app:lottie_loop="true"
        app:lottie_autoPlay="true" />
```
- 2) Programmatically (Asynchronous)
```markdown
[In Java]
        animationView = (LottieAnimationView) findViewById(R.id.animation_view);
        animationView.setAnimation("hello-world.json");
        animationView.loop(true);
        animationView.playAnimation();
```

#### Resource
1. About Lottie
- [lottie android in github](https://github.com/airbnb/lottie-android)
- [airbnb menual](http://airbnb.io/lottie/)

2. .json
- [lottiefiles](https://www.lottiefiles.com)

3. How to use
- https://academy.realm.io/kr/posts/lottie-for-android-interactive-animation/
- https://medium.com/creative-controller/native-app-animations-in-android-studio-using-lottie-from-airbnb-bbc039c87e63



