# 侧滑菜单：仿照官方Demo实现

```java

/**
 * 侧滑菜单：
 * 仿照官方Demo实现，官方使用的布局组合控件：DrawerLayout+FrameLayout+ListView
 * (后期，已将ListView替换为NavigationView)
 */

```

主布局文件，控件组合
```java

<?xml version="1.0" encoding="utf-8"?>
<android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/drawerlayout"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <FrameLayout
        android:id="@+id/flContent"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>

    <ListView
        android:id="@+id/lv"
        android:layout_width="280dp"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:background="#FF0033"
        android:divider="@android:color/transparent"
        android:dividerHeight="0dp"/>

</android.support.v4.widget.DrawerLayout>

```

----
![](https://github.com/ykmeory/DrawerLayout_OfficialUsage/blob/master/img_folder/drawer01.png)
----
![](https://github.com/ykmeory/DrawerLayout_OfficialUsage/blob/master/img_folder/drawer02.png)
----


![img01](https://github.com/ykmeory/DrawerLayout_OfficialUsage/blob/master/img_folder/img01.jpg "screenshot01")
&nbsp;&nbsp;&nbsp;
![img02](https://github.com/ykmeory/DrawerLayout_OfficialUsage/blob/master/img_folder/img02.jpg "screenshot02")
