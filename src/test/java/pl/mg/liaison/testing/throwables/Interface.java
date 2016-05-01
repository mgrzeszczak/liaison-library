package pl.mg.liaison.testing.throwables;

import java.io.IOException;

/**
 * Created by maciej on 01.05.16.
 */
public interface Interface {

    void throwsNullPointer() throws NullPointerException;
    void throwsIOException()throws IOException;
    void throwsException() throws Exception;
    void throwsIOAndNullPointer() throws IOException,NullPointerException;
    void throwsNone();

}
