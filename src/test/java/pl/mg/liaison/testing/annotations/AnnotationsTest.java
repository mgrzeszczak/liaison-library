package pl.mg.liaison.testing.annotations;

import pl.mg.liaison.LiaisonBinder;

/**
 * Created by maciej on 01.05.16.
 */
public class AnnotationsTest {

    public static void main(String args[]){
        Interface inter = LiaisonBinder.bind(new InterfaceImpl(),LiaisonClass.class);
        inter.test();
        inter.deprecated();
    }

}
