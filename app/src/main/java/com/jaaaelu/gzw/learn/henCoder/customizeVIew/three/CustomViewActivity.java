package com.jaaaelu.gzw.learn.henCoder.customizeVIew.three;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jaaaelu.gzw.learn.henCoder.R;

public class CustomViewActivity extends AppCompatActivity {
    NewCameraView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_three);

        view = findViewById(R.id.view);

        ObjectAnimator flipRotationFlipAnim =  ObjectAnimator.ofFloat(view, "flipRotation", 270);
        ObjectAnimator bottomFlipAnim =  ObjectAnimator.ofFloat(view, "bottomFlip", 45);
        ObjectAnimator topFlipAnim =  ObjectAnimator.ofFloat(view, "topFlip", -45);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(bottomFlipAnim, flipRotationFlipAnim, topFlipAnim);
        animatorSet.setStartDelay(1000);
        animatorSet.setDuration(1500);
        animatorSet.start();
    }
}
