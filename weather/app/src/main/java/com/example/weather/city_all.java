package com.example.weather;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class city_all extends AppCompatActivity {
    private ListView allcity;
    private ImageButton back2;
    private citydao dao;
    private ArrayList<city> list;
    private ArrayList<city> list1;
    private MyBaseAdapter1 myadapter;
    private int hh=0;  //记录城市等级，0：省；1：市
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allcities);
        back2=findViewById(R.id.back2);
        allcity=(ListView) findViewById(R.id.allcity);
        dao=new citydao(city_all.this);
        list=dao.findallcity("0");
        myadapter=new city_all.MyBaseAdapter1();
        allcity.setAdapter(myadapter);

        allcity.setOnItemClickListener(new AdapterView.OnItemClickListener() {   //点击事件，点击省查看省下的市，点击市查看该市天气
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (hh) {
                    case 0:   //省
                        list1=dao.findallcity(list.get(position).getFid());  //查找该省下面的市
                        list=list1;
                        //刷新适配器
                        myadapter.notifyDataSetChanged();//涉及到观察者模式
                        hh=1;
                        break;
                    case 1:  //市
                        hh=0;
                        Intent intent = new Intent();
                        intent.putExtra("adcode",list.get(position).getName()); //把名字传回主页面查询天气
                        setResult(RESULT_OK, intent);
                        finish();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public void back2(View v){
        if(hh==1){  //如果在市级点返回
            list=dao.findallcity("0");  //查找所有省
            //刷新适配器
            myadapter.notifyDataSetChanged();//涉及到观察者模式
            hh=0;
        }else{
            finish();
        }
    }

    class MyBaseAdapter1 extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position).getName();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {//组装数据
            View view=View.inflate(city_all.this,R.layout.list_item,null);//在list_item中有两个id,现在要把他们拿过来
            TextView mTextView=(TextView) view.findViewById(R.id.item);
            //组件一拿到，开始组装
            mTextView.setText(list.get(position).getName());
            //组装玩开始返回
            return view;
        }
    }
}
