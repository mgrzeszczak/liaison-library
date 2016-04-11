package pl.mg.liaison.testing;

import pl.mg.liaison.LiaisonBinder;

import java.io.Console;
import java.lang.reflect.Method;

/**
 * Created by Maciej on 10.04.2016.
 */
public class App {

    public static void main(String[] args){
        MyInterface myInterface = LiaisonBinder.bind(new MyInterfaceImpl(), MyLiaison.class);
        try {
            myInterface.test();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(myInterface.test2());
        System.out.println(myInterface.test3("Janek"));
    }

    public static void m(){

    }

}
