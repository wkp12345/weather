package com.example.weather;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class city_manager extends AppCompatActivity {
    private ListView mycity;
    private ImageButton back;
    private citydao dao;
    private ArrayList<Lives> list;
    private MyBaseAdapter myadapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.citymanager);
        mycity=(ListView) findViewById(R.id.mycity);
        back=findViewById(R.id.back);
        dao=new citydao(city_manager.this);
        list=dao.findall();
        myadapter=new MyBaseAdapter();
        mycity.setAdapter(myadapter);

        mycity.setOnItemClickListener(new AdapterView.OnItemClickListener() {   //点击事件，查看天气，并设为默认城市
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                Lives live=list.get(position);
                intent.putExtra("pro", live.getProvince());
                intent.putExtra("city", live.getCity());
                intent.putExtra("wea", live.getWeather());
                intent.putExtra("tem", live.getTemperature());
                intent.putExtra("hum", live.getHumidity());
                intent.putExtra("time", live.getReporttime());
                intent.putExtra("adcode", live.getAdcode());
                setResult(RESULT_OK, intent);
                SharedPreferences mySharedPreferences= getSharedPreferences("test",
                        Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = mySharedPreferences.edit();
                editor.putString("i", position+"");
                editor.commit();
                finish();
            }
        });

        /* 长按删除。*/
        mycity.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(city_manager.this);
                //设置弹出框标题
                builder.setTitle("确定取消关注吗？");
                builder.setItems(new String[]{"确定","取消"}, new DialogInterface.OnClickListener() {
                    //类型码
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                //删除对应的item索引
                                dao.delete(list.get(position));
                                list.remove(position);
                                //刷新适配器
                                myadapter.notifyDataSetChanged();//涉及到观察者模式
                                break;
                            case 1:
                                break;
                            default:
                                break;
                        }
                    }
                });
                builder.create().show();
                return true;
            }
        });
    }
    class MyBaseAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position).getCity();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {//组装数据
            View view=View.inflate(city_manager.this,R.layout.list_item,null);//在list_item中有两个id,现在要把他们拿过来
            TextView mTextView=(TextView) view.findViewById(R.id.item);
            //组件一拿到，开始组装
            mTextView.setText(list.get(position).getCity());
            //组装玩开始返回
            return view;
        }
    }

    public void back(View v){ finish(); }
}
