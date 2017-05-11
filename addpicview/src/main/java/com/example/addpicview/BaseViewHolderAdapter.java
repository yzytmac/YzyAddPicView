package com.example.addpicview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseViewHolderAdapter<DATA,VIEWHOLDER> extends BaseAdapter {
	
	protected Context mCtx;
	protected LayoutInflater mInflater;

	protected List<DATA> mDatas;
	
	public BaseViewHolderAdapter(Context ctx){
		mCtx = ctx;
		mInflater = LayoutInflater.from(mCtx);
	}

	public void setData(List<DATA> datas){
		if(mDatas == null){
			mDatas = new ArrayList<DATA>();
		}
		mDatas.clear();
		mDatas.addAll(datas);
		this.notifyDataSetChanged();
	}
	
	public void addData(DATA data){
		if(mDatas == null){
			mDatas = new ArrayList<DATA>();
		}
		mDatas.add(data);
		this.notifyDataSetChanged();
	}
	
	public void addData(int idx,DATA data){
		if(mDatas == null){
			mDatas = new ArrayList<DATA>();
		}
		mDatas.add(idx,data);
		this.notifyDataSetChanged();
	}
	
	public void addData(List<DATA> datas){
		if(mDatas == null){
			mDatas = new ArrayList<DATA>();
		}
		mDatas.addAll(datas);
		this.notifyDataSetChanged();
	}

	public List<DATA> getList() {
		return mDatas;
	}
	
	@Override
	public int getCount() {
		if(mDatas != null){
			return mDatas.size();
		}
		return 0;
	}
	
	public void removeItem(DATA model){
		if (mDatas==null||mDatas.isEmpty()) {
			return;
		}
		mDatas.remove(model);
	}
	public void removeItem(int position){
		if (mDatas==null||mDatas.isEmpty()) {
			return;
		}
		mDatas.remove(position);
	}
	
	@Override
	public Object getItem(int position) {
		if(mDatas != null){
			return mDatas.get(position);
		}
		return null;
	}
	
	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		VIEWHOLDER holder = null;
		if(view == null){
			view = createItemView(mInflater,position);
			holder = createAndBindViewHolder(view,position);
			view.setTag(holder);
		}else{
			holder = (VIEWHOLDER)view.getTag();
		}
		
		DATA data = mDatas.get(position);
		
		bindDataToView(holder, data, position);
		
		return view;
	}

	public abstract View createItemView(LayoutInflater inflater,int position);
	
	public abstract VIEWHOLDER createAndBindViewHolder(View view,int position);
	
	public abstract void bindDataToView(VIEWHOLDER holder,DATA data,int position);
	
}
