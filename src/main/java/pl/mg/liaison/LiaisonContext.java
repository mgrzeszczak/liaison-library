package pl.mg.liaison;

import java.lang.annotation.Annotation;

import static javafx.scene.input.KeyCode.T;

/**
 * Created by Maciej on 10.04.2016.
 */
public interface LiaisonContext {

    Object call() throws Throwable;
    <T> T[] getArguments(Class<? extends T> type);
    Object[] getArguments();
    Annotation[] getAnnotations();
    Class<?> getReturnType();
    String getMethodName();
    String getClassName();
}
