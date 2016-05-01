# liaison-library
Simple library allowing to quickly modify selected methods of interface implementation without touching the implementation code at all.

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

#Output:

    Beginning method size
    Beginning method clear
    Beginning method isEmpty

#License

    Copyright 2016 Maciej Grzeszczak
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
        http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
