package pl.mg.liaison.testing;

import java.util.Date;

/**
 * Created by Maciej on 10.04.2016.
 */
public class MyInterfaceImpl implements MyInterface {

    @Override
    public void test() throws Exception {
        System.out.println("Hello world!!");
        System.out.println("Hello world!!");
        System.out.println("Hello world!!");
        System.out.println("Hello world!!");
        System.out.println("Hello world!!");
    }

    @Override
    @Deprecated
    public int test2() {
        return (int)new Date().getTime();
    }

    @Override
    public String test3(String name) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "WHAT UP "+name;
    }

}
