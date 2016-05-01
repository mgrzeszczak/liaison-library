package pl.mg.liaison.testing.parameters;

import pl.mg.liaison.LiaisonBinder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static pl.mg.liaison.testing.returntype.ReturnTypeTest.callAllMethods;

/**
 * Created by maciej on 01.05.16.
 */
public class ParametersTest {

    public static void main(String[] args){
        Interface intrf = LiaisonBinder.bind(new InterfaceImpl(),LiaisonClass.class);
        intrf.listParameter(new ArrayList<>());
        intrf.onePrimitiveParameter(1);
        intrf.stringIntDoubleParameters("s",1,1);
        intrf.stringParameter("s");
        intrf.twoPrimitiveParameters(1,1);
        intrf.noParameters();
    }

}
