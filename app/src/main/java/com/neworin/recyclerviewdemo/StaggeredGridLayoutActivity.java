package com.neworin.recyclerviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class StaggeredGridLayoutActivity extends AppCompatActivity {

    private RecyclerView mRecyclerview;
    private List<String> mDatas;
    private MyStaggeredAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staggered_grid_layout);
        initDatas();
        initView();
        mAdapter = new MyStaggeredAdapter(this, mDatas);
        mRecyclerview.setAdapter(mAdapter);
        //设置RecyclerView的布局管理
        mRecyclerview.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        //设置RecyclerView的Item间分割线
//        mRecyclerview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        mAdapter.setOnItemClickListener(new MySimpleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
            }

            @Override
            public void onItemLongClick(View view, int position) {
                mAdapter.deleteDatas(position);
            }
        });
    }

    private void initView() {
        mRecyclerview = (RecyclerView) findViewById(R.id.id_recyclerview);
    }

    private void initDatas() {
        mDatas = new ArrayList<>();
        for (int i = 'A'; i <= 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }
}
