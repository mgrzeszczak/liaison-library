package pl.mg.liaison;

import java.lang.annotation.Annotation;

import static javafx.scene.input.KeyCode.T;

/**
 * Created by Maciej on 10.04.2016.
 */
public interface LiaisonContext {

    Object call() throws Throwable;
    <T> T getArgument(String name);
    <T> T[] getArguments(Class<? extends T> type);
    Annotation[] getAnnotations();
    Class<?> getReturnType();
}
