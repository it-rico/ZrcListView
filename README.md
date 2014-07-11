ZrcListView
===========

一个顺滑又漂亮的Android下拉刷新与加载更多列表组件。

### 根据系统自带ListView源码改造而来: ###
    1.增加下拉刷新及滚动到底部自动加载的功能；
    2.增加越界回弹效果；
    2.增加自定义列表项动画的功能；

### 与其他下拉刷新列表组件的不同 ###
    1.其他下拉刷新组件的实现基本是通过动态更改Header的大小来实现的，而ZrcListView是修改了Listview的边界判断；
    2.其他下拉刷新组件很容易在下拉刷新时变得卡顿，这是动态更改子View引起的，而ZrcListView的下拉刷新部分与滑动内容一样顺滑；
    3.可以设置默认列表头偏移量，这使得实现透明ActionBar与ListView叠加变得很容易；
    4.其他下拉刷新可以在无列表项时下拉刷新，而ZrcListView的实现与ListView的滑动息息相关，在无列表项时，暂时无法下拉刷新。
    
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
