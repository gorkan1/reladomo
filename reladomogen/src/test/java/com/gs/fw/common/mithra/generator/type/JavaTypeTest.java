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
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class JavaTypeTest
{
    private JavaType type;
    private String expectedTypeName;
    private String expectedJavaTypeString;
    private boolean expectedIsPrimitive;

    public JavaTypeTest(JavaType type, String expectedTypeName, String expectedJavaTypeString, boolean expectedIsPrimitive)
    {
        this.type = type;
        this.expectedTypeName = expectedTypeName;
        this.expectedJavaTypeString = expectedJavaTypeString;
        this.expectedIsPrimitive = expectedIsPrimitive;
    }

    @Parameters(name = "{1}")
    public static Collection<Object[]> data()
    {
        return Arrays.asList(new Object[][]{
            {IntJavaType.getInstance(), "int", "int", true},
            {LongJavaType.getInstance(), "long", "long", true},
            {ShortJavaType.getInstance(), "short", "short", true},
            {ByteJavaType.getInstance(), "byte", "byte", true},
            {DoubleJavaType.getInstance(), "double", "double", true},
            {FloatJavaType.getInstance(), "float", "float", true},
            {BooleanJavaType.getInstance(), "boolean", "boolean", true},
            {CharJavaType.getInstance(), "char", "char", true},
            {StringJavaType.getInstance(), "String", "String", false},
            {DateJavaType.getInstance(), "Date", "Date", false},
            {TimeJavaType.getInstance(), "Time", "Time", false},
            {TimestampJavaType.getInstance(), "Timestamp", "Timestamp", false},
            {BigDecimalJavaType.getInstance(), "BigDecimal", "BigDecimal", false},
            {ByteArrayJavaType.getInstance(), "byte[]", "byte[]", false}
        });
    }

    @Test
    public void testGetTypeName()
    {
        assertEquals(expectedTypeName, type.getTypeName());
    }

    @Test
    public void testGetJavaTypeString()
    {
        assertEquals(expectedJavaTypeString, type.getJavaTypeString());
    }

    @Test
    public void testIsPrimitive()
    {
        assertEquals(expectedIsPrimitive, type.isPrimitive());
    }

    @Test
    public void testGetJavaTypeStringPrimary()
    {
        assertNotNull("Should return primary type string", type.getJavaTypeStringPrimary());
    }

    @Test
    public void testGetSqlType()
    {
        assertTrue("SQL type should be valid", type.getSqlType() != 0);
    }

    @Test
    public void testGetSqlTypeAsString()
    {
        assertNotNull("SQL type string should not be null", type.getSqlTypeAsString());
    }

    @Test
    public void testGetSqlDataType()
    {
        assertNotNull("SQL data type should not be null", type.getSqlDataType());
    }

    @Test
    public void testGetDefaultInitialValue()
    {
        assertNotNull("Default initial value should not be null", type.getDefaultInitialValue());
    }

    @Test
    public void testGetResultSetName()
    {
        assertNotNull("ResultSet name should not be null", type.getResultSetName());
    }

    @Test
    public void testGetIoType()
    {
        String ioType = type.getIoType();
        assertNotNull("IO type should not be null", ioType);
        assertTrue("IO type should start with uppercase", Character.isUpperCase(ioType.charAt(0)));
    }

    @Test
    public void testConvertToObject()
    {
        String converted = type.convertToObject("value");
        assertNotNull("Convert to object should not be null", converted);
    }

    @Test
    public void testConvertToPrimitive()
    {
        String converted = type.convertToPrimitive("value");
        assertNotNull("Convert to primitive should not be null", converted);
    }

    @Test
    public void testIsComparableToSameType()
    {
        assertTrue("Type should be comparable to itself", type.isComparableTo(type));
    }
}
