package pl.mg.liaison.testing.returntype;

import java.lang.reflect.Proxy;
import java.util.Collection;
import java.util.List;

/**
 * Created by Maciej on 10.04.2016.
 */
public interface Interface {

    /*
    Return types
     */
    void returnTestVoid();
    String returnTestString();
    int returnTestInt();
    Integer returnTestInteger();
    Class returnTestClass();
    Object returnTestObject();
    List<Integer> returnTestIntegerList();
    Collection<Integer> returnTestIntegerCollection();
    int[] returnTestIntArray();

}
