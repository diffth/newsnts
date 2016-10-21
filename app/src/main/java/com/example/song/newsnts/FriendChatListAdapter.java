package com.example.song.newsnts;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 어댑터 클래스 정의
 * 
 * @author Mike
 *
 */
public class FriendChatListAdapter extends BaseAdapter {

	private Context mContext;

	private List<FriendChatListItem> mItems = new ArrayList<FriendChatListItem>();

	public FriendChatListAdapter() {

	}

	public FriendChatListAdapter(Context context) {
		mContext = context;
	}



	public void addItem(FriendChatListItem it) {
		mItems.add(it);
	}

	public void setListItems(List<FriendChatListItem> lit) {
		mItems = lit;
	}

	public int getCount() {
		return mItems.size();
	}

	public Object getItem(int position) {
		return mItems.get(position);
	}

	public boolean areAllItemsSelectable() {
		return false;
	}

	public boolean isSelectable(int position) {
		try {
			return mItems.get(position).isSelectable();
		} catch (IndexOutOfBoundsException ex) {
			return false;
		}
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		FriendChatListView itemView;
		if (convertView == null) {
			itemView = new FriendChatListView(mContext, mItems.get(position));
		} else {
			itemView = (FriendChatListView) convertView;
			
			itemView.setIcon(mItems.get(position).getIcon());
			itemView.setText(0, mItems.get(position).getData(0));
			itemView.setText(1, mItems.get(position).getData(1));
			itemView.setText(2, mItems.get(position).getData(2));
		}

		return itemView;
	}

}
