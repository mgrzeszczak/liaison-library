package pl.mg.liaison.testing.parameters;

import java.util.List;

/**
 * Created by maciej on 01.05.16.
 */
public interface Interface {

    void noParameters();
    void onePrimitiveParameter(int i);
    void twoPrimitiveParameters(int a, int b);
    void listParameter(List<Integer>list);
    void stringParameter(String s);
    void stringIntDoubleParameters(String s, int i, double d);

}
