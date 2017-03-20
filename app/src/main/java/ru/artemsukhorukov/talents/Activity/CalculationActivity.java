package ru.artemsukhorukov.talents.Activity;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import java.util.ArrayList;

import ru.artemsukhorukov.talents.Characteristics;
import ru.artemsukhorukov.talents.Controller;
import ru.artemsukhorukov.talents.R;

public class CalculationActivity extends ListActivity {
    Controller controller = Controller.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList<Characteristics> list = new ArrayList<>();

        final String[] nameCh = new String[]{
                "Здоровье", "Энергия", "Сила", "Разум", "Хитрость", "Проворство", "Стойкость",
                "Воля", "Скорость", "Регенерация здоровья", "Регенерация энергии", "Кража ХП",
                "Кража МП", "КД пак", "Прайм", "Мощь", "Урон", "Атака в секунду", "Пробивание",
                "Шанс крита", "Защита тела", "Защита духа",
        };
        ArrayList<String> valueCh = controller.getAppLogic().getListCharacteristics();
        for(int i = 0; i < nameCh.length; i++){
            list.add(new Characteristics(nameCh[i], valueCh.get(i)));
        }

        ListAdapter adapter = new SimpleAdapter(this, list, R.layout.list_item_characteristic,
                new String[]{Characteristics.NAME, Characteristics.VALUE}, new int[]{R.id.nameCh, R.id.valueCh});
        setListAdapter(adapter);
    }
}
