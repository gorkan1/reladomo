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

package com.gs.fw.common.mithra.generator.metamodel;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Consolidated test for metamodel types - covers 29 classes
 */
public class MetamodelTypesTest
{
    @Test public void testAttributeType() {
        AttributeType t = new AttributeType();
        t.setName("test");
        assertEquals("test", t.getName());
        t.setJavaType("String");
        assertEquals("String", t.getJavaType());
        t.setColumnName("TEST_COL");
        assertEquals("TEST_COL", t.getColumnName());
        t.setNullable(true);
        assertTrue(t.isNullable());
        t.setPrimaryKey(true);
        assertTrue(t.isPrimaryKey());
    }

    @Test public void testIndexType() {
        IndexType t = new IndexType();
        t.setName("testIndex");
        assertEquals("testIndex", t.getName());
        t.setUnique(true);
        assertTrue(t.isUnique());
    }

    @Test public void testAsOfAttributeType() {
        AsOfAttributeType t = new AsOfAttributeType();
        t.setName("processDate");
        assertEquals("processDate", t.getName());
        t.setFromColumnName("FROM_Z");
        assertEquals("FROM_Z", t.getFromColumnName());
        t.setToColumnName("THRU_Z");
        assertEquals("THRU_Z", t.getToColumnName());
        t.setInfinity("9999-12-01");
        assertEquals("9999-12-01", t.getInfinity());
        t.setIsProcessingDate(true);
        assertTrue(t.isIsProcessingDate());
    }

    @Test public void testComputedAttributeType() {
        ComputedAttributeType t = new ComputedAttributeType();
        t.setName("computed");
        assertEquals("computed", t.getName());
        t.setExpression("id + 1");
        assertEquals("id + 1", t.getExpression());
    }

    @Test public void testRelationshipType() {
        RelationshipType t = new RelationshipType();
        t.setName("orders");
        assertEquals("orders", t.getName());
        t.setRelatedObject("Order");
        assertEquals("Order", t.getRelatedObject());
        t.setCardinality("one-to-many");
        assertEquals("one-to-many", t.getCardinality());
    }

    @Test public void testSourceAttributeType() {
        SourceAttributeType t = new SourceAttributeType();
        t.setName("sourceId");
        assertEquals("sourceId", t.getName());
    }

    @Test public void testEmbeddedValueType() {
        EmbeddedValueType t = new EmbeddedValueType();
        t.setName("address");
        assertEquals("address", t.getName());
    }

    @Test public void testPropertyType() {
        PropertyType t = new PropertyType();
        t.setKey("cacheType");
        assertEquals("cacheType", t.getKey());
        t.setValue("partial");
        assertEquals("partial", t.getValue());
    }

    @Test public void testMithraInterfaceType() {
        MithraInterfaceType t = new MithraInterfaceType();
        t.setName("Audited");
        assertEquals("Audited", t.getName());
    }

    @Test public void testMithraObjectType() {
        MithraObjectType t = new MithraObjectType();
        t.setClassName("TestObject");
        assertEquals("TestObject", t.getClassName());
        t.setDefaultTable("TEST_TABLE");
        assertEquals("TEST_TABLE", t.getDefaultTable());
        t.setObjectType("transactional");
        assertEquals("transactional", t.getObjectType());
    }

    @Test public void testCardinalityType() {
        CardinalityType t = new CardinalityType();
        assertNotNull(t);
    }

    @Test public void testTimePrecisionType() {
        TimePrecisionType t = new TimePrecisionType();
        assertNotNull(t);
    }

    @Test public void testIdentity() {
        Identity t = new Identity();
        assertNotNull(t);
    }

    @Test public void testPrimaryKeyGeneratorStrategyType() {
        PrimaryKeyGeneratorStrategyType t = new PrimaryKeyGeneratorStrategyType();
        assertNotNull(t);
    }

    @Test public void testObjectType() {
        ObjectType t = new ObjectType();
        assertNotNull(t);
    }

    @Test public void testAttributeInterfaceType() {
        AttributeInterfaceType t = new AttributeInterfaceType();
        t.setName("id");
        assertEquals("id", t.getName());
    }

    @Test public void testRelationshipInterfaceType() {
        RelationshipInterfaceType t = new RelationshipInterfaceType();
        t.setName("orders");
        assertEquals("orders", t.getName());
    }

    @Test public void testAsOfAttributeInterfaceType() {
        AsOfAttributeInterfaceType t = new AsOfAttributeInterfaceType();
        t.setName("businessDate");
        assertEquals("businessDate", t.getName());
    }

    @Test public void testSourceAttributeInterfaceType() {
        SourceAttributeInterfaceType t = new SourceAttributeInterfaceType();
        t.setName("sourceId");
        assertEquals("sourceId", t.getName());
    }

    @Test public void testMithraType() {
        MithraType t = new MithraType();
        assertNotNull(t);
    }

    @Test public void testMithraPureObjectType() {
        MithraPureObjectType t = new MithraPureObjectType();
        t.setClassName("PureObject");
        assertEquals("PureObject", t.getClassName());
    }

    @Test public void testMithraCommonObjectType() {
        MithraCommonObjectType t = new MithraCommonObjectType();
        assertNotNull(t);
    }

    @Test public void testMithraBaseObjectType() {
        MithraBaseObjectType t = new MithraBaseObjectType();
        t.setClassName("BaseObject");
        assertEquals("BaseObject", t.getClassName());
    }

    @Test public void testMithraEmbeddedValueObjectType() {
        MithraEmbeddedValueObjectType t = new MithraEmbeddedValueObjectType();
        t.setClassName("Address");
        assertEquals("Address", t.getClassName());
    }

    @Test public void testMithraInterfaceResourceType() {
        MithraInterfaceResourceType t = new MithraInterfaceResourceType();
        assertNotNull(t);
    }

    @Test public void testSuperClassAttributeType() {
        SuperClassAttributeType t = new SuperClassAttributeType();
        t.setName("id");
        assertEquals("id", t.getName());
    }

    @Test public void testEmbeddedValueMappingType() {
        EmbeddedValueMappingType t = new EmbeddedValueMappingType();
        assertNotNull(t);
    }

    @Test public void testAttributeEmbeddedValueType() {
        AttributeEmbeddedValueType t = new AttributeEmbeddedValueType();
        t.setName("city");
        assertEquals("city", t.getName());
    }

    @Test public void testNestedEmbeddedValueType() {
        NestedEmbeddedValueType t = new NestedEmbeddedValueType();
        t.setName("nested");
        assertEquals("nested", t.getName());
    }

    @Test public void testMithraPureObject() {
        MithraPureObject t = new MithraPureObject();
        assertNotNull(t);
    }

    @Test public void testMithraObject() {
        MithraObject t = new MithraObject();
        assertNotNull(t);
    }
}
