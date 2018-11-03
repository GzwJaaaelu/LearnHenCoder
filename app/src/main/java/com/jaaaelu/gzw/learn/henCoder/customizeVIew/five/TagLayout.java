package com.jaaaelu.gzw.learn.henCoder.customizeVIew.five;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class TagLayout extends ViewGroup {
    //  存 onMeasure 里面计算出来的大小
    List<Rect> mChildBounds = new ArrayList<>();

    public TagLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthUsed = 0;
        int heightUsed = 0;
        int lineWidthUsed = 0;
        int lineMaxHeight = 0;
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specWidth = MeasureSpec.getSize(widthMeasureSpec);
        for (int i = 0; i < getChildCount(); i++) {
            //  计算出每个子 View 的尺寸
            View child = getChildAt(i);
//            measureChildWithMargins(child, widthMeasureSpec, widthUsed, heightMeasureSpec, heightUsed);
            //  告诉子 view 随便用
            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed);

            //  已用宽度 + 子 view 宽度 > 我的宽度，准备换行
            if (specMode != MeasureSpec.UNSPECIFIED
                    && lineWidthUsed + child.getMeasuredWidth() > specWidth) {
                lineWidthUsed = 0;
                heightUsed += lineMaxHeight;
                lineMaxHeight = 0;
                measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed);
            }

            //  存子 View 的尺寸
            Rect bounds;
            if (mChildBounds.size() <= i) {
                bounds = new Rect();
                mChildBounds.add(bounds);
            } else {
                bounds = mChildBounds.get(i);
            }
            bounds.set(lineWidthUsed, heightUsed,
                    lineWidthUsed + child.getMeasuredWidth(), heightUsed + child.getMeasuredHeight());

            //  更新使用过的宽度，也就是现在的宽度 + 子 view 的宽度
            lineWidthUsed += child.getMeasuredWidth();
            widthUsed = Math.max(widthUsed, lineWidthUsed);
            lineMaxHeight = Math.max(lineMaxHeight, child.getMeasuredHeight());
        }

        //  算自己的尺寸
        int width = widthUsed;
        int hight = heightUsed + lineMaxHeight;
        setMeasuredDimension(width, hight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        layoutTypeOne(l, t, r, b);
        layoutThisGroup(l, t, r, b);
    }

    private void layoutThisGroup(int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            Rect bounds = mChildBounds.get(i);
            view.layout(bounds.left, bounds.top, bounds.right, bounds.bottom);
        }
    }

    private void layoutTypeOne(int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            if (i == 0) {
                View view = getChildAt(i);
                view.layout(l, t, (l + r) / 2, (t + b) / 2);
            } else {
                View view = getChildAt(i);
                view.layout((l + r) / 2, (t + b) / 2, r, b);
            }
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
