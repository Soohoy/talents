package ru.artemsukhorukov.talents;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

/**
 * Created by user on 01.03.2017.
 */
@Root
public class ListTalents {
    @ElementList
    public ArrayList<Talent> redList = new ArrayList<>();
    @ElementList
    public ArrayList<Talent> orangeList = new ArrayList<>();
    @ElementList
    public ArrayList<Talent> pinkList = new ArrayList<>();
    @ElementList
    public ArrayList<Talent> blueList = new ArrayList<>();
}
