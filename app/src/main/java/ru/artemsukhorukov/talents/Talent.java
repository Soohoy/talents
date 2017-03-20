package ru.artemsukhorukov.talents;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.Root;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 13.02.2017.
 */
@Root
public class Talent{
    @Attribute
    public String name;
    @Attribute
    public int level;
    @ElementMap(required = false)
    public HashMap<String, Float> characteristic = new HashMap<>();
    @Element(required = false)
    public String description;
    @Element
    public String image;
    @ElementMap(required = false)
    HashMap<String, Float> delta;

    public void upLvl(int lvl){

        float value;
        if(this.image.contains("blue")) {
            value = (float)(characteristic.get("Мощь") + 2.625 * (lvl - this.level));
        }else{
            value = (float)(characteristic.get("Мощь") + 0.075 * (lvl - this.level) * characteristic.get("Мощь"));
        }
        characteristic.put("Мощь", value);
        for(Map.Entry<String, Float> pair : delta.entrySet()){
            value = characteristic.get(pair.getKey()) + pair.getValue()*(lvl-this.level);
            characteristic.put(pair.getKey(), value);
        }
        this.level = lvl;
    }

    public String description(){
        StringBuilder builder = new StringBuilder();
        if(description != null) {
            builder.append(description);
        }
        if(delta != null) {
            for (Map.Entry<String, Float> pair : delta.entrySet()) {
                if (builder.length() != 0) {
                    builder.append('\n');
                }
                builder.append("+");
                builder.append(String.format("%(.1f", characteristic.get(pair.getKey())));
                builder.append(" ");
                builder.append(reformString(pair.getKey()));
            }
        }
        return builder.toString();
    }

    private String reformString(String s){
        switch (s){
            case "Сила I Разум":
                s = "к наибольшему из Силы и Разума";
                break;
            case "Стойкость I Воля":
                s = "к наибольшему из Стойкости и Воли";
                break;
            case "Проворство I Хитрость":
                s = "к наибольшему из Проворства и Хитрости";
                break;
            case "Сила I Разум I Стойкость I Воля":
                s = "к наибольшему из Сила, Разума, Стойкости и Воли";
                break;
            case "Стойкость!Воля":
                s = "к наименьшему из Стойкости и Воли";
                break;
            case "Здоровье":
                s = "Здоровья";
                break;
            case "Энергия":
                s = "Энергии";
                break;
            case "Сила":
                s = "Силы";
                break;
            case "Разум":
                s = "Разума";
                break;
            case "Хитрость":
                s = "Хитрости";
                break;
            case "Проворство":
                s = "Проворства";
                break;
            case "Стойкость":
                s = "Стойкости";
                break;
            case "Воля":
                s = "Воли";
                break;
            case "Рег. здоровья":
                s = "регенерации Здоровья";
                break;
            case "Рег. энергии":
                s = "регенерации Энергии";
                break;
            case "Кража ХП":
                s = "кражи Здоровья";
                break;
            case "Кража МП":
                s = "кражи Энергии";
                break;
        }
        return s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Talent talent = (Talent) o;

        return name != null ? name.equals(talent.name) : talent.name == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
