package frameWork;

import org.testng.SkipException;
import org.testng.annotations.Test;

public class DummyTestA {
    @Test(dependsOnMethods={"testA2"})
    public void testA1(){
    	System.out.println("Test A1 start");
    }
    @Test(priority=2)
    public void testA2(){
    	throw new SkipException("Skip this");
    	//System.out.println("Test A2 start");
    	
    }
    @Test
    public void testA3(){
    	System.out.println("Test A3 start");
    }
}
