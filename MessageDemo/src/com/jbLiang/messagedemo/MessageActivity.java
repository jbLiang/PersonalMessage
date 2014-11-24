package com.jbLiang.messagedemo;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.jbLiang.message.data.MessageListData;
import com.jbLiang.message.view.MessageDialog;

/**
 * 消息界面
 * @author jbLiang
 *
 */
public class MessageActivity extends Activity  implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message);
		
	}
	@Override
	public void onClick(View view) {
		switch( view.getId() ){
		case R.id.messageButton:{
			if( mMessageDialog != null ){
				mMessageDialog.cancel();
				mMessageDialog = null;
			}
			mMessagelist = new ArrayList<MessageListData>();
			for( int i = 0; i < 10; i++ ){
				MessageListData messagedata = new MessageListData();
				messagedata.setTitle("系统消息");
				messagedata.setContent("测试测试测试测试");
				mMessagelist.add(messagedata);
				
				MessageListData messagedata2 = new MessageListData();
				messagedata2.setTitle("升级消息");
				messagedata2.setContent("升级升级升级");
				mMessagelist.add(messagedata2);
			}
			
			mMessageDialog = new MessageDialog(MessageActivity.this, mMessagelist);
			mMessageDialog.showMessageDialog();
		}
		break;
		default:
			break;
		}
	}
	private MessageDialog mMessageDialog = null;
	private ArrayList<MessageListData>  mMessagelist = null;   //消息信息结构体
}
