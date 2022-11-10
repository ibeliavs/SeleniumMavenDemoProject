package common;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

public class DataProviderDemo {
    @DataProvider(name="swagLabs")
    public Object[][] dataSet1(Method method){

        Object[][] testData = null;

       if(method.getName().equals("testOne")){
           testData = new Object[][] {
                   {"standard_user", "secret_sauce"},
                   {"locked_out_user", "secret_sauce"},
                   {"problem_user", "secret_sauce"},
                   {"performance_glitch_user", "secret_sauce"}};
       } else if (method.getName().equals("loginTest") ) {
           testData = new Object[][] {
                   {"standard_user", "secret_sauce", "test1"},
                   {"locked_out_user", "secret_sauce", "test2"},
                   {"problem_user", "secret_sauce", "test3"},
                   {"performance_glitch_user", "secret_sauce", "test4"}};
        }

        return testData;
    }
}
