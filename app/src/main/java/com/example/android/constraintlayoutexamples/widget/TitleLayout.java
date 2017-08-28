package com.example.android.constraintlayoutexamples.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.constraintlayoutexamples.R;


/**
 * Created by dumingwei on 2017/8/28.
 */
public class TitleLayout extends RelativeLayout {

    private final String TAG = getClass().getSimpleName();

    private TextView textTitle;
    private String title;
    private ImageView imgLeftBack;
    private ImageView imgRightBack;

    //返回键是在左边还是右边
    private boolean rightBack;

    public TitleLayout(Context context) {
        this(context, null);
    }

    public TitleLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(final Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.rl_title, this);
    }
}
