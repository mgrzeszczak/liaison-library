package pl.mg.liaison.testing.throwables;

import java.io.IOException;

/**
 * Created by maciej on 01.05.16.
 */
public class InterfaceImpl implements Interface {

    @Override
    public void throwsNullPointer() throws NullPointerException {

    }

    @Override
    public void throwsIOException() throws IOException {

    }

    @Override
    public void throwsException() throws Exception {

    }

    @Override
    public void throwsIOAndNullPointer() throws IOException, NullPointerException {

    }

    @Override
    public void throwsNone() {

    }
}
