package com.example.addpicview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class ScallerGridView extends GridView {
    public ScallerGridView(Context context) {
        super(context);
    }

    public ScallerGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST); //意思就是取出空间值里面的数值，然后再添加一个模式为atmost，组成一个新的空间值
        super.onMeasure(widthMeasureSpec,expandSpec);
    }
}