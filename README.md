ZrcListView
===========

一个顺滑又漂亮的Android下拉刷新与加载更多列表组件。

### 根据系统自带ListView源码改造而来: ###
    1.增加下拉刷新及滚动到底部自动加载的功能
    2.增加自定义列表项动画的功能
    
## **ZrcListView使用示例** ##

设置ZrcListView相关属性<br>
```java
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
```

##Screenshots
![Screenshot 0](https://raw.github.com/zarics/ZrcListView/master/Screenshots/0.png)

![Screenshot 1](https://raw.github.com/zarics/ZrcListView/master/Screenshots/1.png)

![Screenshot 2](https://raw.github.com/zarics/ZrcListView/master/Screenshots/2.png)

![Screenshot 3](https://raw.github.com/zarics/ZrcListView/master/Screenshots/3.png)
