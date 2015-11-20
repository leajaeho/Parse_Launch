package pe.kr.crasy.parse_launch;

import android.app.DownloadManager;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    private Calendar calendar;
    private FloatingActionsMenu famMultipleActionsDown;
    private List<String> Today_Launch = new ArrayList<>();
    public static ArrayAdapter<String> Launch_List_Adapter;
    private IntentFilter intentFilter;
    private BroadcastReceiver broadcastReceiver;
    private Realm realm;
    public static android.support.v7.app.ActionBar actionBar;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
    private SharedPreferences sharedPreferences;
    private NotificationCompat.Builder mBuilder;
    private NotificationManager mNotificationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBuilder = new NotificationCompat.Builder(this);
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mBuilder.setSmallIcon(R.mipmap.ic_download_grey600_36dp);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();

        calendar = Calendar.getInstance(Locale.KOREA);

        setTitle("" + calendar.get(Calendar.YEAR) + "년 " + (calendar.get(Calendar.MONTH) + 1) + "월 " + calendar.get(Calendar.DAY_OF_MONTH) + "일 급식");

        FloatingActionButton fabDownload = (FloatingActionButton)findViewById(R.id.Button_Download_All);
        FloatingActionButton fabRemoveData = (FloatingActionButton) findViewById(R.id.Button_Remove_All_Data);
        FloatingActionButton fabShowInCalender = (FloatingActionButton) findViewById(R.id.Button_Show_In_Calender);
        FloatingActionButton fabShowLicense = (FloatingActionButton)findViewById(R.id.Button_Show_Setting);
        famMultipleActionsDown = (FloatingActionsMenu)findViewById(R.id.multiple_actions_down);
        fabDownload.setOnClickListener(new fabOnclickListener());
        fabRemoveData.setOnClickListener(new fabOnclickListener());
        fabShowInCalender.setOnClickListener(new fabOnclickListener());
        fabShowLicense.setOnClickListener(new fabOnclickListener());

        intentFilter = new IntentFilter();                                                              //점심 정보를 가져오기 위해
        intentFilter.addAction(getPackageName() + "Show_Launch_List");                                  //점심 정보가 이미 있을 때 보넬 브로드 케스트
        intentFilter.addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE);                               //HWP 파일이 다운로드 되었을 떄 보넬 브로드 케스트
        broadcastReceiver = new Show_Launch_List();                                                     //Inner class Show_Launch_List() 브로드 케스트
        registerReceiver(broadcastReceiver, intentFilter);                                               //브로드 케스트 등록

        ListView Launch_List = (ListView)findViewById(R.id.Launch_List);
        Launch_List_Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        Launch_List.setAdapter(Launch_List_Adapter);

        RealmResults<LaunchStore> realmResults;
        RealmResults<LaunchList> realmResultsList;
        realm = Realm.getInstance(this);
        realmResults = realm.where(LaunchStore.class).findAll();                       //오늘 급식 식단표가 이미 있는지 확인
        Iterator<LaunchStore> iterator = realmResults.iterator();
        Boolean test = false;
        LaunchStore launchstore;
        while (iterator.hasNext()) {
            launchstore = iterator.next();
            if(simpleDateFormat.format(launchstore.getDate()).equals(simpleDateFormat.format(new Date()))){
                realmResultsList = launchstore.getLaunchList().where().findAll();
                Iterator<LaunchList> launchListIterator = realmResultsList.listIterator();
                while(launchListIterator.hasNext()){
                    Today_Launch.add(launchListIterator.next().getLaunch());
                }
                sendBroadcast(new Intent(getPackageName() + "Show_Launch_List"));
                test = true;
                break;
            }
        }
        if(!test){
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
            if((sharedPreferences.getBoolean("UpdateLaunchOnlyWIFI",true)&&isWifi().equals("WIFI"))||
                    !sharedPreferences.getBoolean("UpdateLaunchOnlyWIFI",true)) {
                new Thread(new Runnable() {                                                                     //오늘의 급식 정보를 가죠옴
                    private Realm realm_2;
                    @Override
                    public void run() {
                        Log.d("dd", "loaded download");
                        try {
                            realm_2 = Realm.getInstance(getApplicationContext());
                            Document doc = Jsoup.connect("http://www.sugong.org/main.php?menugrp=060602&master=meal2&act=list&SearchYear=2015&SearchMonth=" + (calendar.get(Calendar.MONTH) + 1) + "&SearchDay=" + calendar.get(Calendar.DAY_OF_MONTH) + "#diary_list").get();
                            Elements elements = doc.select(".meal_table");
                            String[] strSplit = elements.text().split("식단 중식 메뉴");
                            realm_2.beginTransaction();
                            LaunchStore launchStore = realm_2.createObject(LaunchStore.class);
                            launchStore.setDate(new Date());
                            LaunchList launchList;
                            if (strSplit.length >= 2) {                                                           //급식 없는 날이면 1이하
                                strSplit = strSplit[1].split(",");
                                for (String s : strSplit) {
                                    Log.d("dd",s.trim());
                                    Today_Launch.add(s.trim());
                                    launchList = realm_2.createObject(LaunchList.class);
                                    launchList.setLaunch(s.trim());
                                    launchStore.getLaunchList().add(launchList);
                                }
                            } else {
                                launchList = realm_2.createObject(LaunchList.class);
                                launchList.setLaunch("No Launch Today");
                                launchStore.getLaunchList().add(launchList);
                                Today_Launch.add("No Launch Today");
                            }
                            realm_2.commitTransaction();
                        } catch (IOException e) {
                            Today_Launch.add("연결 오류!!!");
                            e.printStackTrace();
                        }

                        sendBroadcast(new Intent(getPackageName() + "Show_Launch_List"));                     //다운로드가 완료됨을 알림
                    }
                }).start();
            }else{
                Launch_List_Adapter.clear();
                Launch_List_Adapter.add("설정에서 WI-FI 로만 다운로드를 해제 하시면 다운로드가 가능 합니다.");
            }
        }
    }
    public class fabOnclickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.Button_Download_All:
                    sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                    if((sharedPreferences.getBoolean("UpdateAllLaunchOnlyWIFI",true)&&isWifi().equals("WIFI"))||
                            !sharedPreferences.getBoolean("UpdateAllLaunchOnlyWIFI",true)){
                        new Thread(new Runnable() {                                                                     //오늘의 급식 정보를 가죠옴
                            private Realm realm_2;
                            private Calendar ThreadCalendar = Calendar.getInstance(Locale.KOREA);
                            @Override
                            public void run() {
                                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this); // 설정이 도중에 변경될시.... 알람을 지울수가 없음...
                                mBuilder.setOngoing(true);
                                realm_2 = Realm.getInstance(getApplicationContext());
                                RealmResults<LaunchStore> realmResults;
                                realmResults = realm_2.where(LaunchStore.class).findAll();                       //오늘 급식 식단표가 이미 있는지 확인
                                for(int i = 1 ; i<=calendar.getActualMaximum(Calendar.DATE);i++){
                                    if(sharedPreferences.getBoolean("ShowNotification",true)) {
                                        Log.d("dd", "notification");
                                        mBuilder.setContentTitle("급식 목록 다운로드 중").setContentText("다운로드중..." + (int) ((double) i / (double) calendar.getActualMaximum(Calendar.DATE) * 100.0) + "%").setProgress(30, i, false);
                                        mNotificationManager.notify(1, mBuilder.build());
                                        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this); // 설정이 도중에 변경될시....
                                    }
                                    else {
                                        mBuilder.setOngoing(false);
                                    }
                                    Log.d("dd",""+i);
                                    ThreadCalendar.set(Calendar.DAY_OF_MONTH, i);
                                    Date date = new Date(ThreadCalendar.getTimeInMillis());
                                    boolean isHave = false;
                                    for(int j = 0; realmResults.size()>j;j++){
                                        realmResults.get(j).getDate();
                                        if(simpleDateFormat.format(realmResults.get(j).getDate()).equals(simpleDateFormat.format(date))) {
                                            isHave = true;
                                            break;
                                        }
                                    }
                                    if(!isHave){
                                        Log.d("dd","is???");
                                        realm_2.beginTransaction();
                                        LaunchStore launchStore = realm_2.createObject(LaunchStore.class);
                                        LaunchList launchList;
                                        try {
                                            Document doc = Jsoup.connect("http://www.sugong.org/main.php?menugrp=060602&master=meal2&act=list&SearchYear=2015&SearchMonth=" + (calendar.get(Calendar.MONTH) + 1) + "&SearchDay=" + i + "#diary_list").get();
                                            Elements elements = doc.select(".meal_table");
                                            String[] strSplit = elements.text().split("식단 중식 메뉴");
                                            launchStore.setDate(date);
                                            if (strSplit.length > 1) {                                                           //급식 없는 날이면 1이하
                                                Log.d("dd", "Download"+i);
                                                strSplit = strSplit[1].split(",");
                                                Log.d("dd", date.toString());
                                                for (String s : strSplit) {
                                                    launchList = realm_2.createObject(LaunchList.class);
                                                    launchList.setLaunch(s.trim());
                                                    launchStore.getLaunchList().add(launchList);
                                                }
                                            }else{
                                                launchList = realm_2.createObject(LaunchList.class);
                                                launchList.setLaunch("No Launch");
                                                launchStore.getLaunchList().add(launchList);
                                            }
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        realm_2.commitTransaction();
                                    }


                                }
                                Snackbar.make(findViewById(R.id.cood),"Downloaded All Data",Snackbar.LENGTH_LONG).show();
                                if(sharedPreferences.getBoolean("ShowNotification",true)){
                                    Log.d("dd", "notification");
                                    mBuilder.setOngoing(false);
                                    mBuilder.setContentTitle("급식 목록 다운로드 완료").setContentText("다운로드 완료").setProgress(0,0,false);
                                    mNotificationManager.notify(1, mBuilder.build());
                                }

                            }
                        }).start();
                    }
                    break;
                case R.id.Button_Remove_All_Data:
                    RealmResults<LaunchStore> launchStores = realm.where(LaunchStore.class).findAll();
                    realm.beginTransaction();
                    launchStores.clear();
                    realm.commitTransaction();
                    Snackbar.make(findViewById(R.id.cood),"Removed All Data",Snackbar.LENGTH_LONG).show();
                    break;
                case R.id.Button_Show_In_Calender:
                    startActivity(new Intent(MainActivity.this,Launch_Calendar.class));
                    break;
                case R.id.Button_Show_Setting:
                    startActivity(new Intent(MainActivity.this,SettingActivity.class));
                    break;
            }
            if(famMultipleActionsDown.isExpanded()){
                famMultipleActionsDown.collapse();
            }
        }
    }
    public class Show_Launch_List extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(getPackageName()+"Show_Launch_List")){                                 //이미 저장되어 있는 오늘 점심 명단을 가져오라고 알림
                Launch_List_Adapter.addAll(Today_Launch);
                Log.d("dd",""+Today_Launch.isEmpty());
                Log.d("dd","sdfasdf");
            }
        }
    }
    private long lastBack = 0;
    @Override
    public void onBackPressed(){
        if(famMultipleActionsDown.isExpanded()){
            famMultipleActionsDown.collapse();
            return;
        }
        long thisTime = System.currentTimeMillis();
        if (thisTime < lastBack + 2000) {
            this.finish();
        } else {
            Snackbar.make(findViewById(R.id.cood), "'뒤로' 버튼을 한번 더 누르시면 종료 됩니다.", Snackbar.LENGTH_LONG).show();
            lastBack = thisTime;
        }
    }
    @Override
    public  void onDestroy(){
        super.onDestroy();
    }
    @Override
    public void onResume(){
        super.onResume();
        registerReceiver(broadcastReceiver, intentFilter);
    }
    @Override
    public void onPause(){
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {return true;}
    public String isWifi(){
        ConnectivityManager connectivityManager = (ConnectivityManager)MainActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getActiveNetworkInfo();
        if(wifi==null){
            return "AirMode";
        }else{
            return wifi.getTypeName();
        }
    }
}