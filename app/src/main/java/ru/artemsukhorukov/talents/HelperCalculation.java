package ru.artemsukhorukov.talents;

import java.util.HashMap;
import java.util.Map;

// подсчет характеристик
public class HelperCalculation {
    static HashMap<String, Float> characteristic;

    static public HashMap<String, Float> calculation(Hero hero) throws NullPointerException{
        createCharacteristics(hero);
        addCharacteristic(hero);  // складываем характеристики талантов
        otherFormulas(hero); // подсчет второстепенных характеристик

        return characteristic;
    }


    private static void addCharacteristic(Hero hero) throws NullPointerException{
        for(Talent talent : hero.allTalents()){
            try {
            for(Map.Entry<String, Float> pair : talent.characteristic.entrySet()){
                String key;
                if(!pair.getKey().equals("Скорость")) {
                    if (pair.getKey().split(" I ").length > 1) { //pair.getKey().contains("|")){  // к наибольшему из
                        String[] keys = pair.getKey().split(" I ");
                        key = (characteristic.get(keys[0]) > characteristic.get(keys[1])) ? keys[0] : keys[1];  // !!! ДОРАБОТАТЬ ДЛЯ 4 !!!
                    } else if (pair.getKey().contains("!")) {  // к наименьшему из
                        String[] keys = pair.getKey().split("!");
                        key = (characteristic.get(keys[0]) < characteristic.get(keys[1])) ? keys[0] : keys[1];
                    } else {
                        key = pair.getKey();
                    }
                    Float value = characteristic.get(key) + pair.getValue();
                    characteristic.put(key, value);
                }
            }
        }catch (NullPointerException e){
                throw new NullPointerException(e.getMessage() + talent.name);
            }
        }
        incrementCh(hero);  //прирост характеристик от мощи
        characteristic.put("Скорость", hero.baseCharacteristic.get("Скорость") + incSpeed(hero));

    }

    private static void otherFormulas(Hero hero) {
        float value;
        if(characteristic.get("Проворство") < 415) {
            value = 0.00364f * characteristic.get("Проворство") + 0.49f;
        }else {
            value = 2f;
        }
        characteristic.put("Атака в секунду", value);

        value = (float)(62.765 - 11534/(126.04 + characteristic.get("Хитрость")));
        characteristic.put("Шанс крита", value);

        value = (float)(0.5355*(characteristic.get("Стойкость")+0.3*characteristic.get("Воля"))-20);
        characteristic.put("Защита тела", value);

        value = (float)(0.5355*(characteristic.get("Воля")+0.3*characteristic.get("Стойкость"))-20);
        characteristic.put("Защита духа", value);

        if(characteristic.get("Проворство") > 500){
            value = (float)(61.72+0.6876*characteristic.get("Проворство")-10.035*Math.pow(characteristic.get("Проворство"), 0.5));
        }else {
            value = (float)(48.45+0.764*characteristic.get("Проворство")-11.15*Math.pow(characteristic.get("Проворство"), 0.5));
        }
        if(characteristic.get("Хитрость") > 500){
            value += (float)(85.78 + 0.43 * characteristic.get("Хитрость") - 15.55 * Math.log(characteristic.get("Хитрость")));
        }else{
            value += (float)(59.83 + 0.57 * characteristic.get("Хитрость") - 20.73 * Math.log(characteristic.get("Хитрость")));
        }
        characteristic.put("Пробивание", value);

        regHPregMP(hero);

        damage(hero);  // считаем урон
    }

    private static void regHPregMP(Hero hero) {
        float value = (float)(0.0036*characteristic.get("Энергия"));
        characteristic.put("Рег. энергии", characteristic.get("Рег. энергии") + value);

        float regHP = 0;
        switch (hero.name){
            case "Дуэлянт / Принц воров":
            case "Безликий / Белая маска":
            case "Горец / Бессмертный":
            case "Комбат / Мейдзин":
            case "Человек-гора / Рокот":
            case "Чистильщик / Ассасин":
            case "Крысолов / Повелитель крыс":
            case "Клык / Коготь":
            case "Мастер клинков / Отмеченный змеем":
            case "Ведьмак":
            case "Вампир / Акшар":
            case "Да'Ка / Ха'Ка":
            case "Геноморф / Химера":
            case "Фриз":
            case "Ту'Реху":
            case "Мими":
                regHP = 3.3f;
                break;
            case "Воевода / Предводитель":
            case "Дева / Нимфа":
            case "Душелов / Жнец душ":
            case "Жабий наездник / Болотный царь":
                regHP = 1.65f;
                break;
        }
        if(hero.name.equals("Царица ночи / Черная пантера")){
            value = 0.0045f * characteristic.get("Здоровье");
        }else {
            value = (float) (0.00105 * characteristic.get("Здоровье") + regHP);
        }
        characteristic.put("Рег. здоровья", value);
    }

    private static void damage(Hero hero){
        float value = characteristic.get(hero.damage);
        switch (hero.name) {
            case "Дуэлянт / Принц воров":
                value *= 1.15;
                break;
            case "Чарозмей / Магозавр":
                value *= 0.8;
                break;
            case "Целительница / Жрица":
                value *= 0.6;
                break;
            case "Лучница / Амазонка":
                value *= 0.58;
                break;
        }
        characteristic.put("Урон min", 0.9f*value);
        characteristic.put("Урон max", 1.1f*value);
    }

    private static void incrementCh(Hero hero) throws NullPointerException{
        float x = characteristic.get("Мощь");
        characteristic.put("Здоровье", characteristic.get("Здоровье") + hero.aHealth * x + hero.bHealth);
        characteristic.put("Энергия", characteristic.get("Энергия") + hero.aEnergy * x + hero.bEnergy);
        characteristic.put("Сила", characteristic.get("Сила") + hero.aForce * x + hero.bForce);
        characteristic.put("Разум", characteristic.get("Разум") + hero.aMind * x + hero.bMind);
        characteristic.put("Хитрость", characteristic.get("Хитрость") + hero.aTrick * x + hero.bTrick);
        characteristic.put("Проворство", characteristic.get("Проворство") + hero.aAgility * x + hero.bAgility);
        characteristic.put("Стойкость", characteristic.get("Стойкость") + hero.aPersistence * x + hero.bPersistence);
        characteristic.put("Воля", characteristic.get("Воля") + hero.aWill * x + hero.bWill);
    }


    private static void createCharacteristics(Hero hero) {
        characteristic = new HashMap<>();
        for(Map.Entry<String, Integer> pair : hero.baseCharacteristic.entrySet()){
            characteristic.put(pair.getKey(), (float)pair.getValue());
        }
        characteristic.put("Рег. здоровья", 0f);
        characteristic.put("Рег. энергии", 0f);
        characteristic.put("Кража ХП", 0f);
        characteristic.put("Кража МП", 0f);
        characteristic.put("КД пак", 0f);
        characteristic.put("Прайм", 0f);
        characteristic.put("Мощь", 0f);
        characteristic.put("Урон min", 0f);
        characteristic.put("Урон max", 0f);
        characteristic.put("Атака в секунду", 0f);
        characteristic.put("Пробивание", 0f);
        characteristic.put("Шанс крита", 0f);
        characteristic.put("Защита тела", 0f);
        characteristic.put("Защита духа", 0f);
    }

    private static float incSpeed(Hero hero){
        float speed = 0f;
        for(Talent talent : hero.allTalents()){
            Float temp = talent.characteristic.get("Скорость");
            if(temp != null){
                if(speed < temp){
                    speed = temp;
                }
            }
        }
        return speed;
    }
}
