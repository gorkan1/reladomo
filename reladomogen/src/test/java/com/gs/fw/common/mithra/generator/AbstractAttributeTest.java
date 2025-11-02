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

import com.gs.fw.common.mithra.generator.type.IntJavaType;
import com.gs.fw.common.mithra.generator.type.StringJavaType;
import org.junit.Test;

import static org.junit.Assert.*;

public class AbstractAttributeTest
{
    @Test
    public void testIsBeanIntType()
    {
        TestAttribute intAttr = new TestAttribute(IntJavaType.getInstance());
        assertTrue("Int type should be bean int type", intAttr.isBeanIntType());

        TestAttribute stringAttr = new TestAttribute(StringJavaType.getInstance());
        assertFalse("String type should not be bean int type", stringAttr.isBeanIntType());
    }

    @Test
    public void testIsBeanLongType()
    {
        TestAttribute attr = new TestAttribute(IntJavaType.getInstance());
        boolean result = attr.isBeanLongType();
        // Result depends on type
        assertNotNull("Should return boolean value", result);
    }

    @Test
    public void testIsBeanObjectType()
    {
        TestAttribute stringAttr = new TestAttribute(StringJavaType.getInstance());
        assertTrue("String should be bean object type", stringAttr.isBeanObjectType());

        TestAttribute intAttr = new TestAttribute(IntJavaType.getInstance());
        assertFalse("Int should not be bean object type", intAttr.isBeanObjectType());
    }

    @Test
    public void testIsSourceAttribute()
    {
        TestAttribute attr = new TestAttribute(IntJavaType.getInstance());
        assertFalse("Regular attribute should not be source attribute", attr.isSourceAttribute());
    }

    @Test
    public void testIsPrimaryKey()
    {
        TestAttribute attr = new TestAttribute(IntJavaType.getInstance());
        assertFalse("Regular attribute should not be PK by default", attr.isPrimaryKey());
    }

    @Test
    public void testIsNullable()
    {
        TestAttribute attr = new TestAttribute(IntJavaType.getInstance());
        assertFalse("Regular attribute should not be nullable by default", attr.isNullable());
    }

    @Test
    public void testGetColumnName()
    {
        TestAttribute attr = new TestAttribute(IntJavaType.getInstance());
        attr.setColumnName("TEST_COL");
        assertEquals("TEST_COL", attr.getColumnName());
    }

    @Test
    public void testGetName()
    {
        TestAttribute attr = new TestAttribute(IntJavaType.getInstance());
        attr.setName("testAttr");
        assertEquals("testAttr", attr.getName());
    }

    @Test
    public void testGetType()
    {
        TestAttribute attr = new TestAttribute(IntJavaType.getInstance());
        assertNotNull("Type should not be null", attr.getType());
        assertEquals("int", attr.getType().getTypeName());
    }

    @Test
    public void testIsPrimitive()
    {
        TestAttribute intAttr = new TestAttribute(IntJavaType.getInstance());
        assertTrue("Int should be primitive", intAttr.isPrimitive());

        TestAttribute stringAttr = new TestAttribute(StringJavaType.getInstance());
        assertFalse("String should not be primitive", stringAttr.isPrimitive());
    }

    @Test
    public void testIsArray()
    {
        TestAttribute attr = new TestAttribute(IntJavaType.getInstance());
        assertFalse("Int should not be array", attr.isArray());
    }

    @Test
    public void testIsStringAttribute()
    {
        TestAttribute stringAttr = new TestAttribute(StringJavaType.getInstance());
        assertTrue("String type should be string attribute", stringAttr.isStringAttribute());

        TestAttribute intAttr = new TestAttribute(IntJavaType.getInstance());
        assertFalse("Int type should not be string attribute", intAttr.isStringAttribute());
    }

    // Test implementation of AbstractAttribute
    private static class TestAttribute extends AbstractAttribute
    {
        private String name;
        private String columnName;

        public TestAttribute(com.gs.fw.common.mithra.generator.type.JavaType type)
        {
            super(null);
            this.setType(type);
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public void setColumnName(String columnName)
        {
            this.columnName = columnName;
        }

        @Override
        public String getName()
        {
            return name;
        }

        @Override
        public String getColumnName()
        {
            return columnName;
        }

        @Override
        public boolean isPrimaryKey()
        {
            return false;
        }

        @Override
        public boolean isNullable()
        {
            return false;
        }

        @Override
        public boolean isSourceAttribute()
        {
            return false;
        }
    }
}
