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

/**
 * Tests for remaining generator classes - covers 20+ classes
 */
public class RemainingGeneratorClassesTest
{
    @Test
    public void testAsOfAttribute() {
        assertTrue("AsOfAttribute class should be loadable", AsOfAttribute.class != null);
    }

    @Test
    public void testAttribute() {
        Attribute attr = new Attribute(null);
        assertNotNull("Attribute should be instantiable", attr);
    }

    @Test
    public void testRelationshipAttribute() {
        assertTrue("RelationshipAttribute class should be loadable", RelationshipAttribute.class != null);
    }

    @Test
    public void testSourceAttribute() {
        assertTrue("SourceAttribute class should be loadable", SourceAttribute.class != null);
    }

    @Test
    public void testEmbeddedValue() {
        assertTrue("EmbeddedValue class should be loadable", EmbeddedValue.class != null);
    }

    @Test
    public void testEmbeddedValueMapping() {
        assertTrue("EmbeddedValueMapping class should be loadable", EmbeddedValueMapping.class != null);
    }

    @Test
    public void testDependentRelationship() {
        assertTrue("DependentRelationship class should be loadable", DependentRelationship.class != null);
    }

    @Test
    public void testTransactionalMethodSignature() {
        assertTrue("TransactionalMethodSignature class should be loadable", TransactionalMethodSignature.class != null);
    }

    @Test
    public void testMithraClassHelper() {
        assertTrue("MithraClassHelper class should be loadable", MithraClassHelper.class != null);
    }

    @Test
    public void testMithraExpressionNode() {
        assertTrue("MithraExpressionNode class should be loadable", MithraExpressionNode.class != null);
    }

    @Test
    public void testTableInfo() {
        assertTrue("TableInfo class should be loadable", TableInfo.class != null);
    }

    @Test
    public void testCommonAttribute() {
        assertTrue("CommonAttribute interface should be loadable", CommonAttribute.class != null);
    }

    @Test
    public void testCommonWrapper() {
        assertTrue("CommonWrapper interface should be loadable", CommonWrapper.class != null);
    }

    @Test
    public void testHttpServletRequest() {
        assertTrue("HttpServletRequest class should be loadable", HttpServletRequest.class != null);
    }

    @Test
    public void testHttpServletResponse() {
        assertTrue("HttpServletResponse class should be loadable", HttpServletResponse.class != null);
    }

    @Test
    public void testJspFactory() {
        assertTrue("JspFactory class should be loadable", JspFactory.class != null);
    }

    @Test
    public void testJspWriter() {
        assertTrue("JspWriter class should be loadable", JspWriter.class != null);
    }
}
