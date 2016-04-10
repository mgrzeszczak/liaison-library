package pl.mg.liaison;

import java.lang.reflect.Method;

/**
 * Created by Maciej on 10.04.2016.
 */
public interface Liaison {

    //Object call(Method method, Object instance, Object[] args) throws Throwable;
    Object call(LiaisonContext context) throws Throwable;

}
