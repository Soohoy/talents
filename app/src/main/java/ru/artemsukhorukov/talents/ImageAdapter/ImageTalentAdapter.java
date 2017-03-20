package ru.artemsukhorukov.talents.ImageAdapter;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import ru.artemsukhorukov.talents.R;
import ru.artemsukhorukov.talents.Talent;

/**
 * Created by user on 13.02.2017.
 */
public class ImageTalentAdapter extends BaseAdapter{
    private Activity mContext;
    ArrayList<Talent> talents;

    public ImageTalentAdapter(Activity mContext, ArrayList<Talent> talents){
        this.mContext = mContext;
        this.talents = talents;
    }

    @Override
    public int getCount() {
        return talents.size();
    }

    @Override
    public Object getItem(int position) {
        return talents.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //ViewHolder буферизирует оценку ращличных полей шаблона элемента
        ViewHolder holder;
        //Очищает существующий шаблон, если параметр задан
        //Работает только если базовый шаблон для всех классов один и тот же
        View rowView = convertView;
        if(rowView == null){
            LayoutInflater inflater = mContext.getLayoutInflater();
            rowView = inflater.inflate(R.layout.list_item_talents, null, true);
            holder = new ViewHolder();
            holder.imageView = (ImageView)rowView.findViewById(R.id.imgTalent);
            holder.textName = (TextView)rowView.findViewById(R.id.textName);
            holder.textDef = (TextView)rowView.findViewById(R.id.textDef);
            rowView.setTag(holder);
        }else{
            holder = (ViewHolder) rowView.getTag();
        }
        holder.textName.setText(talents.get(position).name);
        if(talents.get(position).image.contains("red")) {
            holder.textName.setTextColor(Color.RED);
        }else if(talents.get(position).image.contains("orange")){
            holder.textName.setTextColor(Color.parseColor("#FF8C00"));
        }else if(talents.get(position).image.contains("pink")){
            holder.textName.setTextColor(Color.parseColor("#FF00FF"));
        }else if(talents.get(position).image.contains("blue")){
            holder.textName.setTextColor(Color.BLUE);
        }else{
            holder.textName.setTextColor(holder.textDef.getTextColors());
        }
        holder.textDef.setText(talents.get(position).description());
        String path = talents.get(position).image;
        holder.imageView.setImageResource(R.drawable.null_talent);
        try {
            holder.imageView.setImageDrawable(Drawable.createFromStream(mContext.getAssets().open(path), null));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rowView;
    }

    static class ViewHolder{
        public ImageView imageView;
        public TextView textName;
        public TextView textDef;
    }
}
