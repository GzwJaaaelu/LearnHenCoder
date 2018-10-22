package com.jaaaelu.gzw.learn.henCoder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * 自定义 View 方法是重写绘制方法，其中最常用的是 onDraw()
 * 绘制的关键是 Canvas 的使用：
 * - Canvas 的绘制类方法：drawXXX()（关键参数：Paint）
 * - Canvas 的辅助类方法：范围裁切和几何变换
 * 可以使用不同的绘制方法来控制遮盖关系
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
