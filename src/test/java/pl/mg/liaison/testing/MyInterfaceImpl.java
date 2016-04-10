package pl.mg.liaison.testing;

import java.util.Date;

/**
 * Created by Maciej on 10.04.2016.
 */
public class MyInterfaceImpl implements MyInterface {

    @Override
    public void test() {
        System.out.println("Hello world!!");
        System.out.println("Hello world!!");
        System.out.println("Hello world!!");
        System.out.println("Hello world!!");
        System.out.println("Hello world!!");
    }

    @Override
    public int test2() {
        return (int)new Date().getTime();
    }

    @Override
    public String test3() {
        return "WHAT UP";
    }

}
