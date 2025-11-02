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

public class CardinalityTest
{
    @Test
    public void testOneToMany()
    {
        Cardinality cardinality = Cardinality.getByName("one-to-many");

        assertNotNull("One-to-many should not be null", cardinality);
        assertTrue("Should be to-many", cardinality.isToMany());
        assertFalse("Should not be from-many", cardinality.isFromMany());
        assertFalse("Should not be many-to-many", cardinality.isManyToMany());
        assertEquals("Name should be OneToMany", "OneToMany", cardinality.getNameAsClassName());
    }

    @Test
    public void testManyToOne()
    {
        Cardinality cardinality = Cardinality.getByName("many-to-one");

        assertNotNull("Many-to-one should not be null", cardinality);
        assertFalse("Should not be to-many", cardinality.isToMany());
        assertTrue("Should be from-many", cardinality.isFromMany());
        assertFalse("Should not be many-to-many", cardinality.isManyToMany());
        assertEquals("Name should be ManyToOne", "ManyToOne", cardinality.getNameAsClassName());
    }

    @Test
    public void testManyToMany()
    {
        Cardinality cardinality = Cardinality.getByName("many-to-many");

        assertNotNull("Many-to-many should not be null", cardinality);
        assertTrue("Should be to-many", cardinality.isToMany());
        assertTrue("Should be from-many", cardinality.isFromMany());
        assertTrue("Should be many-to-many", cardinality.isManyToMany());
        assertEquals("Name should be ManyToMany", "ManyToMany", cardinality.getNameAsClassName());
    }

    @Test
    public void testOneToOne()
    {
        Cardinality cardinality = Cardinality.getByName("one-to-one");

        assertNotNull("One-to-one should not be null", cardinality);
        assertFalse("Should not be to-many", cardinality.isToMany());
        assertFalse("Should not be from-many", cardinality.isFromMany());
        assertFalse("Should not be many-to-many", cardinality.isManyToMany());
        assertEquals("Name should be OneToOne", "OneToOne", cardinality.getNameAsClassName());
    }

    @Test
    public void testReverseCardinality_OneToMany()
    {
        Cardinality oneToMany = Cardinality.getByName("one-to-many");
        Cardinality reverse = oneToMany.getReverseCardinality();

        assertNotNull("Reverse should not be null", reverse);
        assertFalse("Reverse should not be to-many", reverse.isToMany());
        assertTrue("Reverse should be from-many", reverse.isFromMany());
        assertEquals("Reverse should be ManyToOne", "ManyToOne", reverse.getNameAsClassName());
    }

    @Test
    public void testReverseCardinality_ManyToOne()
    {
        Cardinality manyToOne = Cardinality.getByName("many-to-one");
        Cardinality reverse = manyToOne.getReverseCardinality();

        assertNotNull("Reverse should not be null", reverse);
        assertTrue("Reverse should be to-many", reverse.isToMany());
        assertFalse("Reverse should not be from-many", reverse.isFromMany());
        assertEquals("Reverse should be OneToMany", "OneToMany", reverse.getNameAsClassName());
    }

    @Test
    public void testReverseCardinality_ManyToMany()
    {
        Cardinality manyToMany = Cardinality.getByName("many-to-many");
        Cardinality reverse = manyToMany.getReverseCardinality();

        assertNotNull("Reverse should not be null", reverse);
        assertTrue("Reverse should be to-many", reverse.isToMany());
        assertTrue("Reverse should be from-many", reverse.isFromMany());
        assertEquals("Reverse should remain ManyToMany", "ManyToMany", reverse.getNameAsClassName());
    }

    @Test
    public void testReverseCardinality_OneToOne()
    {
        Cardinality oneToOne = Cardinality.getByName("one-to-one");
        Cardinality reverse = oneToOne.getReverseCardinality();

        assertNotNull("Reverse should not be null", reverse);
        assertFalse("Reverse should not be to-many", reverse.isToMany());
        assertFalse("Reverse should not be from-many", reverse.isFromMany());
        assertEquals("Reverse should remain OneToOne", "OneToOne", reverse.getNameAsClassName());
    }

    @Test
    public void testGetByName_InvalidName()
    {
        Cardinality cardinality = Cardinality.getByName("invalid-name");
        assertNull("Should return null for invalid name", cardinality);
    }

    @Test
    public void testGetByName_NullName()
    {
        Cardinality cardinality = Cardinality.getByName(null);
        assertNull("Should return null for null name", cardinality);
    }

    @Test
    public void testSameInstanceReturned()
    {
        Cardinality first = Cardinality.getByName("one-to-many");
        Cardinality second = Cardinality.getByName("one-to-many");

        assertSame("Should return same instance for same name", first, second);
    }
}
