package com.neworin.recyclerviewdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * 总结：
 * 1，LayoutManager去实现ListView, GridView, 横向ListView/GridView, 瀑布流
 * 2，ItemDecoration 实现的原理和如何定制
 * 3，ItemAnimator的使用
 * 4，手动在Adapter中提供ItemClick或者ItemLongClck的回调
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerview;
    private List<String> mDatas;
    private MySimpleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDatas();
        initView();
        mAdapter = new MySimpleAdapter(this, mDatas);
        mRecyclerview.setAdapter(mAdapter);
        //设置RecyclerView的布局管理
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerview.setLayoutManager(linearLayoutManager);
        mRecyclerview.setItemAnimator(new DefaultItemAnimator());
        //设置RecyclerView的Item间分割线
//        mRecyclerview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        mAdapter.setOnItemClickListener(new MySimpleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, "click:" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(MainActivity.this, "long click:" + position, Toast.LENGTH_SHORT).show();
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
        switch (id) {
            case R.id.action_add:
                mAdapter.addDatas(1);
                break;
            case R.id.action_delete:
                mAdapter.deleteDatas(1);
                break;
            case R.id.action_gridview:
                mRecyclerview.setLayoutManager(new GridLayoutManager(this, 3));
                break;
            case R.id.action_listview:
                mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
                break;
            case R.id.action_hor_gridview:
                mRecyclerview.setLayoutManager(new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.HORIZONTAL));
                break;
            case R.id.action_staggered:
                startActivity(new Intent(this, StaggeredGridLayoutActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
