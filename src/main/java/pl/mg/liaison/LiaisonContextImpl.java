package pl.mg.liaison;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maciej on 10.04.2016.
 */
final class LiaisonContextImpl implements LiaisonContext {

    private final Method method;
    private final Object[] args;
    private final Object instance;

    public LiaisonContextImpl(Method method, Object[] args, Object instance){
        this.method = method;
        this.args = args;
        this.instance = instance;
    }

    @Override
    public Object call() throws Throwable {
        return method.invoke(instance,args);
    }

    @Override
    public <T> T[] getArguments(Class<? extends T> type) {
        List<T> args = new ArrayList<T>();
        for (int i=0;i<this.args.length;i++)
            if (type.isAssignableFrom(this.args[i].getClass())) args.add((T)this.args[i]);
        return (T[])args.toArray();
    }

    @Override
    public Object[] getArguments() {
        return args;
    }

    @Override
    public Annotation[] getAnnotations() {
        return method.getAnnotations();
    }

    @Override
    public Class<?> getReturnType() {
        return method.getReturnType();
    }

    @Override
    public String getMethodName() {
        return method.getName();
    }

    @Override
    public String getClassName() {
        return method.getDeclaringClass().getName();
    }

}
