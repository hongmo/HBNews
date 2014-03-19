package info.binhua.hbnews.custom;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import info.binhua.hbnews.R;

/**
 * Created by Administrator on 14-3-19.
 */
public class CustomSimpleAdapter extends SimpleAdapter {

    public CustomSimpleAdapter(Context context, List<? extends Map<String, ?>> data,
                         int resource, String[] from, int[] to) {
        super(context,data,resource,from, to);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View v = super.getView(position,convertView,parent);

        if(position == 0){
            TextView categoryTitle = (TextView)v;
            categoryTitle.setTextColor(0XFFFFFFFF); //设置字体为白色
            categoryTitle.setBackgroundResource(R.drawable.categorybar_item_background);
        }
        return v;
    }
}
