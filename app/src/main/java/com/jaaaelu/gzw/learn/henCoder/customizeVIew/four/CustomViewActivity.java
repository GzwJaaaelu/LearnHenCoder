package com.jaaaelu.gzw.learn.henCoder.customizeVIew.four;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.jaaaelu.gzw.learn.henCoder.R;

public class CustomViewActivity extends AppCompatActivity {

    private MaterialEditText mMaterialEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_four);

        mMaterialEditText = findViewById(R.id.met_my_edit_text);

        CheckBox openLabel = findViewById(R.id.cb_open_label);
        openLabel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mMaterialEditText.setUseFloatingLabel(isChecked);
            }
        });
    }
}
