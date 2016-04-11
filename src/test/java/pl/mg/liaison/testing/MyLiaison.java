package pl.mg.liaison.testing;

import pl.mg.liaison.Liaison;
import pl.mg.liaison.filter.*;
import pl.mg.liaison.LiaisonContext;

/**
 * Created by Maciej on 10.04.2016.
 */
@Filters(value = {
        @Filter(
                annotations = @Annotations(disallow = {},allow = {Deprecated.class}),
                returns = @Returns(allow = {int.class},disallow = {}),
                name = @Name(regex = "test."),
                parameters = @Parameters(allow = {},disallow = {},maxCount = 0),
                _throws = @Throws(allow = {VoidThrowable.class},disallow = {})
        )
})
public class MyLiaison implements Liaison {

    @Override
    public Object call(LiaisonContext context) throws Throwable {
        long timeBegin = System.currentTimeMillis();
        Object ret = context.call();
        System.out.println("Method "+context.getMethodName()+ " execution time: "+ (System.currentTimeMillis()-timeBegin));
        return ret;
    }

}
