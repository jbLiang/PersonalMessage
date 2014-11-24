package com.jbLiang.message.view;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.jbLiang.message.adapter.MessageListAdapter;
import com.jbLiang.message.data.MessageListData;
import com.jbLiang.messagedemo.R;

/**
 * 个人消息页面
 * @author ljb
 */
public class MessageDialog extends AlertDialog implements android.view.View.OnClickListener{

	public MessageDialog(Activity context, ArrayList<MessageListData> messagelist) {
		super(context);
		mContext = context;
		mMessageDataList = messagelist;
		initMessageDialog();
	}
	
	public void showMessageDialog( ){
		new AlertDialog.Builder(mContext).create();
		show();
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.width = LayoutParams.FILL_PARENT;
		lp.height = LayoutParams.FILL_PARENT;
		if( null == mMessageView ){
			return;
		}
		addContentView(mMessageView, lp);
		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
	}
	
	private void initMessageDialog(){
		mInflater = LayoutInflater.from(mContext);
		mMessageView = mInflater.inflate(R.layout.message_dialoglayout, null, false);
		mMessageLayout = (RelativeLayout)mMessageView.findViewById(R.id.messagecontentlayout);
		mTitle = (TextView)mMessageView.findViewById(R.id.titletv);
		mMessageList = ( ListView )mMessageView.findViewById(R.id.messagelistviewId);
		mMessageListAdapter = new MessageListAdapter(mContext, mMessageDataList);
		mMessageList.setAdapter(mMessageListAdapter);
		
		mEditButton = (Button)mMessageView.findViewById(R.id.editbutton);
		mCLoseButton = (Button)mMessageView.findViewById(R.id.closebutton);
		mEditButton.setOnClickListener(this);
		mCLoseButton.setOnClickListener(this);
		
		mMessageList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				
				if(  mState == NORMAL_STATE){
					mState = READING_STATE;
					mEditButton.setText("返回");
					mTitle.setText("消息详情");
					mMessageList.setVisibility(View.INVISIBLE);
					mMessageLayout.setVisibility(View.VISIBLE);
				}
			}
		});
	}

	@Override
	public void onClick(View view) {
		
		switch( view.getId() ){
		case R.id.closebutton:{
			dismiss();
		}
		break;
		case R.id.editbutton:{
			editClickItem();
		}
		break;
		default:
			break;
		}
	}
	
	private void editClickItem(){
		switch( mState ){
		case NORMAL_STATE:{
			mState = DELETE_STATE;
			mEditButton.setText("取消");
			mMessageListAdapter.setdeleteVisable(true);
			mMessageListAdapter.notifyDataSetChanged();
		}
		break;
		case DELETE_STATE:{
			mState = NORMAL_STATE;
			mEditButton.setText("编辑");
			mMessageListAdapter.setdeleteVisable(false);
			mMessageListAdapter.notifyDataSetChanged();
		}
		break;
		case READING_STATE:{
			mState = NORMAL_STATE;
			mTitle.setText("消息列表");
			mEditButton.setText("编辑");
			mMessageList.setVisibility(View.VISIBLE);
			mMessageLayout.setVisibility(View.INVISIBLE);
		}
		break;
		default:
		break;
		}
	}
	
	private Activity mContext = null;
	private LayoutInflater mInflater = null;
	private ArrayList<MessageListData> mMessageDataList = null;
	private Button  mEditButton = null;
	private Button mCLoseButton = null;
	private TextView mTitle = null;
	private RelativeLayout mMessageLayout = null;
	private View mMessageView = null;    //消息框页面
	private ListView mMessageList = null;
	private MessageListAdapter mMessageListAdapter = null;
	
	private int mState = NORMAL_STATE;    //0:正常模式；1: 删除模式 ; 2: 阅览消息模式    
	
	private static final int NORMAL_STATE = 0;
	private static final int DELETE_STATE = 1;
	private static final int READING_STATE = 2;
}
