package ru.artemsukhorukov.talents;

import android.content.Context;
import android.content.res.AssetManager;

import org.simpleframework.xml.core.Persister;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import ru.artemsukhorukov.talents.Activity.SelectHeroActivity;

/**
 * Created by user on 25.02.2017.
 */
public class AppLogic {
    int id;
    Hero hero;
    HashMap<String, Float> characteristic = new HashMap<>();

    HashMap<Talent, Integer> baseTalents = new HashMap<>();
    ArrayList<String> listImage = new ArrayList<>();

    public void addHero(int id, Context context){
        this.id = id;
       // if(setHeroes.get(id) == null) {
            AssetManager am = context.getAssets();
            Persister serializer = new Persister();
            String path = getPath(id);
            try {
                hero = serializer.read(Hero.class, am.open(path));
                createBaseTalents();
                updateList();
            } catch (IOException e) {

            } catch (Exception e) {
                SelectHeroActivity.mSelectText.setText(e.getMessage());
            }
      //  }

    }

    private void createBaseTalents() {
        for(int i = 1; i < hero.talents1.size(); i++){
            baseTalents.put(hero.talents1.get(i), 1);
        }
        for(int i = 0; i < hero.talents2.size(); i++){
            baseTalents.put(hero.talents2.get(i), 2);
        }
        for(int i = 0; i < hero.talents3.size(); i++){
            baseTalents.put(hero.talents3.get(i), 3);
        }
        for(int i = 0; i < hero.talents4.size(); i++){
            baseTalents.put(hero.talents4.get(i), 4);
        }
    }

    public String getImagePath(int i){
        updateList();
        return listImage.get(i);
    }

    public void updateList(){
        for(int i = 0; i < 36; i++){
            listImage.add("image/nullTalent.png");
        }
        listImage.set(listImage.size()-1, hero.talents1.get(0).image); //устанавливаем неубираемый талант
        for(int i = 1; i < hero.talents1.size(); i++){
            listImage.set(i+29, hero.talents1.get(i).image);
        }
        for(int i = 0; i < hero.talents2.size(); i++){
            listImage.set(i+24, hero.talents2.get(i).image);
        }
        for(int i = 0; i < hero.talents3.size(); i++){
            listImage.set(i+18, hero.talents3.get(i).image);
        }
        for(int i = 0; i < hero.talents4.size(); i++){
            listImage.set(i+12, hero.talents4.get(i).image);
        }
        for(int i = 0; i < hero.talents5.size(); i++){
            listImage.set(i+6, hero.talents5.get(i).image);
        }
        for(int i = 0; i < hero.talents6.size(); i++){
            listImage.set(i, hero.talents6.get(i).image);
        }
    }

    public String getNameHero(){
        return hero.name;
    }

    String getPath(int id){
        switch (id){
            case 0: return "heroes/duelist.xml";
            case 1: return "heroes/cryo.xml";
            case 2: return "heroes/whiteMask.xml";
            case 3: return "heroes/leader.xml";
            case 4: return "heroes/fulminant.xml";
            case 5: return "heroes/shadow.xml";
            case 6: return "heroes/huntsman.xml";
            case 7: return "heroes/inventor.xml";
            case 8: return "heroes/painter.xml";
            case 9: return "heroes/immortal.xml";
            case 10: return "heroes/combat.xml";
            case 11: return "heroes/fireFox.xml";
            case 12: return "heroes/healer.xml";
            case 13: return "heroes/blackPanther.xml";
            case 14: return "heroes/rokot.xml";
            case 15: return "heroes/assassin.xml";
            case 16: return "heroes/nymph.xml";
            case 17: return "heroes/hunter.xml";
            case 18: return "heroes/soulcatcher.xml";
            case 19: return "heroes/piedPiper.xml";
            case 20: return "heroes/amazon.xml";
            case 21: return "heroes/fang.xml";
            case 22: return "heroes/swampKing.xml";
            case 23: return "heroes/lesovik.xml";
            case 24: return "heroes/magozavr.xml";
            case 25: return "heroes/bard.xml";
            case 26: return "heroes/masterBlade.xml";
            case 27: return "heroes/wizard.xml";
            case 28: return "heroes/fix.xml";
            case 29: return "heroes/witcher.xml";
            case 30: return "heroes/doctrine.xml";
            case 31: return "heroes/demonologist.xml";
            case 32: return "heroes/vampire.xml";
            case 33: return "heroes/witch.xml";
            case 34: return "heroes/daKa.xml";
            case 35: return "heroes/haKa.xml";
            case 36: return "heroes/chimera.xml";
            case 37: return "heroes/keeper.xml";
            case 38: return "heroes/freese.xml";
            case 39: return "heroes/thug.xml";
            case 40: return "heroes/tuRehu.xml";
            case 41: return "heroes/mimi.xml";
            case 42: return "heroes/putnik.xml";
            case 43: return "heroes/moon.xml";
            case 44: return "heroes/hooligan.xml";
            case 45: return "heroes/berserk.xml";
            case 46: return "heroes/aggele.xml";
        }
        return "heroes/duelist.xml";
    }

    public Hero getHero() {
        return hero;
    }

    public void addTalent(Talent talent, int idd) {
        list(idd).add(talent);
    }

    public boolean full(int idd) {
        return list(idd).size() == 6;
    }

    ArrayList<Talent> list(int number){
        switch (number){
            case 0: return hero.talents1;
            case 1: return hero.talents2;
            case 2: return hero.talents3;
            case 3: return hero.talents4;
            case 4: return hero.talents5;
            case 5: return hero.talents6;
        }
        return new ArrayList<>();
    }

    public boolean contains(Talent talent, int idd) {
        return list(idd).contains(talent);
    }

    public String delete(int position) {
        String s;
        int pos;
        if(positionToNumLine(position) == 0) {
            pos = (position / 6)*6 + list(positionToNumLine(position)).size()-2;
            s = list(positionToNumLine(position)).get(position%6+1).name;
            list(positionToNumLine(position)).remove(position%6+1);

        }else {
            pos = (position / 6)*6 + list(positionToNumLine(position)).size()-1;
            s = list(positionToNumLine(position)).get(position%6).name;
            list(positionToNumLine(position)).remove(position%6);
        }
        listImage.set(pos, "image/nullTalent.png");
        updateList();
        return s;
    }

    // переводит номер позиции панели в номер строки
    public int positionToNumLine(int position) {
        return (35 - position)/6;
    }

    public void calculation() throws NullPointerException{
        characteristic = HelperCalculation.calculation(hero);
    }

    private ArrayList<Talent> allTalents(){
        return hero.allTalents();
    }

    public Talent getTalent(int position) {
        Talent talent;
        if(position == 35){
            return hero.talents1.get(0);
        }
        if(positionToNumLine(position) == 0) {
            talent = list(positionToNumLine(position)).get(position%6+1);

        }else {
            talent = list(positionToNumLine(position)).get(position%6);
        }
        return talent;
    }

    public boolean haveTalent(int position) {  // ДОРАБОТАТЬ ОБРАБОТКУ ИСКЛЮЧИТЕЛЬНЫХ СИТУАЦИЙ!!!
        try {
            if(getTalent(position) != null){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }

    public void upLvlTalent(int position, int level) {
        if(position == 35){
            hero.talents1.get(0).upLvl(level);
            return;
        }
        if(positionToNumLine(position) == 0) {
            list(positionToNumLine(position)).get(position%6+1).upLvl(level);

        }else {
            list(positionToNumLine(position)).get(position%6).upLvl(level);
        }
    }

    public ArrayList<Talent> getBaseTalent(int id) {
        ArrayList<Talent> talents = new ArrayList<>();
        for(HashMap.Entry<Talent, Integer> pair : baseTalents.entrySet()){
            if(pair.getValue() == id+1){
                talents.add(pair.getKey());
            }
        }
        return talents;
    }

    public ArrayList<String> getListCharacteristics(){

        ArrayList<String> list = new ArrayList<>();

        list.add(String.format("%(.1f",characteristic.get("Здоровье")));
        list.add(String.format("%(.1f",characteristic.get("Энергия")));
        list.add(String.format("%(.1f",characteristic.get("Сила")));
        list.add(String.format("%(.1f",characteristic.get("Разум")));
        list.add(String.format("%(.1f",characteristic.get("Хитрость")));
        list.add(String.format("%(.1f",characteristic.get("Проворство")));
        list.add(String.format("%(.1f",characteristic.get("Стойкость")));
        list.add(String.format("%(.1f",characteristic.get("Воля")));
        list.add(String.format("%(.0f",characteristic.get("Скорость")));
        list.add(String.format("%(.1f",characteristic.get("Рег. здоровья")));
        list.add(String.format("%(.1f",characteristic.get("Рег. энергии")));
        list.add(String.format("%(.0f",characteristic.get("Кража ХП")));
        list.add(String.format("%(.0f",characteristic.get("Кража МП")));
        list.add(String.format("%(.0f",characteristic.get("КД пак")));
        list.add(String.format("%(.0f",characteristic.get("Прайм")));
        list.add(String.format("%(.2f",characteristic.get("Мощь")));
        list.add(String.format("%(.0f",characteristic.get("Урон min")) + " - " + String.format("%(.0f",characteristic.get("Урон max")));
        list.add(String.format("%(.1f",characteristic.get("Атака в секунду")));
        list.add(String.format("%(.1f",characteristic.get("Пробивание")) + "%");
        list.add(String.format("%(.1f",characteristic.get("Шанс крита")) + "%");
        list.add(String.format("%(.1f",characteristic.get("Защита тела")) + "%");
        list.add(String.format("%(.1f",characteristic.get("Защита духа")) + "%");

        return list;
    }

    public void upAllTalents(int level) {
        for(Talent talent : allTalents()){
            talent.upLvl(level);
        }
    }
}
