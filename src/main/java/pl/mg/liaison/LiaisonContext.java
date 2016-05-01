package pl.mg.liaison;

import java.lang.annotation.Annotation;

/**
 * Created by Maciej on 10.04.2016.
 */
public interface LiaisonContext {

    Object call() throws Throwable;
    <T> T[] getArguments(Class<? extends T> type);
    Object[] getArguments();
    Annotation[] getAnnotations();
    Annotation[][] getParameterAnnotations();
    Class<?> getReturnType();
    String getMethodName();
    String getClassName();
    Class<?>[] getExceptionTypes();
    int getModifiers();
}
