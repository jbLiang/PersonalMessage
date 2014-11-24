package com.jbLiang.message.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jbLiang.message.data.MessageListData;
import com.jbLiang.messagedemo.R;

public class MessageListAdapter extends BaseAdapter{
	
	public MessageListAdapter( Context context, ArrayList<MessageListData> messageList ){
		this.mContext = context;
		inflater = LayoutInflater.from(mContext);
		mMessageList = messageList;
	}
	
	public void setMessageList( ArrayList<MessageListData>  messageList ){
		mMessageList = messageList;
	}

	@Override
	public int getCount() {
		return mMessageList.size();
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		if( convertView == null ){
			mViewHolder = new ViewHolder();
			convertView = inflater.inflate(R.layout.message_listview_layout, null);
			mViewHolder.imageView = (ImageView)convertView.findViewById(R.id.messageImgId);
			mViewHolder.titleView = ( TextView )convertView.findViewById(R.id.titleTvId);
			mViewHolder.contentView = ( TextView )convertView.findViewById(R.id.contentTvId);
			mViewHolder.timeView = ( TextView )convertView.findViewById(R.id.timeTvId);
			mViewHolder.deleteButton = ( Button )convertView.findViewById(R.id.deleteButton);
			convertView.setTag(mViewHolder);
			mViewHolder.deleteButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					if( position < mMessageList.size() && position >= 0){
						mMessageList.remove(position);
						Log.d( "ljb", "remove position = " + position );
						notifyDataSetChanged();
					}
				}
			});
		}
		else{
			mViewHolder = ( ViewHolder )convertView.getTag();
		}
		
		if( mIsdeleteVisable == true ){
			mViewHolder.deleteButton.setVisibility(View.VISIBLE);
		}else{
			mViewHolder.deleteButton.setVisibility(View.INVISIBLE);
		}
		setMessageList( position );
		
		
		return convertView;
	}
	
	private void setMessageList( int position ){
		if( mMessageList== null || position < 0 || position >= mMessageList.size() ){
			return;
		}
		mViewHolder.titleView.setText( mMessageList.get(position).getTitle() );
		mViewHolder.contentView.setText( mMessageList.get(position).getContent());
	}
	
	public void setdeleteVisable( Boolean isdeleteBoolean ){
		mIsdeleteVisable = isdeleteBoolean;
	}
	
	class ViewHolder{
		ImageView imageView = null;       //图片
		TextView     titleView  =  null;         //标题
		TextView     contentView = null;   //内容
		TextView     timeView = null;         //时间
		Button        deleteButton = null;  //删除按钮
	}
	
	private Context mContext = null;
	private LayoutInflater inflater = null;
	private ViewHolder mViewHolder = null;
	private ArrayList<MessageListData> mMessageList = null;
	
	private Boolean mIsdeleteVisable = false;
}
