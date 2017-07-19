package com.example.addpicview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by yzy on 17-5-10.
 */

public class AddPicView extends RelativeLayout implements AddPicCallBack {

    private Context mContext;
    private ScallerGridView mGridView;
    private TextView addText;
    private GridViewAdapter mAdapter;
    private ArrayList<Uri> listData;//最终我们要选择的图片的uri都在这个集合里面
    private Uri mAddUri = Uri.parse("");//占位用,用来显示那个加号
    private int picCounts = 8;//可添加图片数量，默认8
    private CustomClickCallBack mCustomClickCallBack;

    public AddPicView(Context context) {
        super(context);
        mContext = context;
    }

    public AddPicView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        TypedArray vArray = getResources().obtainAttributes(attrs, R.styleable.AddPicView);
        int numColumns = vArray.getInteger(R.styleable.AddPicView_numColumns, 4);
        View vView = LayoutInflater.from(context).inflate(R.layout.addpicview, this, true);
        mGridView = (ScallerGridView) vView.findViewById(R.id.pic_gridview);
        mGridView.setNumColumns(numColumns);
        addText = (TextView) findViewById(R.id.add_img);
        mAdapter = new GridViewAdapter(context, this);
        listData = new ArrayList<>();
        listData.add(mAddUri);
        mGridView.setAdapter(mAdapter);
        mAdapter.setData(listData);
    }


    @Override
    public void onDeleteClick(int position) {
        //点击删除按钮回调
        listData.remove(position);
        if (!listData.get(listData.size() - 1).equals(mAddUri)) {
            listData.add(mAddUri);
        }
        if (listData.size() == 1) {
            addText.setVisibility(View.VISIBLE);
        }
        mAdapter.setData(listData);
    }

    @Override
    public void onAddClick() {
        //如果没设置点击回调就跳转到系统相册，否则执行自定义到自定义跳转到拍照等操作
        if(mCustomClickCallBack == null) {
            Intent innerIntent = new Intent(Intent.ACTION_GET_CONTENT); //"android.intent.action.GET_CONTENT"
            innerIntent.setType("image/*"); //查看类型 String IMAGE_UNSPECIFIED = "image/*";
            Intent wrapperIntent = Intent.createChooser(innerIntent, null);
            if(mContext instanceof Activity) {
                Activity ac = (Activity) mContext;
                ac.startActivityForResult(wrapperIntent, 0x01);
            }
        }else {
            mCustomClickCallBack.customAddClick();
        }
    }

    /**
     * 添加图片的uri
     * @param pUri
     */
    public void addPicture(Uri pUri) {
        addText.setVisibility(View.GONE);
        listData.remove(mAddUri);
        listData.add(pUri);
        if (mAdapter.getCount() != picCounts) {
            listData.add(mAddUri);
        }
        mAdapter.setData(listData);
    }

    /**
     * 设置最大图片数量，不设置默认为8张
     * @param count
     */
    public void setMaxCounts(int count) {
        picCounts = count;
    }

    /**
     * 设置自定义到加号点击回调，不设至用默认到
     * @param pCallBack
     */
    public void setCustomClickCallBack(CustomClickCallBack pCallBack){
        mCustomClickCallBack = pCallBack;
    }

    /**
     * 设置图片到列数，如果不设至默认为4列
     * @param numColumns
     */
    public void setNumColumns(int numColumns){
        mGridView.setNumColumns(numColumns);
    }

}
