package flik;
import org.junit.Assert;
import org.junit.Test;

public class FlikTest {
    @Test
    public void testOneEqualsOne() {
        Assert.assertTrue(Flik.isSameNumber(1, 1));
    }

    @Test
    public void testFlikFromZeroToFiveHundred() {
        int i = 0, j = 0;

        while (i <= 500 && j <= 500) {
            Assert.assertTrue(i + " does not equal " + j,Flik.isSameNumber(i, j));
            i += 1;
            j += 1;
        }
    }
}
