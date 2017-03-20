package ru.artemsukhorukov.talents.ImageAdapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.IOException;

import ru.artemsukhorukov.talents.Controller;
import ru.artemsukhorukov.talents.R;

/**
 * Created by user on 25.02.2017.
 */
public class ImagePanelAdapter extends BaseAdapter{
    private Context mContext;
    private Controller controller = Controller.getInstance();

    public ImagePanelAdapter(Context c){
        mContext = c;
    }
    @Override
    public int getCount() {
        return mImageIds.length;
    }

    @Override
    public Object getItem(int position) {
        return mImageIds[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // создание нового ImageView для каждого элемента на который ссылается адаптер
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if(convertView == null){
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(96,96));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(4,4,4,4);
        }else{
            imageView = (ImageView)convertView;
        }

        //imageView.setImageResource(mImageIds[position]);
        try {
            String path = controller.getAppLogic().getImagePath(position);
            imageView.setImageDrawable(Drawable.createFromStream(mContext.getAssets().open(path), null));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageView;
    }
    public Integer[] mImageIds = {R.drawable.null_talent, R.drawable.null_talent, R.drawable.null_talent,
            R.drawable.null_talent, R.drawable.null_talent, R.drawable.null_talent, R.drawable.null_talent,
            R.drawable.null_talent, R.drawable.null_talent, R.drawable.null_talent, R.drawable.null_talent,
            R.drawable.null_talent, R.drawable.null_talent, R.drawable.null_talent, R.drawable.null_talent,
            R.drawable.null_talent, R.drawable.null_talent, R.drawable.null_talent, R.drawable.null_talent,
            R.drawable.null_talent, R.drawable.null_talent, R.drawable.null_talent, R.drawable.null_talent,
            R.drawable.null_talent, R.drawable.null_talent, R.drawable.null_talent, R.drawable.null_talent,
            R.drawable.null_talent, R.drawable.null_talent, R.drawable.null_talent, R.drawable.null_talent,
            R.drawable.null_talent, R.drawable.null_talent, R.drawable.null_talent, R.drawable.null_talent,
            R.drawable.null_talent, };
}
