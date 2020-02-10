package stqa.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

    @Test
    public void testArea() {
        Point m = new Point(2.0, 5.0);
        Assert.assertEquals(m.distance(), 10.0);
    }
}
