package net.mobctrl.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import net.mobctrl.ItemBean;
import net.mobctrl.adapter.RecyclerAdapter;
import net.mobctrl.treerecyclerview.R;
import net.mobctrl.views.SuperSwipeRefreshLayout;
import net.mobctrl.views.SuperSwipeRefreshLayout.OnPullRefreshListener;
import net.mobctrl.views.SuperSwipeRefreshLayout.OnPushLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Zheng Haibo
 * @PersonalWebsite http://www.mobctrl.net
 * @Description
 */
@SuppressLint("NewApi")
public class RecyclerViewActivity extends Activity {

    private RecyclerView recyclerView;
    private RecyclerAdapter myAdapter;
    private LinearLayoutManager linearLayoutManager;
    private SuperSwipeRefreshLayout swipeRefreshLayout;

    // Header View
    private ProgressBar progressBar;
    private TextView textView;
    private ImageView imageView;

    // Footer View
    private ProgressBar footerProgressBar;
    private TextView footerTextView;
    private ImageView footerImageView;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        /** init recyclerView */
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//		linearLayoutManager = new LinearLayoutManager(this);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        myAdapter = new RecyclerAdapter(this);
        recyclerView.setAdapter(myAdapter);
        initDatas();
        // init SuperSwipeRefreshLayout
        swipeRefreshLayout = (SuperSwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setHeaderViewBackgroundColor(0xff888888);
        swipeRefreshLayout.setHeaderView(createHeaderView());// add headerView
        swipeRefreshLayout.setFooterView(createFooterView());
        swipeRefreshLayout.setTargetScrollWithLayout(true);
        swipeRefreshLayout
                .setOnPullRefreshListener(new OnPullRefreshListener() {

                    @Override
                    public void onRefresh() {
                        textView.setText("正在刷新");
                        imageView.setVisibility(View.GONE);
                        progressBar.setVisibility(View.VISIBLE);
                        new Handler().postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                refreshData();
                                swipeRefreshLayout.setRefreshing(false);
                                progressBar.setVisibility(View.GONE);
                            }
                        }, 1000);
                    }

                    @Override
                    public void onPullDistance(int distance) {
                        // pull distance
                    }

                    @Override
                    public void onPullEnable(boolean enable) {
                        textView.setText(enable ? "松开刷新" : "下拉刷新");
                        imageView.setVisibility(View.VISIBLE);
                        imageView.setRotation(enable ? 180 : 0);
                    }
                });

        swipeRefreshLayout
                .setOnPushLoadMoreListener(new OnPushLoadMoreListener() {

                    @Override
                    public void onLoadMore() {
                        footerTextView.setText("正在加载...");
                        footerImageView.setVisibility(View.GONE);
                        footerProgressBar.setVisibility(View.VISIBLE);
                        new Handler().postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                loadMoreDatas();
                                footerImageView.setVisibility(View.VISIBLE);
                                footerProgressBar.setVisibility(View.GONE);
                                swipeRefreshLayout.setLoadMore(false);
                            }
                        }, 1000);
                    }

                    @Override
                    public void onPushEnable(boolean enable) {
                        footerTextView.setText(enable ? "松开加载" : "上拉加载");
                        footerImageView.setVisibility(View.VISIBLE);
                        footerImageView.setRotation(enable ? 0 : 180);
                    }

                    @Override
                    public void onPushDistance(int distance) {
                        // TODO Auto-generated method stub

                    }

                });

    }

    private View createFooterView() {
        View footerView = LayoutInflater.from(swipeRefreshLayout.getContext())
                .inflate(R.layout.layout_footer, null);
        footerProgressBar = (ProgressBar) footerView
                .findViewById(R.id.footer_pb_view);
        footerImageView = (ImageView) footerView
                .findViewById(R.id.footer_image_view);
        footerTextView = (TextView) footerView
                .findViewById(R.id.footer_text_view);
        footerProgressBar.setVisibility(View.GONE);
        footerImageView.setVisibility(View.VISIBLE);
        footerImageView.setImageResource(R.drawable.down_arrow);
        footerTextView.setText("上拉加载更多...");
        return footerView;
    }

    private View createHeaderView() {
        View headerView = LayoutInflater.from(swipeRefreshLayout.getContext())
                .inflate(R.layout.layout_head, null);
        progressBar = (ProgressBar) headerView.findViewById(R.id.pb_view);
        textView = (TextView) headerView.findViewById(R.id.text_view);
        textView.setText("下拉刷新");
        imageView = (ImageView) headerView.findViewById(R.id.image_view);
        imageView.setVisibility(View.VISIBLE);
        imageView.setImageResource(R.drawable.down_arrow);
        progressBar.setVisibility(View.GONE);
        return headerView;
    }

    private void initDatas() {
        List<ItemBean> list = new ArrayList<ItemBean>();
        for (int i = 0; i < 50; i++) {
            int height = (int) (Math.random()*300+100);
            ItemBean bean = new ItemBean(height,"item " + i);
            list.add(bean);
        }
        myAdapter.addAll(list, 0);
    }

    private void loadMoreDatas() {
        List<ItemBean> list = new ArrayList<ItemBean>();
        for (int i = 0; i < 10; i++) {
            int height = (int) (Math.random()*300+100);
            ItemBean bean = new ItemBean(height,"Insert" + i);
            list.add(bean);
        }
        myAdapter.addAll(list, myAdapter.getItemCount());
    }

    private void refreshData(){
        List<ItemBean> list = new ArrayList<ItemBean>();
        for (int i = 0; i < 50; i++) {
            int height = (int) (Math.random()*300+100);
            ItemBean bean = new ItemBean(height,"item " + i);
            list.add(bean);
        }
        myAdapter.removeAll(0);
        myAdapter.addAll(list, 0);
    }


}
