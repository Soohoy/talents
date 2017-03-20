package ru.artemsukhorukov.talents.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import ru.artemsukhorukov.talents.Controller;
import ru.artemsukhorukov.talents.ImageAdapter.ImageTalentAdapter;
import ru.artemsukhorukov.talents.R;
import ru.artemsukhorukov.talents.Talent;

public class TalentsActivity extends Activity {
    ListView listView;
    Controller controller = Controller.getInstance();
    CheckBox checkBoxBlue;
    CheckBox checkBoxPink;
    CheckBox checkBoxOrange;
    CheckBox checkBoxRed;
    int line;  //уровень талантов (I, II, III, IV, V, VI)
    ArrayList<Talent> talents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talents);

        //инициализируем CheckBox
        checkBoxBlue = (CheckBox)findViewById(R.id.checkBlue);
        checkBoxPink = (CheckBox)findViewById(R.id.checkPink);
        checkBoxOrange = (CheckBox)findViewById(R.id.checkOrange);
        checkBoxRed = (CheckBox)findViewById(R.id.checkRed);
        listView = (ListView) findViewById(R.id.listTalents);

        //создаем список талантов в активности
        line = controller.positionToNum((int)getIntent().getSerializableExtra("id")); // получаем позицию панели и из позиции 0-35 получаем id 0-5;
        try {
            createListTalents(controller.getListTalents(line, true, true, true, true));
        }catch (NullPointerException e){
            Toast toast = new Toast(this);
            toast.setText(e.getMessage());
            toast.show();
        }

    }

    private ListView.OnItemClickListener listViewOnClickListener = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //проверяем есть ли уже этот талант
            if(!controller.contains(talents.get(position), line)) {
                if (!controller.fullPanel(line)) {
                    controller.addTalent(talents.get(position), line);
                   // startActivity(new Intent(getApplicationContext(), PanelActivity.class));
                }else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Заполнено", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }else{
                Toast toast = Toast.makeText(getApplicationContext(), "Этот талант уже добавлен", Toast.LENGTH_SHORT);
                toast.show();
            }

        }
    };

    void createListTalents(ArrayList<Talent> talents){
        this.talents = talents;

        ImageTalentAdapter adapter = new ImageTalentAdapter(this, talents);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(listViewOnClickListener);
        adapter.notifyDataSetChanged();
    }

    public void onCheck(View view){
        boolean red = checkBoxRed.isChecked();
        boolean orange = checkBoxOrange.isChecked();
        boolean pink = checkBoxPink.isChecked();
        boolean blue = checkBoxBlue.isChecked();
        createListTalents(controller.getListTalents(line, red, orange, pink, blue));
    }

}
