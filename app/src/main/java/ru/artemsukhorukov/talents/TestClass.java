package ru.artemsukhorukov.talents;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by user on 11.03.2017.
 */
@Root
public class TestClass {
    @Element
    public Float[] increment = new Float[10];
}
