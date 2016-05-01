package pl.mg.liaison.testing.returntype;

import pl.mg.liaison.LiaisonBinder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Maciej on 10.04.2016.
 */
public class ReturnTypeTest {

    public static void main(String[] args) {
        Interface testInterface = LiaisonBinder.bind(new InterfaceImpl(),LiaisonClass.class);
        callAllMethods(Interface.class,testInterface);
    }

    public static <T> void callAllMethods(Class<T> type, T instance){
        for (Method m : type.getMethods()){
            try {
                m.invoke(instance);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

}
