package com.example.demo;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import zrc.widget.SimpleFooter;
import zrc.widget.SimpleHeader;
import zrc.widget.ZrcListView;
import zrc.widget.ZrcListView.OnStartListener;

public class MainActivity extends Activity {
    private ZrcListView listView;
    private Handler handler;
    private ArrayList<String> msgs;
    private int pageId = -1;
    private MyAdapter adapter;

    private static final String[][] names = new String[][]{
        {"加拿大","瑞典","澳大利亚","瑞士","新西兰","挪威","丹麦","芬兰","奥地利","荷兰","德国","日本","比利时","意大利","英国"},
        {"德国","西班牙","爱尔兰","法国","葡萄牙","新加坡","希腊","巴西","美国","阿根廷","波兰","印度","秘鲁","阿联酋","泰国"},
        {"智利","波多黎各","南非","韩国","墨西哥","土耳其","埃及","委内瑞拉","玻利维亚","乌克兰"},
        {"以色列","海地","中国","沙特阿拉伯","俄罗斯","哥伦比亚","尼日利亚","巴基斯坦","伊朗","伊拉克"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ZrcListView) findViewById(R.id.zListView);
        handler = new Handler();

        // 设置默认偏移量，主要用于实现透明标题栏功能。
        float density = getResources().getDisplayMetrics().density;
        listView.setFirstTopOffset((int) (50 * density));

        // 设置下拉刷新的样式
        SimpleHeader header = new SimpleHeader(this);
        header.setTextColor(0xff0066aa);
        header.setCircleColor(0xff33bbee);
        listView.setHeadable(header);

        // 设置加载更多的样式
        SimpleFooter footer = new SimpleFooter(this);
        footer.setCircleColor(0xff33bbee);
        listView.setFootable(footer);

        // 设置列表项出现动画
        listView.setItemAnimForTopIn(R.anim.topitem_in);
        listView.setItemAnimForBottomIn(R.anim.bottomitem_in);

        // 下拉刷新事件回调
        listView.setOnRefreshStartListener(new OnStartListener() {
            @Override
            public void onStart() {
                refresh();
            }
        });

        // 加载更多事件回调
        listView.setOnLoadMoreStartListener(new OnStartListener() {
            @Override
            public void onStart() {
                loadMore();
            }
        });

        adapter = new MyAdapter();
        listView.setAdapter(adapter);
        listView.refresh(); // 主动下拉刷新
    }

    private void refresh(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int rand = (int) (Math.random() * 2); // 随机数模拟成功失败。这里从有数据开始。
                if(rand == 0 || pageId == -1){
                    pageId = 0;
                    msgs = new ArrayList<String>();
                    for(String name:names[0]){
                        msgs.add(name);
                    }
                    adapter.notifyDataSetChanged();
                    listView.setRefreshSuccess("加载成功"); // 通知加载成功
                    listView.startLoadMore(); // 开启LoadingMore功能
                }else{
                    listView.setRefreshFail("加载失败");
                }
            }
        }, 2 * 1000);
    }

    private void loadMore(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pageId++;
                if(pageId<names.length){
                    for(String name:names[pageId]){
                        msgs.add(name);
                    }
                    adapter.notifyDataSetChanged();
                    listView.setLoadMoreSuccess();
                }else{
                    listView.stopLoadMore();
                }
            }
        }, 2 * 1000);
    }

    private class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return msgs==null ? 0 : msgs.size();
        }
        @Override
        public Object getItem(int position) {
            return msgs.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView;
            if(convertView==null) {
                textView = (TextView) getLayoutInflater().inflate(android.R.layout.simple_list_item_1, null);
            }else{
                textView = (TextView) convertView;
            }
            textView.setText(msgs.get(position));
            return textView;
        }
    }
}
