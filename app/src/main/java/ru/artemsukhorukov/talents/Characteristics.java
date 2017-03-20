package ru.artemsukhorukov.talents;

import java.util.HashMap;

/**
 * Created by user on 01.03.2017.
 */
public class Characteristics extends HashMap<String, String> {
    public static final String NAME = "name";
    public static final String VALUE = "value";

    public Characteristics(String name, String value){
        super();
        super.put(NAME, name);
        super.put(VALUE, value);
    }
}
