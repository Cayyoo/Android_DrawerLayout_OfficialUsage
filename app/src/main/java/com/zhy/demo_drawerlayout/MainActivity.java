package com.zhy.demo_drawerlayout;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 侧滑菜单：
 * 仿照官方Demo实现，官方使用的布局组合控件：DrawerLayout+FrameLayout+ListView
 * (后期，已将ListView替换为NavigationView)
 */
public class MainActivity extends FragmentActivity implements AdapterView.OnItemClickListener {
	private DrawerLayout drawerLayout;
	private FrameLayout flContent;
	private ListView lv;
	private ActionBarDrawerToggle drawerToggle;//控制侧滑菜单的开关

	private List<String> datas;
	private ArrayAdapter<String> adapter;

	private String title;
	private String[] cities={"上海","北京","深圳","广州","杭州"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		title= (String) getTitle();
		drawerLayout= (DrawerLayout) findViewById(R.id.drawerlayout);
		flContent= (FrameLayout) findViewById(R.id.flContent);
		lv= (ListView) findViewById(R.id.lv);

		datas=new ArrayList<>();
		datas.addAll(Arrays.asList(cities));

		//绑定适配器
		adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datas);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(this);

		//创建菜单控制开关
		//这里的两个string参数只是标记，没有很大的实际意义
		drawerToggle=new ActionBarDrawerToggle(this,drawerLayout,
				R.drawable.ic_launcher,R.string.drawer_open,R.string.drawer_close){
			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
				getActionBar().setTitle("请选择城市");
				invalidateOptionsMenu();
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				getActionBar().setTitle(title);
				invalidateOptionsMenu();
			}
		};

		drawerLayout.setDrawerListener(drawerToggle);
		//开启ActionBar，显示菜单控制开关
		getActionBar().setDisplayHomeAsUpEnabled(true);
		//响应菜单控制开关的点击事件
		getActionBar().setHomeButtonEnabled(true);
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Fragment fragment=new ContentFragment();
        Bundle args=new Bundle();
        args.putString("text", datas.get(position));
        fragment.setArguments(args);

		FragmentManager fragmentManager=getSupportFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.flContent,fragment).commit();

        //关闭菜单
        drawerLayout.closeDrawer(lv);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater=getMenuInflater();
		inflater.inflate(R.menu.main,menu);
		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		boolean isDrawOpen =drawerLayout.isDrawerOpen(lv);
		menu.findItem(R.id.action_websearch).setVisible(!isDrawOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		//屏蔽drawerToggle的点击事件
		if (drawerToggle.onOptionsItemSelected(item)){
			return true;
		}

		if (item.getItemId()==R.id.action_websearch){
			Intent intent=new Intent();
			intent.setAction("android.intent.action.VIEW");
			Uri uri=Uri.parse("http://www.baidu.com/");
			intent.setData(uri);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		//将drawerToggle与DrawerLayout状态同步
		drawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		drawerToggle.onConfigurationChanged(newConfig);
	}
}
