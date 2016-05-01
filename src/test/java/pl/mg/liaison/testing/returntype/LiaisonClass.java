package pl.mg.liaison.testing.returntype;

import pl.mg.liaison.Liaison;
import pl.mg.liaison.filter.*;
import pl.mg.liaison.LiaisonContext;

import java.util.Collection;
import java.util.List;

/**
 * Created by Maciej on 10.04.2016.
 */
@Filters(value = {
        @Filter(
                returns = @Returns(allow = {void.class},disallow = {})
        ),
        @Filter(
                returns = @Returns(allow = {int.class},disallow = {})
        ),
        @Filter(
                returns = @Returns(allow = {String.class},disallow = {})
        ),
        @Filter(
                returns = @Returns(allow = {int[].class},disallow = {})
        ),
        @Filter(
                returns = @Returns(allow = {List.class},disallow = {})
        ),
        @Filter(
                returns = @Returns(allow = {Collection.class},disallow = {})
        ),
        @Filter(
                returns = @Returns(allow = {Integer.class},disallow = {})
        ),
        @Filter(
                returns = @Returns(allow = {Class.class},disallow = {})
        ),
        @Filter(
                returns = @Returns(allow = {Object.class},disallow = {})
        )
})
public class LiaisonClass implements Liaison {

    @Override
    public Object call(LiaisonContext context) throws Throwable {
        System.out.println("Calling "+context.getMethodName());
        return context.call();
    }

}
