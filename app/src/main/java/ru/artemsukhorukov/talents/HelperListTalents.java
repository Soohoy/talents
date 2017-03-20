package ru.artemsukhorukov.talents;

import android.content.Context;
import android.content.res.AssetManager;

import org.simpleframework.xml.core.Persister;

import java.util.ArrayList;

/**
 * Created by user on 01.03.2017.
 */

public class HelperListTalents {
    ListTalents[] talents = new ListTalents[6];


    public HelperListTalents(Context context, int line){
        if(talents[line] == null) {
            AssetManager assetManager = context.getAssets();
            Persister persister = new Persister();
            try {
                switch (line) {
                    case 0:
                        talents[line] = persister.read(ListTalents.class, assetManager.open("talents/talents1.xml"));
                        break;
                    case 1:
                        talents[line] = persister.read(ListTalents.class, assetManager.open("talents/talents2.xml"));
                        break;
                    case 2:
                        talents[line] = persister.read(ListTalents.class, assetManager.open("talents/talents3.xml"));
                        break;
                    case 3:
                        talents[line] = persister.read(ListTalents.class, assetManager.open("talents/talents4.xml"));
                        break;
                    case 4:
                        talents[line] = persister.read(ListTalents.class, assetManager.open("talents/talents5.xml"));
                        break;
                    case 5:
                        talents[line] = persister.read(ListTalents.class, assetManager.open("talents/talents6.xml"));
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Talent> getListTalents(int id, boolean red, boolean orange, boolean pink, boolean blue){
        ArrayList<Talent> list = new ArrayList<>();
        try {
            if (red) {
                for (int i = 0; i < talents[id].redList.size(); i++) {
                    list.add(talents[id].redList.get(i));
                }
            }
            if (orange) {
                for (int i = 0; i < talents[id].orangeList.size(); i++) {
                    list.add(talents[id].orangeList.get(i));
                }
            }
            if (pink) {
                for (int i = 0; i < talents[id].pinkList.size(); i++) {
                    list.add(talents[id].pinkList.get(i));
                }
            }
            if (blue) {
                for (int i = 0; i < talents[id].blueList.size(); i++) {
                    list.add(talents[id].blueList.get(i));
                }
            }

            return list;
        }catch (Exception e){
            return new ArrayList<>();
        }
    }
}