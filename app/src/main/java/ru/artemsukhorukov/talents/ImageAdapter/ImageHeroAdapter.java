package ru.artemsukhorukov.talents.ImageAdapter;

/**
 * Created by user on 12.02.2017.
 */
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import ru.artemsukhorukov.talents.R;

public class ImageHeroAdapter extends BaseAdapter {
    private Context mContext;

    public ImageHeroAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return mThumbIds[position];
    }

    public long getItemId(int position) {
        return position;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(183, 93));  //размер рисунка * 1,5
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // references to our images
    public	Integer[] mThumbIds = { R.drawable.hero_duelist, R.drawable.hero_cryo,
            R.drawable.hero_white_mask, R.drawable.hero_leader, R.drawable.hero_fulminant,
            R.drawable.hero_shadow, R.drawable.hero_huntsman, R.drawable.hero_inventor,
            R.drawable.hero_painter, R.drawable.hero_immortal, R.drawable.hero_combat,
            R.drawable.hero_fire_fox, R.drawable.hero_healer, R.drawable.hero_black_panther,
            R.drawable.hero_rokot, R.drawable.hero_assassin, R.drawable.hero_nymph,
            R.drawable.hero_hunter, R.drawable.hero_soulcatcher, R.drawable.hero_pied_piper,
            R.drawable.hero_amazon, R.drawable.hero_fang, R.drawable.hero_swamp_king,
            R.drawable.hero_lesovik, R.drawable.hero_magozavr, R.drawable.hero_bard,
            R.drawable.hero_master_blade, R.drawable.hero_wizard, R.drawable.hero_fix,
            R.drawable.hero_witcher, R.drawable.hero_doctrine, R.drawable.hero_demonologist,
            R.drawable.hero_vampire, R.drawable.hero_witch, R.drawable.hero_daka,
            R.drawable.hero_haka, R.drawable.hero_chimera, R.drawable.hero_keeper,
            R.drawable.hero_freese, R.drawable.hero_thug, R.drawable.hero_turehu,
            R.drawable.hero_mimi, R.drawable.hero_putnik, R.drawable.hero_moon,
            R.drawable.hero_hooligan, R.drawable.hero_berserk, R.drawable.hero_aggele
     };
}
