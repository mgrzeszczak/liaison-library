package pl.mg.liaison.testing;

import java.lang.reflect.Proxy;

/**
 * Created by Maciej on 10.04.2016.
 */
public interface MyInterface {

    void test() throws Exception;

    @Deprecated
    int test2();

    String test3(String name);

}
