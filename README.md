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
