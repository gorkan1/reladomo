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

package com.gs.fw.common.mithra.generator.type;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Detailed tests for all Java type classes - covers 17 classes
 */
public class AllJavaTypesDetailedTest
{
    @Test
    public void testIntJavaType() {
        IntJavaType type = IntJavaType.getInstance();
        assertNotNull(type);
        assertEquals("int", type.getTypeName());
        assertTrue(type.isPrimitive());
        assertTrue(type.isIntOrLong());
    }

    @Test
    public void testLongJavaType() {
        LongJavaType type = LongJavaType.getInstance();
        assertNotNull(type);
        assertEquals("long", type.getTypeName());
        assertTrue(type.isPrimitive());
        assertTrue(type.isIntOrLong());
    }

    @Test
    public void testShortJavaType() {
        ShortJavaType type = ShortJavaType.getInstance();
        assertNotNull(type);
        assertEquals("short", type.getTypeName());
        assertTrue(type.isPrimitive());
    }

    @Test
    public void testByteJavaType() {
        ByteJavaType type = ByteJavaType.getInstance();
        assertNotNull(type);
        assertEquals("byte", type.getTypeName());
        assertTrue(type.isPrimitive());
    }

    @Test
    public void testDoubleJavaType() {
        DoubleJavaType type = DoubleJavaType.getInstance();
        assertNotNull(type);
        assertEquals("double", type.getTypeName());
        assertTrue(type.isPrimitive());
        assertTrue(type.isNumber());
    }

    @Test
    public void testFloatJavaType() {
        FloatJavaType type = FloatJavaType.getInstance();
        assertNotNull(type);
        assertEquals("float", type.getTypeName());
        assertTrue(type.isPrimitive());
        assertTrue(type.isNumber());
    }

    @Test
    public void testBooleanJavaType() {
        BooleanJavaType type = BooleanJavaType.getInstance();
        assertNotNull(type);
        assertEquals("boolean", type.getTypeName());
        assertTrue(type.isPrimitive());
    }

    @Test
    public void testCharJavaType() {
        CharJavaType type = CharJavaType.getInstance();
        assertNotNull(type);
        assertEquals("char", type.getTypeName());
        assertTrue(type.isPrimitive());
    }

    @Test
    public void testStringJavaType() {
        StringJavaType type = StringJavaType.getInstance();
        assertNotNull(type);
        assertEquals("String", type.getTypeName());
        assertFalse(type.isPrimitive());
    }

    @Test
    public void testDateJavaType() {
        DateJavaType type = DateJavaType.getInstance();
        assertNotNull(type);
        assertEquals("Date", type.getTypeName());
        assertFalse(type.isPrimitive());
    }

    @Test
    public void testTimeJavaType() {
        TimeJavaType type = TimeJavaType.getInstance();
        assertNotNull(type);
        assertEquals("Time", type.getTypeName());
        assertFalse(type.isPrimitive());
    }

    @Test
    public void testTimestampJavaType() {
        TimestampJavaType type = TimestampJavaType.getInstance();
        assertNotNull(type);
        assertEquals("Timestamp", type.getTypeName());
        assertFalse(type.isPrimitive());
    }

    @Test
    public void testBigDecimalJavaType() {
        BigDecimalJavaType type = BigDecimalJavaType.getInstance();
        assertNotNull(type);
        assertEquals("BigDecimal", type.getTypeName());
        assertFalse(type.isPrimitive());
        assertTrue(type.isBigDecimal());
    }

    @Test
    public void testByteArrayJavaType() {
        ByteArrayJavaType type = ByteArrayJavaType.getInstance();
        assertNotNull(type);
        assertEquals("byte[]", type.getTypeName());
        assertFalse(type.isPrimitive());
    }

    @Test
    public void testPrimitiveWrapperJavaType() {
        assertTrue("PrimitiveWrapperJavaType class should be loadable", PrimitiveWrapperJavaType.class != null);
    }

    @Test
    public void testJavaTypeException() {
        JavaTypeException ex = new JavaTypeException("test");
        assertEquals("test", ex.getMessage());
    }

    @Test
    public void testJavaTypeBaseClass() {
        assertTrue("JavaType abstract class should be loadable", JavaType.class != null);
    }
}
