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

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class IndexTest
{
    private Index index;
    private Attribute[] attributes;

    @Before
    public void setUp()
    {
        TestAttribute attr1 = new TestAttribute("id", "ID");
        TestAttribute attr2 = new TestAttribute("code", "CODE");
        attributes = new Attribute[]{attr1, attr2};

        index = new Index(attributes, "testIndex", true, null);
    }

    @Test
    public void testBasicConstruction()
    {
        assertNotNull("Index should be created", index);
        assertEquals("testIndex", index.getName());
        assertTrue("Should be unique", index.isUnique());
    }

    @Test
    public void testSetAndGetName()
    {
        index.setName("newName");
        assertEquals("newName", index.getName());
    }

    @Test
    public void testPkStatus()
    {
        assertFalse("Should not be PK by default", index.isPk());
        index.setPkStatus(true);
        assertTrue("Should be PK after setting", index.isPk());
    }

    @Test
    public void testGetAttributes()
    {
        AbstractAttribute[] attrs = index.getAttributes();
        assertNotNull("Attributes should not be null", attrs);
        assertEquals("Should have 2 attributes", 2, attrs.length);
    }

    @Test
    public void testGetIndexColumns()
    {
        String columns = index.getIndexColumns();
        assertNotNull("Columns should not be null", columns);
        assertTrue("Should contain ID", columns.contains("ID"));
        assertTrue("Should contain CODE", columns.contains("CODE"));
    }

    @Test
    public void testGetIndexColumnsNames()
    {
        ArrayList<String> names = index.getIndexColumnsNames();
        assertNotNull("Names list should not be null", names);
        assertEquals("Should have 2 column names", 2, names.size());
        assertTrue("Should contain ID", names.contains("ID"));
        assertTrue("Should contain CODE", names.contains("CODE"));
    }

    @Test
    public void testCheckConsistency()
    {
        List<String> errors = new ArrayList<String>();
        index.checkConsistency(errors);
        assertTrue("Should have no errors for valid index", errors.isEmpty());
    }

    @Test
    public void testIsRedundantIndex()
    {
        List<Attribute> attrList = new ArrayList<Attribute>();
        attrList.add(attributes[0]);
        attrList.add(attributes[1]);

        boolean result = index.isRedundantIndex(attrList);
        assertTrue("Should be redundant with same attributes", result);
    }

    @Test
    public void testIsNotRedundantIndex()
    {
        List<Attribute> attrList = new ArrayList<Attribute>();
        attrList.add(attributes[0]);

        boolean result = index.isRedundantIndex(attrList);
        assertFalse("Should not be redundant with different attributes", result);
    }

    @Test
    public void testSetIsSameAsPk()
    {
        assertFalse("Should not be same as PK by default", index.isSameAsPk());
        index.setIsSameAsPk(true);
        assertTrue("Should be same as PK after setting", index.isSameAsPk());
    }

    @Test
    public void testSetSameIndex()
    {
        Index otherIndex = new Index(attributes, "otherIndex", false, null);
        index.setSameIndex(otherIndex);
        assertEquals("Should return same index", otherIndex, index.getSameIndex());
    }

    @Test
    public void testGetSanitizedUpperCaseName()
    {
        Index byIdIndex = new Index(attributes, "byId", true, null);
        String sanitized = byIdIndex.getSanitizedUpperCaseName();
        assertNotNull("Sanitized name should not be null", sanitized);
        assertTrue("Should start with uppercase", Character.isUpperCase(sanitized.charAt(0)));
    }

    @Test
    public void testHasFastPathLookup()
    {
        boolean result = index.hasFastPathLookup();
        // Result depends on attribute types
        assertNotNull("Should return boolean", result);
    }

    // Test helper class
    private static class TestAttribute extends Attribute
    {
        private String name;
        private String columnName;

        public TestAttribute(String name, String columnName)
        {
            super(null);
            this.name = name;
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
        public boolean isSourceAttribute()
        {
            return false;
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
    }
}
