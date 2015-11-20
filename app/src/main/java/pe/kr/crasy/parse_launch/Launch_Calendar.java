package pe.kr.crasy.parse_launch;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import io.realm.Realm;
import io.realm.RealmResults;

public class Launch_Calendar extends AppCompatActivity implements View.OnClickListener {
    private Realm realm;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
    private TextView MonthText;
    private ImageButton MonthBack, MonthFront;
    private GridView gridView;
    private GridAdapter gridAdapter;
    private Calendar calendar;
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_calendar);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        calendar = Calendar.getInstance(Locale.KOREA);

        realm = Realm.getInstance(getApplicationContext());

        MonthBack = (ImageButton)findViewById(R.id.MonthBack);
        MonthFront = (ImageButton)findViewById(R.id.MonthFront);
        MonthBack.setOnClickListener(this);
        MonthFront.setOnClickListener(this);
        MonthText = (TextView)findViewById(R.id.MonthText);
        MonthText.setText("" + (calendar.get(Calendar.MONTH) + 1));
        gridView = (GridView)findViewById(R.id.gridView);
        gridAdapter = new GridAdapter(getApplicationContext());
        gridView.setAdapter(gridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            private Calendar test = Calendar.getInstance();
            private RealmResults<LaunchList> realmResultsList;
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                calendar.set(Calendar.DATE, 1);
                test.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),(position - (calendar.get(Calendar.DAY_OF_WEEK) - 2)));
                Iterator<LaunchStore> iterator = realm.where(LaunchStore.class).findAll().iterator();
                LaunchStore launchstore;
                MainActivity.Launch_List_Adapter.clear();
                while (iterator.hasNext()) {
                    launchstore = iterator.next();
                    if(simpleDateFormat.format(launchstore.getDate()).equals(simpleDateFormat.format(new Date(test.getTimeInMillis())))){
                        realmResultsList = launchstore.getLaunchList().where().findAll();
                        Iterator<LaunchList> launchListIterator = realmResultsList.listIterator();
                        MainActivity.Launch_List_Adapter.clear();
                        if(realmResultsList.size()>1){
                            while(launchListIterator.hasNext()){
                                MainActivity.Launch_List_Adapter.add(launchListIterator.next().getLaunch());
                            }
                        }
                        break;
                    }
                }
                if(MainActivity.Launch_List_Adapter.getCount()<1){
                    MainActivity.Launch_List_Adapter.add("이날은 점심이 없습니다.");
                }
                MainActivity.actionBar.setTitle("" + test.get(Calendar.YEAR) + "년 " + (test.get(Calendar.MONTH) + 1) + "월 " + test.get(Calendar.DATE) + "일 급식");
                finish();
            }
        });

        i = (0-calendar.get(Calendar.DAY_OF_WEEK)+2);
    }
    public class GridAdapter extends BaseAdapter {
        private Context mContext;
        Iterator<LaunchStore> storeIterator;
        LaunchStore launchStore;
        public GridAdapter(Context context){
            mContext = context;
        }
        @Override
        public int getCount() {
            calendar.set(Calendar.DATE, 1);//첫쨰날 요일을 구하기 위해
            return (calendar.getActualMaximum(Calendar.DATE)+(calendar.get(Calendar.DAY_OF_WEEK)-1));
        }
        @Override
        public Object getItem(int position) {
            return position;
        }
        @Override
        public long getItemId(int position) {
            return 0;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            storeIterator = realm.where(LaunchStore.class).findAll().iterator();
            LinearLayout linearLayout;
            TextView textView = new TextView(mContext);
            ImageView imageView = new ImageView(mContext);
            textView.setTextColor(Color.parseColor("#000000"));
            if(convertView == null){
                linearLayout = new LinearLayout(mContext);
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                if(i>0&&calendar.getActualMaximum(Calendar.DATE)>=i){
                    textView.setText(""+i);
                    imageView.setImageResource(R.mipmap.ic_calendar_remove_grey600_48dp);
                    calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), i);
                    while (storeIterator.hasNext()){
                        launchStore = storeIterator.next();
                        if(simpleDateFormat.format(launchStore.getDate()).equals(simpleDateFormat.format(new Date(calendar.getTimeInMillis())))){
                            Log.d("dd","data have " + i);
                            if(launchStore.getLaunchList().size()>2){
                                imageView.setImageResource(R.mipmap.ic_calendar_check_black_48dp);
                            }
                            break;
                        }
                    }
                    linearLayout.addView(textView);
                    linearLayout.addView(imageView);
                }
                else{
                    textView.setText("");
                }

                i++;
            }
            else {
                linearLayout = (LinearLayout)convertView;
            }
            return linearLayout;
        }
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.MonthBack:
                calendar.add(Calendar.MONTH, -1);
                calendar.set(Calendar.DATE, 1);
                MonthText.setText("" + (calendar.get(Calendar.MONTH) + 1));
                i = (0-calendar.get(Calendar.DAY_OF_WEEK)+2);
                gridView.invalidateViews();
                gridView.setAdapter(gridAdapter);
                break;
            case R.id.MonthFront:
                calendar.add(Calendar.MONTH, +1);
                calendar.set(Calendar.DATE, 1);
                MonthText.setText("" + (calendar.get(Calendar.MONTH) + 1));
                i = (0-calendar.get(Calendar.DAY_OF_WEEK)+2);
                gridView.invalidateViews();
                gridView.setAdapter(gridAdapter);
                break;
        }
    }
}
