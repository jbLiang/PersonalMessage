package com.jbLiang.message.data;

import android.graphics.Bitmap;

/**
 * 消息内容结构体
 * @author jbLiang
 *
 */
public class MessageListData {
	
	public Bitmap getBitmap() {
		return bitmap;
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	private Bitmap bitmap = null;
	private String   title = null;
	private String   content = null;
	private String   time = null;
}
