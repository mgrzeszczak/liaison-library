# liaison-library
Simple library allowing to quickly modify selected methods of interface implementation.

# Example:
Modify HashMap so that a message is printed before methods that take no parameters are executed.

    public class App {
    
        public static void main(String[] args){
            Map<String,Integer> map = LiaisonBinder.bind(new HashMap<String,Integer>(),MyLiaison.class);
            map.put("Hello",1);
            map.get(1);
            map.size();
            map.clear();
            map.isEmpty();
        }
    
        @Filters(value = {
                @Filter(parameters = @Parameters(maxCount = 0))
        })
        public static class MyLiaison implements Liaison {
    
            public Object call(LiaisonContext liaisonContext) throws Throwable {
                System.out.println("Beginning method "+liaisonContext.getMethodName());
                return liaisonContext.call();
            }
        }
    
    
    }

Output:

    Beginning method size
    Beginning method clear
    Beginning method isEmpty
