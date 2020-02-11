package stqa.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

    @Test
    public void testPoint(){
        Point point1 = new Point(5, 6);
        Point point2 = new Point(2, 4);

        Assert.assertEquals(point1.distance(point2), 5, 6);
    }

    public void testMyFirst(){
        Point point1 = new Point(5, 6);
        Point point2 = new Point(2, 4);

        Assert.assertEquals(MyFirst.distance(point1, point2), 5, 6);
    }
}
