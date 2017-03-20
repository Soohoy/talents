package ru.artemsukhorukov.talents.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.IOException;

import ru.artemsukhorukov.talents.Controller;
import ru.artemsukhorukov.talents.ImageAdapter.ImagePanelAdapter;
import ru.artemsukhorukov.talents.R;
import ru.artemsukhorukov.talents.Talent;

/**
 * Created by user on 12.02.2017.
 */
public class PanelActivity extends Activity {
    public static TextView textView;
    GridView gridPanel;
    Controller controller = Controller.getInstance();
    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_panel);

        textView = (TextView)findViewById(R.id.text);
        try {
            textView.setText(controller.getAppLogic().getNameHero());
        }catch (Exception e){
            textView.setText(e.getMessage() + " метод Create");
        }

       // updateGridTalents();
    }

    private GridView.OnItemClickListener gridPanelOnItemClickListener = new GridView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            // TODO Auto-generated method stub
            try {

                if (controller.haveTalent(position)) {
                    if (position != 35) {
                        showRatingDialog(position, true);
                    } else {
                        showRatingDialog(position, false);
                    }
                } else {
                    int line = controller.positionToNum(position);
                    controller.createHelperListTalents(getApplicationContext(), line);
                    // посылаем идентификатор картинки в TalentsActivity
                    Intent i = new Intent(getApplicationContext(), TalentsActivity.class);
                    // передаем индекс массивa
                    i.putExtra("id", position);
                    startActivity(i);
                }

            }catch (Exception e){
                textView.setText(e.getMessage());
            }
        }
    };

    private void showRatingDialog(final int position, boolean button) {
        Talent talent = controller.getTalent(position);
        final AlertDialog.Builder ratingDialog = new AlertDialog.Builder(this);

        try {
            ratingDialog.setIcon(Drawable.createFromStream(getAssets().open(talent.image), null));
            ratingDialog.setTitle(talent.name);
            View linearlayout = getLayoutInflater().inflate(R.layout.rating_dialog_talent, null);
            ratingDialog.setView(linearlayout);
            ratingDialog.setMessage(talent.description());

            final RatingBar rating = (RatingBar)linearlayout.findViewById(R.id.ratingbartalent);
            rating.setRating(talent.level);

            ratingDialog.setPositiveButton("Заточить", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    controller.upLvlTalent(position, (int)rating.getRating());
                }
            });
            if(button) {
                ratingDialog.setNegativeButton("Удалить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        textView.setText(controller.delete(position));
                        updateGridTalents();
                    }
                });
            }
            ratingDialog.create();
            ratingDialog.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private GridView.OnItemLongClickListener gridPanelOnItemLongClickListener = new AdapterView.OnItemLongClickListener(){

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            try {
                if(position != 35) {
                    textView.setText(controller.delete(position));
                    updateGridTalents();
                }


            }catch (Exception e){
                textView.setText(e.getMessage());
            }
            return true;
        }
    };

    public void onClickCalculation(View view){
        Intent i = new Intent(getApplicationContext(), CalculationActivity.class);
        try {
            controller.calculation();
            startActivity(i);
        }catch (NullPointerException e){
            textView.setText(e.getMessage() + "В таланте ошибка");
        }catch (Exception e) {
            textView.setText(e.getMessage());
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            updateGridTalents();
        }catch (Exception e){
            textView.setText(e.getMessage() + " метод Resume");
        }
    }

    public void updateGridTalents(){
        gridPanel = (GridView)findViewById(R.id.gridPanel);
        ImagePanelAdapter adapter = new ImagePanelAdapter(this);
        gridPanel.setAdapter(adapter);
        gridPanel.setOnItemClickListener(gridPanelOnItemClickListener);
        gridPanel.setOnItemLongClickListener(gridPanelOnItemLongClickListener);
        adapter.notifyDataSetChanged();
    }

    public void onClickButton(View view){
        try {
            textView.setText(String.valueOf(controller.getAppLogic().getHero().talents1.size()));
            updateGridTalents();
        }catch (Exception e){
            textView.setText(e.getMessage());
        }
    }

    public void onClickUpAllTalents(View view){
        controller.upAllTalents(5);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contex_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete_item:
                textView.setText("del");

        }
        return true;
    }
}
