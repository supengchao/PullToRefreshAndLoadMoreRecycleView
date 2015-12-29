package net.mobctrl.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.mobctrl.ItemBean;
import net.mobctrl.treerecyclerview.R;
import net.mobctrl.viewholder.BaseViewHolder;
import net.mobctrl.viewholder.ChildViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Zheng Haibo
 * @PersonalWebsite http://www.mobctrl.net
 * @Description
 */
public class RecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder> {

	private Context mContext;
	private List<ItemBean> mDataSet;

	public RecyclerAdapter(Context context) {
		mContext = context;
		mDataSet = new ArrayList<ItemBean>();
	}

	@Override
	public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(mContext).inflate(
				R.layout.item_recycler_child, parent, false);
		return new ChildViewHolder(view);
	}

	@Override
	public void onBindViewHolder(BaseViewHolder holder, int position) {
		ChildViewHolder textViewHolder = (ChildViewHolder) holder;
		textViewHolder.bindView(mDataSet.get(position), position);
	}


	@Override
	public int getItemCount() {
		return mDataSet.size();
	}

	/**
	 * 从position开始删除，删除
	 * 
	 * @param position
	 * @param itemCount
	 *            删除的数目
	 */
	public void removeAll(int position, int itemCount) {
		for (int i = 0; i < itemCount; i++) {
			mDataSet.remove(i);
		}
		notifyItemRangeRemoved(position, itemCount);
	}
	public void removeAll(int position) {
		Log.d("TAG","mDataSet。size="+mDataSet.size());
//		for (int i = 0; i <50; i++) {
//			mDataSet.remove(i);
//			Log.d("TAG","mDataSet。size="+mDataSet.size());
//			Log.d("TAG","I="+i);
//		}
		mDataSet.clear();
		Log.d("TAG", "mDataSet。size=" + mDataSet.size());
		notifyDataSetChanged();
	}

	@Override
	public int getItemViewType(int position) {
		return 0;
	}

	public void add(String text, int position) {
		ItemBean bean = new ItemBean((int) (Math.random()*300+100),text);
		mDataSet.add(position, bean);
		notifyItemInserted(position);
	}

	public void addAll(List<ItemBean> list, int position) {
		mDataSet.addAll(position, list);

		notifyItemRangeInserted(position, list.size());
		notifyDataSetChanged();
	}

}
