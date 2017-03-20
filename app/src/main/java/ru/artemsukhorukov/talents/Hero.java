package ru.artemsukhorukov.talents;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by user on 13.02.2017.
 */
@Root
public class Hero {
    @Attribute
    public String name;
    @ElementMap
    public HashMap<String, Integer> baseCharacteristic = new HashMap<>();
    @ElementList
    public ArrayList<Talent> talents1 = new ArrayList<>(6);
    @ElementList
    public ArrayList<Talent> talents2 = new ArrayList<>(6);
    @ElementList
    public ArrayList<Talent> talents3 = new ArrayList<>(6);
    @ElementList
    public ArrayList<Talent> talents4 = new ArrayList<>(6);

    public ArrayList<Talent> talents5 = new ArrayList<>(6);

    public ArrayList<Talent> talents6 = new ArrayList<>(6);
    @Element
    Float aHealth;  //Здоровье
    @Element
    Float bHealth;
    @Element
    Float aEnergy;  //Энергия
    @Element
    Float bEnergy;
    @Element
    Float aForce;  //Сила
    @Element
    Float bForce;
    @Element
    Float aMind;  //Разум
    @Element
    Float bMind;
    @Element
    Float aTrick;  //Хитрость
    @Element
    Float bTrick;
    @Element
    Float aAgility;  //Проворство
    @Element
    Float bAgility;
    @Element
    Float aPersistence;  //Стойкость
    @Element
    Float bPersistence;
    @Element
    Float aWill;  //Воля
    @Element
    Float bWill;
    @Element
    String damage;

    public ArrayList<Talent> allTalents(){
        ArrayList<Talent> list = new ArrayList<>();
        for(int i = 0; i < talents1.size(); i++){
            list.add(talents1.get(i));
        }
        for(int i = 0; i < talents2.size(); i++){
            list.add(talents2.get(i));
        }
        for(int i = 0; i < talents3.size(); i++){
            list.add(talents3.get(i));
        }
        for(int i = 0; i < talents4.size(); i++){
            list.add(talents4.get(i));
        }
        for(int i = 0; i < talents5.size(); i++){
            list.add(talents5.get(i));
        }
        for(int i = 0; i < talents6.size(); i++){
            list.add(talents6.get(i));
        }
        return list;
    }
}
