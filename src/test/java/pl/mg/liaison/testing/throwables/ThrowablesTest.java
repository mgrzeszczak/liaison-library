package pl.mg.liaison.testing.throwables;

import pl.mg.liaison.LiaisonBinder;

/**
 * Created by maciej on 01.05.16.
 */
public class ThrowablesTest {

    public static void main(String args[]){
        Interface inter = LiaisonBinder.bind(new InterfaceImpl(),LiaisonClass.class);
        try {
            inter.throwsException();
            inter.throwsIOAndNullPointer();
            inter.throwsIOException();
            inter.throwsNone();
            inter.throwsNullPointer();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
