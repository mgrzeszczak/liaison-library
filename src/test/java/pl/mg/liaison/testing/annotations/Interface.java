package pl.mg.liaison.testing.annotations;

import javax.annotation.Generated;

/**
 * Created by maciej on 01.05.16.
 */
public interface Interface {

    @Deprecated
    void deprecated();

    @Generated(value = "test")
    void test();

}
