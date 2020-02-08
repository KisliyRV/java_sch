package stqa.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

    @Test
    public void testArea() {
        Point m = new Point(1,2);
        Assert.assertEquals(m.distance(), 3.605551275463989);
    }
}
