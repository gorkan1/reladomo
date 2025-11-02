/*
 Copyright 2016 Goldman Sachs.
 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing,
 software distributed under the License is distributed on an
 "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 KIND, either express or implied.  See the License for the
 specific language governing permissions and limitations
 under the License.
 */

package com.gs.fw.common.mithra.generator;

import org.junit.Test;
import static org.junit.Assert.*;

public class BeanStateTest
{
    @Test
    public void testInitialState()
    {
        BeanState state = new BeanState();
        assertEquals(1, state.getIntCount());
        assertEquals(1, state.getLongCount());
        assertEquals(1, state.getObjectCount());
    }

    @Test
    public void testIncrementIntType()
    {
        BeanState state = new BeanState();
        MockAttribute intAttr = new MockAttribute(true, false, false);

        state.increment(intAttr);

        assertEquals(2, state.getIntCount());
        assertEquals(1, state.getLongCount());
        assertEquals(1, state.getObjectCount());
    }

    @Test
    public void testIncrementLongType()
    {
        BeanState state = new BeanState();
        MockAttribute longAttr = new MockAttribute(false, true, false);

        state.increment(longAttr);

        assertEquals(1, state.getIntCount());
        assertEquals(2, state.getLongCount());
        assertEquals(1, state.getObjectCount());
    }

    @Test
    public void testIncrementObjectType()
    {
        BeanState state = new BeanState();
        MockAttribute objAttr = new MockAttribute(false, false, true);

        state.increment(objAttr);

        assertEquals(1, state.getIntCount());
        assertEquals(1, state.getLongCount());
        assertEquals(2, state.getObjectCount());
    }

    @Test
    public void testMultipleIncrements()
    {
        BeanState state = new BeanState();
        MockAttribute intAttr = new MockAttribute(true, false, false);
        MockAttribute longAttr = new MockAttribute(false, true, false);
        MockAttribute objAttr = new MockAttribute(false, false, true);

        state.increment(intAttr);
        state.increment(intAttr);
        state.increment(longAttr);
        state.increment(objAttr);
        state.increment(objAttr);
        state.increment(objAttr);

        assertEquals(3, state.getIntCount());
        assertEquals(2, state.getLongCount());
        assertEquals(4, state.getObjectCount());
    }

    @Test
    public void testNoIncrementForNonBeanTypes()
    {
        BeanState state = new BeanState();
        MockAttribute nonBeanAttr = new MockAttribute(false, false, false);

        state.increment(nonBeanAttr);

        assertEquals(1, state.getIntCount());
        assertEquals(1, state.getLongCount());
        assertEquals(1, state.getObjectCount());
    }

    // Mock attribute for testing
    private static class MockAttribute extends AbstractAttribute
    {
        private final boolean isInt;
        private final boolean isLong;
        private final boolean isObject;

        public MockAttribute(boolean isInt, boolean isLong, boolean isObject)
        {
            super(null);
            this.isInt = isInt;
            this.isLong = isLong;
            this.isObject = isObject;
        }

        @Override
        public boolean isBeanIntType()
        {
            return isInt;
        }

        @Override
        public boolean isBeanLongType()
        {
            return isLong;
        }

        @Override
        public boolean isBeanObjectType()
        {
            return isObject;
        }
    }
}
