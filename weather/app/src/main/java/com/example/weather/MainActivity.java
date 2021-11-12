package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton flash;  //刷新
    private TextView pro;     //省
    private TextView city;   //城市
    private TextView wea;   //天气现象
    private TextView tem;   //温度
    private TextView hum;   //湿度
    private TextView time;  //更新时间
    private Button insert;  //插入数据库
    private String adcode="";  //城市编码
    private citydao dao;
    private int i=0;  //城市在关注列表中的位置

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flash=findViewById(R.id.flush);
        insert=findViewById(R.id.insert);
        pro=findViewById(R.id.pro);
        city=findViewById(R.id.city);
        wea=findViewById(R.id.wea);
        tem=findViewById(R.id.tem);
        hum=findViewById(R.id.hum);
        time=findViewById(R.id.time);
        dao = new citydao(MainActivity.this);
        show();
    }

    /**
     * 显示关注城市的天气
     */
    public void show(){
        if(!dao.findall().isEmpty()){   //如果关注列表不为空
            SharedPreferences sharedPreferences= getSharedPreferences("test",
                    Activity.MODE_PRIVATE);
            if(!sharedPreferences.getString("i", "").equals("")){
                i =Integer.parseInt(sharedPreferences.getString("i", "")); //记录关注列表中最后一次被点击的城市位置
            }
            if(i>=dao.findall().size()){ i=0; }  //如果该城市被删除则显示列表中第一个城市天气
            Lives l=dao.findall().get(i);
            pro.setText(l.getProvince());
            city.setText(l.getCity());
            wea.setText(l.getWeather());
            tem.setText(l.getTemperature());
            hum.setText(l.getHumidity());
            time.setText(l.getReporttime());
            adcode = l.getAdcode();
            insert.setText("已关注");
        }
    }

    /**
     *     用onCreateOptionsMenu（）显示菜单
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);//getMenuInflater()方法得到MenuInflater
        //调用inflate接收两个参数
        //R.menu.mymenu指调用menu文件下的mymenu资源文件
        return true;//返回true，允许创建的菜单显示,返回false不显示
    }
    /**
     *     定义菜单响应事件
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){   //item.getItemId()判断我们选择那个菜单项
            case R.id.first:
                Intent intent1=new Intent(MainActivity.this, city_query.class);
                startActivityForResult(intent1, 1);
                break;
            case R.id.second:
                Intent intent2=new Intent(MainActivity.this, city_manager.class);
                startActivityForResult(intent2, 2);
                break;
            case R.id.third:
                Intent intent3=new Intent(MainActivity.this, city_all.class);
                startActivityForResult(intent3, 3);
                break;
            default:
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
        switch (requestCode) {
            case 1:
                Bundle bundle = data.getExtras();
                pro.setText(bundle.getString("pro"));
                city.setText(bundle.getString("city"));
                wea.setText(bundle.getString("wea"));
                tem.setText(bundle.getString("tem"));
                hum.setText(bundle.getString("hum"));
                time.setText(bundle.getString("time"));
                adcode=bundle.getString("adcode");
                if(dao.find(adcode)==null){
                    insert.setText("关注");
                }else{
                    insert.setText("已关注");
                }
                break;
            case 2:
                Bundle bundle1 = data.getExtras();
                pro.setText(bundle1.getString("pro"));
                city.setText(bundle1.getString("city"));
                wea.setText(bundle1.getString("wea"));
                tem.setText(bundle1.getString("tem"));
                hum.setText(bundle1.getString("hum"));
                time.setText(bundle1.getString("time"));
                adcode=bundle1.getString("adcode");
                insert.setText("已关注");
                break;
            case 3:
                Bundle bundle2 = data.getExtras();
                adcode=bundle2.getString("adcode");
                query();
                query();
                query();
                if(dao.find(adcode)==null){
                    insert.setText("关注");
                }else{
                    insert.setText("已关注");
                }
                break;
            default:
        }
    }
    }

    /**
     * 若未关注，则把当前城市添加为关注，插入数据库
     * 否则取消关注并从数据库删除
     * @param v
     */
    public void insert(View v){
        if(adcode!=null&&!adcode.equals("")){
            Lives l=new Lives();
            l.setAdcode(adcode);
            l.setReporttime(time.getText().toString());
            l.setProvince(pro.getText().toString());
            l.setCity(city.getText().toString());
            l.setWeather(wea.getText().toString());
            l.setTemperature(tem.getText().toString());
            l.setHumidity(hum.getText().toString());
            if(dao.find(adcode)==null){
                dao.insert(l);
                insert.setText("已关注");
                Toast.makeText(MainActivity.this, "关注成功", Toast.LENGTH_SHORT).show();
            }else{
                dao.delete(l);
                insert.setText("关注");
                Toast.makeText(MainActivity.this, "取消关注", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 刷新当前城市天气数据
     * @param v
     */
    public void flash(View v){
        query();
        if(dao.find(adcode)==null){
            insert.setText("关注");
        }else{
            insert.setText("已关注");
        }
    }

    /**
     * 根据adcode查询城市天气
     */
    public void query(){
        HttpClient.query(adcode, HttpClient.WEATHER_TYPE_BASE, Weather.class, new HttpClient.IHttpCallback() {
            @Override
            public <T> void onSuccess(T result, boolean isSuccess) {
                if (isSuccess) {
                    Weather weather = (Weather) result;
                    if (weather.getInfo().equals("OK") && Integer.parseInt(weather.getCount())>0) {
                        Lives live = weather.getLives().get(0);
                        System.out.println("***********************" + live.getAdcode());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                pro.setText(live.getProvince());
                                city.setText(live.getCity());
                                wea.setText(live.getWeather());
                                tem.setText(live.getTemperature());
                                hum.setText(live.getHumidity());
                                time.setText(live.getReporttime());
                                adcode = live.getAdcode();
                            }
                        });
                    }
                }else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "无法提供天气信息", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}