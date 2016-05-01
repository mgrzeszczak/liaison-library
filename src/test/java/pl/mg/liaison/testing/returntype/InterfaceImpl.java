package pl.mg.liaison.testing.returntype;

import java.util.Collection;
import java.util.List;

/**
 * Created by Maciej on 10.04.2016.
 */
public class InterfaceImpl implements Interface {


    @Override
    public void returnTestVoid() {

    }

    @Override
    public String returnTestString() {
        return null;
    }

    @Override
    public int returnTestInt() {
        return 0;
    }

    @Override
    public Integer returnTestInteger() {
        return null;
    }

    @Override
    public Class returnTestClass() {
        return null;
    }

    @Override
    public Object returnTestObject() {
        return null;
    }

    @Override
    public List<Integer> returnTestIntegerList() {
        return null;
    }

    @Override
    public Collection<Integer> returnTestIntegerCollection() {
        return null;
    }

    @Override
    public int[] returnTestIntArray() {
        return new int[0];
    }
}
