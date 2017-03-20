package ru.artemsukhorukov.talents;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by user on 26.02.2017.
 */
public class Controller {
    boolean checkRed = true, checkOrange = true, checkPink = true, checkBlue = true;

    AppLogic appLogic = new AppLogic();
    HelperListTalents helperListTalents;
    private ArrayList<Talent> listTalents;

    private Controller(){}
    private static Controller instance = null;
    public static Controller getInstance(){
        if(instance == null){
            instance = new Controller();
        }
        return instance;
    }

    public void addHero(int position, Context context) {
        appLogic.addHero(position, context);
    }

    public void createHelperListTalents(Context context, int line) {
        helperListTalents = new HelperListTalents(context, line);

    }

    public void calculation() throws NullPointerException {
        appLogic.calculation();
    }

    public AppLogic getAppLogic(){
        return appLogic;
    }

    public ArrayList<Talent> getListTalents(int line, boolean red, boolean orange, boolean pink, boolean blue) throws NullPointerException{
        ArrayList<Talent> talents = new ArrayList<>();
        talents.addAll(appLogic.getBaseTalent(line));
        talents.addAll(helperListTalents.getListTalents(line, red, orange, pink, blue));
        return talents;
    }

    public void updateGrid() {

    }

    public void addTalent(Talent talent, int idd) {
        appLogic.addTalent(talent, idd);
    }

    public boolean fullPanel(int idd) {
        return appLogic.full(idd);
    }

    public boolean contains(Talent talent, int idd) {
        return appLogic.contains(talent, idd);
    }

    public String delete(int position) {
       return appLogic.delete(position);
    }

    public int positionToNum(int position){
        return appLogic.positionToNumLine(position);
    }

    public boolean haveTalent(int position) {  // проверка имеется ли талант на панеле талантов
        if(position == 35){
            return true;
        }
        return appLogic.haveTalent(position);
    }

    public Talent getTalent(int position) {  // возвращает талант по позиции в панели
        return appLogic.getTalent(position);
    }

    public void upLvlTalent(int position, int level) {
        appLogic.upLvlTalent(position, level);
    }

    public void upAllTalents(int level) {
        appLogic.upAllTalents(level);
    }

    public boolean isCheckRed() {
        return checkRed;
    }

    public void setCheckRed(boolean checkRed) {
        this.checkRed = checkRed;
    }

    public boolean isCheckOrange() {
        return checkOrange;
    }

    public void setCheckOrange(boolean checkOrange) {
        this.checkOrange = checkOrange;
    }

    public boolean isCheckPink() {
        return checkPink;
    }

    public void setCheckPink(boolean checkPink) {
        this.checkPink = checkPink;
    }

    public boolean isCheckBlue() {
        return checkBlue;
    }

    public void setCheckBlue(boolean checkBlue) {
        this.checkBlue = checkBlue;
    }
}