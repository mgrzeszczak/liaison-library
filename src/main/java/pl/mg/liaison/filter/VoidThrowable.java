package pl.mg.liaison.filter;

/**
 * Created by Maciej on 11.04.2016.
 */
public final class VoidThrowable extends Throwable {

    private VoidThrowable(){
        throw new AssertionError("No instances.");
    }

}
