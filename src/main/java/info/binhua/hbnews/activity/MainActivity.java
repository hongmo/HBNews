package info.binhua.hbnews.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.view.ViewGroup.LayoutParams;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import info.binhua.hbnews.R;
import info.binhua.hbnews.custom.CustomSimpleAdapter;
import info.binhua.hbnews.util.DensityUtil;

public class MainActivity extends ActionBarActivity {

    private final int COLUMNWIDTHPX = 55;
    private int columnWidthDip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] categoryArray = getResources().getStringArray(R.array.categories);
        columnWidthDip = DensityUtil.px2dip(this, COLUMNWIDTHPX);

        List<HashMap<String, String>> categories = new ArrayList<HashMap<String, String>>();
        for(int i=0; i<categoryArray.length; i++){
            HashMap<String, String> hashMap = new HashMap<String, String>();
            hashMap.put("category_title", categoryArray[i]);
            categories.add(hashMap);
        }
        CustomSimpleAdapter categoryAdapter = new CustomSimpleAdapter(this, categories, R.layout.category_titile,
                new String[]{"category_title"} , new int[]{R.id.category_title});

        GridView category = new GridView(this);
        category.setColumnWidth(columnWidthDip);// each grid width
        category.setNumColumns(GridView.AUTO_FIT); //the num of grids
        category.setGravity(Gravity.CENTER);
        category.setSelector(new ColorDrawable(Color.TRANSPARENT));

        int width = columnWidthDip * categories.size();

        LayoutParams params = new LayoutParams(width,LayoutParams.WRAP_CONTENT);

        category.setLayoutParams(params);
        category.setAdapter(categoryAdapter);

        category.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView categoryTitle;
                for(int i=0; i<parent.getChildCount(); i++){
                    categoryTitle = (TextView)(parent.getChildAt(i));
                    categoryTitle.setBackgroundResource(0);
                    categoryTitle.setTextColor(getResources().getColor(R.color.category_title_normal_textColor));
                }
                categoryTitle = (TextView)view;
                categoryTitle.setTextColor(getResources().getColor(R.color.white)); //设置字体为白色
                categoryTitle.setBackgroundResource(R.drawable.categorybar_item_background); //设置单击时候的背景图
            }
        });

        //在LinearLayout中添加GridView
        LinearLayout categoryLayout = (LinearLayout)findViewById(R.id.category_layout);
        categoryLayout.addView(category);

        //右边箭头单击响应事件
        final HorizontalScrollView categoryScrollview = (HorizontalScrollView)findViewById(R.id.category_scrollview);
        Button categoryArrowRight = (Button)findViewById(R.id.category_arrow_right);
        categoryArrowRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryScrollview.fling(columnWidthDip*18);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
