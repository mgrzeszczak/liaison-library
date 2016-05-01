package pl.mg.liaison.testing.annotations;

import pl.mg.liaison.Liaison;
import pl.mg.liaison.LiaisonContext;
import pl.mg.liaison.filter.Annotations;
import pl.mg.liaison.filter.Filter;
import pl.mg.liaison.filter.Filters;

/**
 * Created by maciej on 01.05.16.
 */
@Filters(value = {
        @Filter(annotations = @Annotations(allow = {},disallow = {Deprecated.class})),
        @Filter(annotations = @Annotations(allow = {Deprecated.class})),
})
public class LiaisonClass implements Liaison {
    @Override
    public Object call(LiaisonContext context) throws Throwable {
        System.out.println("Calling "+context.getMethodName());
        return context.call();
    }
}
