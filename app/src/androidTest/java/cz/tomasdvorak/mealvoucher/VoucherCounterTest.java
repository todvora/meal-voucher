package cz.tomasdvorak.mealvoucher;

import junit.framework.Assert;
import junit.framework.TestCase;

public class VoucherCounterTest extends TestCase {

    public void testCount() throws Exception {
        VoucherCountResult result = VoucherCounter.compute("4.2");
        Assert.assertEquals(1, result.getUnderCount());
        Assert.assertEquals(2, result.getOverCount());
        Assert.assertEquals(2.0d, result.getUnderPrice());
        Assert.assertEquals(0.2d, result.getOverPrice());
    }
}