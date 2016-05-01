package pl.mg.liaison.testing.throwables;

import pl.mg.liaison.Liaison;
import pl.mg.liaison.LiaisonContext;
import pl.mg.liaison.filter.Filter;
import pl.mg.liaison.filter.Filters;
import pl.mg.liaison.filter.Throws;
import pl.mg.liaison.filter.VoidThrowable;
/**
 * Created by maciej on 01.05.16.
 */
@Filters(value = {
    //@Filter(_throws = @Throws(allow = {IOException.class})),
    //@Filter(_throws = @Throws(allow = {NullPointerException.class})),
    //@Filter(_throws = @Throws(disallow = {NullPointerException.class,IOException.class,VoidThrowable.class}))
        @Filter(_throws = @Throws(allow = {VoidThrowable.class}))
})
public class LiaisonClass implements Liaison {
    @Override
    public Object call(LiaisonContext context) throws Throwable {
        System.out.println("Calling "+context.getMethodName());
        return context.call();
    }
}
