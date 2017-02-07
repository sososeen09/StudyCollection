package com.longge.studycollection;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * Created by yunlong.su on 2017/2/4.
 */


public class AdderUnitTest extends TestCase {
    private Adder mAdder;

    @Override
    protected void setUp() throws Exception {
        mAdder = new AdderImpl();
        super.setUp();
    }

    @Test
    public void testAdd() {
        assertEquals(2, mAdder.add(1, 1));
    }
}
