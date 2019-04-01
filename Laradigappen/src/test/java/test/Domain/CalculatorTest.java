
package test.Domain;

import appen.domain.Calculator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatorTest {
    private Calculator calc;
    
    @Before
    public void setUp() {
        calc = new Calculator();
    }
    
    @Test
    public void formula1() {
        String f = "2*2";
        assertEquals(4,calc.laske(f));
    }
    
    @Test
    public void formula2() {
        String f = "2+2";
        assertEquals(4,calc.laske(f));
    }
    
    @Test
    public void formula3() {
        String f = "5*(1+2)+4";
        assertEquals(19,calc.laske(f));
    }
    
    @Test
    public void formula4() {
        String f = "((1+2)*(3+4)+5)*6";
        assertEquals(156,calc.laske(f));
    }
    
    @Test
    public void formula5() {
        String f = "123*456*789";
        assertEquals(44253432,calc.laske(f));
    }
    
    @Test
    public void formula6() {
        String f = "2-2";
        assertEquals(0,calc.laske(f));
    }
    
    @Test
    public void formula7() {
        String f = "5*(1+2)-4";
        assertEquals(11,calc.laske(f));
    }
    
    @Test
    public void formula8() {
        String f = "5*(5-2)-4";
        assertEquals(11,calc.laske(f));
    }
    
    @Test
    public void formula9() {
        String f = "2/2";
        assertEquals(1,calc.laske(f));
    }
    
    @Test
    public void formula10() {
        String f = "2-(2/2)";
        assertEquals(1,calc.laske(f));
    }
    
    @Test
    public void formula11() {
        String f = "((1+2)*(3+4)-5)/4";
        assertEquals(4,calc.laske(f));
    }
}
