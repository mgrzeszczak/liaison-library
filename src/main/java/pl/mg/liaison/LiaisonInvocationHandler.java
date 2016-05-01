package pl.mg.liaison;

import pl.mg.liaison.filter.Filters;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Maciej on 10.04.2016.
 */
final class LiaisonInvocationHandler implements InvocationHandler {

    private final Liaison liaison;
    private final Class<? extends Liaison> liaisonClass;
    private final Object instance;
    private final Map<Method,LiaisonContext> contextCache = new HashMap<Method, LiaisonContext>();
    private final FilterProcessor filterProcessor;

    LiaisonInvocationHandler(Liaison liaison, Class<? extends Liaison> liaisonClass, Object instance) {
        this.liaison = liaison;
        this.liaisonClass = liaisonClass;
        this.instance = instance;
        if (this.liaisonClass.getAnnotation(Filters.class)==null) throw new AssertionError(liaisonClass.getName()+" is not annotated with "+Filters.class.getName());
        this.filterProcessor = new FilterProcessor(this.liaisonClass.getAnnotation(Filters.class));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        LiaisonContext context;
        if (contextCache.containsKey(method)) context = contextCache.get(method);
        else {
            context = new LiaisonContextImpl(method,args,instance);
            contextCache.put(method,context);
        }
        boolean eligible = filterProcessor.eligible(method);
        if (eligible) return liaison.call(context);
        else return method.invoke(instance,args);
    }

}
