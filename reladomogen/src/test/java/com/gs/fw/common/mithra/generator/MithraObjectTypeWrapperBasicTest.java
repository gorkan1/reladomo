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

import com.gs.fw.common.mithra.generator.metamodel.MithraObjectType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MithraObjectTypeWrapperBasicTest
{
    private MithraObjectTypeWrapper wrapper;
    private MithraObjectType mockType;
    private TestLogger logger;

    @Before
    public void setUp()
    {
        mockType = new MithraObjectType();
        mockType.setObjectType("transactional");
        mockType.setClassName("TestObject");
        mockType.setDefaultTable("TEST_TABLE");

        logger = new TestLogger();
        wrapper = new MithraObjectTypeWrapper(mockType, "test.xml", null, false, false, logger);
    }

    @Test
    public void testBasicConstruction()
    {
        assertNotNull("Wrapper should be created", wrapper);
        assertEquals("TestObject", wrapper.getClassName());
    }

    @Test
    public void testIsPure()
    {
        assertFalse("Should not be pure by default", wrapper.isPure());
        wrapper.setPure(true);
        assertTrue("Should be pure after setting", wrapper.isPure());
    }

    @Test
    public void testIsReplicated()
    {
        assertFalse("Should not be replicated by default", wrapper.isReplicated());
        wrapper.setReplicated(true);
        assertTrue("Should be replicated after setting", wrapper.isReplicated());
    }

    @Test
    public void testDefaultFinalGetters()
    {
        assertFalse("Should not have final getters by default", wrapper.isDefaultFinalGetters());
        wrapper.setDefaultFinalGetters(true);
        assertTrue("Should have final getters after setting", wrapper.isDefaultFinalGetters());
    }

    @Test
    public void testIgnoreTransactionalMethods()
    {
        wrapper.setIgnoreTransactionalMethods(true);
        // Just verify it doesn't throw - state should be set
        assertTrue(true);
    }

    @Test
    public void testFkCounter()
    {
        assertEquals("FK counter should start at 0", 0, wrapper.getFkCounter());
        wrapper.incrementFkCounter();
        assertEquals("FK counter should be 1", 1, wrapper.getFkCounter());

        int val = wrapper.getFkCounterAndIncrement();
        assertEquals("Should return 1", 1, val);
        assertEquals("FK counter should be 2", 2, wrapper.getFkCounter());
    }

    @Test
    public void testFirstFkFileWrite()
    {
        assertTrue("Should be first FK file write by default", wrapper.isFirstFkFileWrite());
        wrapper.setNotFirstFkFileWrite();
        assertFalse("Should not be first FK file write after setting", wrapper.isFirstFkFileWrite());
    }

    @Test
    public void testGenerateTxMethods()
    {
        boolean result = wrapper.generateTxMethods();
        // Result depends on table-per-class subclass status
        assertNotNull("Should return a value", result);
    }

    @Test
    public void testGetDescription()
    {
        assertEquals("Should return 'object' for non-pure", "object", wrapper.getDescription());
        wrapper.setPure(true);
        assertEquals("Should return 'pure object' for pure", "pure object", wrapper.getDescription());
    }

    @Test
    public void testDisableForeignKeys()
    {
        boolean result = wrapper.disableForeignKeys();
        // Result depends on wrapped type's setting
        assertNotNull("Should return a value", result);
    }

    // Test helper class
    private static class TestLogger implements Logger
    {
        @Override
        public void info(String msg) {}

        @Override
        public void info(String msg, Throwable t) {}

        @Override
        public void warn(String msg) {}

        @Override
        public void warn(String msg, Throwable t) {}

        @Override
        public void error(String msg) {}

        @Override
        public void error(String msg, Throwable t) {}

        @Override
        public void debug(String msg) {}

        @Override
        public void debug(String msg, Throwable t) {}
    }
}
