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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class city_query extends AppCompatActivity {
    private EditText cityid;
    private Button query;
    private ImageButton back1;
    private ListView listview;
    private citydao dao;
    private ArrayList<Lives> list;
    private MyBaseAdapter2 myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.query);
        cityid=findViewById(R.id.cityid);
        query=findViewById(R.id.query);
        back1=findViewById(R.id.back1);
        listview=findViewById(R.id.listview);
        dao=new citydao(city_query.this);
        list=dao.hfindall();
        myadapter=new city_query.MyBaseAdapter2();
        listview.setAdapter(myadapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {   //点击查看缓存的天气
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                Lives live=list.get(list.size()-position-1);
                intent.putExtra("pro", live.getProvince());
                intent.putExtra("city", live.getCity());
                intent.putExtra("wea", live.getWeather());
                intent.putExtra("tem", live.getTemperature());
                intent.putExtra("hum", live.getHumidity());
                intent.putExtra("time", live.getReporttime());
                intent.putExtra("adcode", live.getAdcode());
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        /* 长按删除该查询记录。*/
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //删除对应的item索引
                dao.hdelete(list.get(list.size()-position-1));
                list.remove(list.size()-position-1);
                //刷新适配器
                myadapter.notifyDataSetChanged();//涉及到观察者模式
                return true;
            }
        });
    }

    /**
     * 根据城市名或adcode查询城市天气，如果缓存过则在数据库查
     * 否则访问API查
     * @param v
     */
    String code;
    public void query(View v){
        if(exist()){   //如果缓存中存在则在数据库中查找
            Lives live1 = dao.hfind(code);
            System.out.println("****************1*"+live1.getAdcode());
            Intent intent1 = new Intent();
            intent1.putExtra("pro", live1.getProvince());
            intent1.putExtra("city", live1.getCity());
            intent1.putExtra("wea", live1.getWeather());
            intent1.putExtra("tem", live1.getTemperature());
            intent1.putExtra("hum", live1.getHumidity());
            intent1.putExtra("time", live1.getReporttime());
            intent1.putExtra("adcode", live1.getAdcode());
            setResult(RESULT_OK, intent1);
            finish();
        }else{  //否则访问在线API
            HttpClient.query(code, HttpClient.WEATHER_TYPE_BASE, Weather.class, new HttpClient.IHttpCallback() {
                @Override
                public <T> void onSuccess(T result, boolean isSuccess) {
                    if (isSuccess) {
                        Weather weather = (Weather) result;
                        if (weather.getInfo().equals("OK") && Integer.parseInt(weather.getCount())>0) {
                            Lives live = weather.getLives().get(0);
                            System.out.println("****************2*"+live.getAdcode());
                            if(dao.hfind(live.getAdcode())==null){
                                dao.hinsert(live);                     //缓存这次查询结果到数据库中
                            }
                            runOnUiThread(new Runnable(){
                                @Override
                                public void run() {
                                    Intent intent = new Intent();
                                    intent.putExtra("pro", live.getProvince());
                                    intent.putExtra("city", live.getCity());
                                    intent.putExtra("wea", live.getWeather());
                                    intent.putExtra("tem", live.getTemperature());
                                    intent.putExtra("hum", live.getHumidity());
                                    intent.putExtra("time", live.getReporttime());
                                    intent.putExtra("adcode", live.getAdcode());
                                    setResult(RESULT_OK, intent);
                                    finish();
                                }
                            });
                        }else{
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(city_query.this, "--error--", Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(city_query.this, "无法提供天气信息", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }
            });
        }
    }

    /**
     * 判断缓存中是否存在该城市
     * @return
     */
    private boolean exist(){
        code=cityid.getText().toString();
        System.out.println("***************************"+code);
        for(int m=0;m<list.size();m++){
            String s=list.get(m).getCity();
            int b=s.length();
            if(list.get(m).getAdcode().equals(code)||list.get(m).getCity().equals(code)
            ||list.get(m).getProvince().equals(code)||
            s.substring(0,b-1).equals(code)){
                if(dao.hfind(list.get(m).getAdcode())!=null){
                    code=list.get(m).getAdcode();
                    return true;
                }
            }
        }
        return false;
    }
    public void back1(View v){ finish();}

    /**
     * 适配器
     */
    class MyBaseAdapter2 extends BaseAdapter {

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
            View view=View.inflate(city_query.this,R.layout.list_item,null);//在list_item中有两个id,现在要把他们拿过来
            TextView mTextView=(TextView) view.findViewById(R.id.item);
            //组件一拿到，开始组装
            mTextView.setText(list.get(list.size()-position-1).getCity());  //逆序显示缓存的查询结果，最近一次的查询显示在最上面
            //组装玩开始返回
            return view;
        }
    }
}
