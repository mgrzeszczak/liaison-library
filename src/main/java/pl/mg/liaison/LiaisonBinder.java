package pl.mg.liaison;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import static javafx.scene.input.KeyCode.T;

/**
 * Created by Maciej on 10.04.2016.
 */
public final class LiaisonBinder {

    private LiaisonBinder(){
        throw new AssertionError("No instances");
    }

    public static <T> T bind(Object instance, Class<? extends Liaison> liaison) {
        try {
            return tryBind(instance,liaison);
        } catch (Throwable e) {
            throw new AssertionError(e.getMessage());
        }
    }

    private static <T> T tryBind(Object instance, Class<? extends Liaison> liaison) throws Throwable {
        Class<?> _interface = instance.getClass().getInterfaces()[0];
        final Liaison liaison1 = liaison.newInstance();
        return (T) Proxy.newProxyInstance(_interface.getClassLoader(), new Class[]{_interface}, new LiaisonInvocationHandler(liaison1,liaison,instance));
    }

}
