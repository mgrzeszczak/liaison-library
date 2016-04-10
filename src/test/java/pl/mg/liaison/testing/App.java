package pl.mg.liaison.testing;

import pl.mg.liaison.LiaisonBinder;
/**
 * Created by Maciej on 10.04.2016.
 */
public class App {

    public static void main(String[] args){
        MyInterface myInterface = LiaisonBinder.bind(new MyInterfaceImpl(), MyLiaison.class);

        myInterface.test();
        System.out.println(myInterface.test2());
        System.out.println(myInterface.test3());
    }

}
