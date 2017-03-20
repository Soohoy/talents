package ru.artemsukhorukov.talents.Activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import ru.artemsukhorukov.talents.Controller;
import ru.artemsukhorukov.talents.ImageAdapter.ImageHeroAdapter;
import ru.artemsukhorukov.talents.R;

public class SelectHeroActivity extends Activity {

    public static TextView mSelectText;
    Controller controller = Controller.getInstance();

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_hero);

        mSelectText = (TextView) findViewById(R.id.info);
        GridView gridview = (GridView) findViewById(R.id.gridHero);
        gridview.setAdapter(new ImageHeroAdapter(this));

        gridview.setOnItemClickListener(gridviewOnItemClickListener);
    }

    private GridView.OnItemClickListener gridviewOnItemClickListener = new GridView.OnItemClickListener() {

        @TargetApi(Build.VERSION_CODES.KITKAT)
        @Override
        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            // TODO Auto-generated method stub
            // выводим номер позиции
            try {
                mSelectText.setText(String.valueOf(position));

                // посылаем идентификатор картинки в PanelActivity
                Intent i = new Intent(getApplicationContext(), PanelActivity.class);

                // передаем индекс массивa
                controller.addHero(position, getApplicationContext());
                //  i.putExtra("id", position);
                startActivity(i);
            }catch (Exception e){
                mSelectText.setText("ошибка " + e.getMessage());
            }


        }
    };

}
