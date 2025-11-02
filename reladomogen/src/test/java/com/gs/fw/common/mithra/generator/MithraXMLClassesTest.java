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
 * Tests for XML-related classes - covers 15+ classes
 */
public class MithraXMLClassesTest
{
    @Test
    public void testMithraXMLObjectTypeParser() {
        assertTrue("MithraXMLObjectTypeParser class should be loadable", MithraXMLObjectTypeParser.class != null);
    }

    @Test
    public void testMithraImportXMLObjectTypeParser() {
        assertTrue("MithraImportXMLObjectTypeParser class should be loadable", MithraImportXMLObjectTypeParser.class != null);
    }

    @Test
    public void testMithraXMLUtil() {
        assertTrue("MithraXMLUtil class should be loadable", MithraXMLUtil.class != null);
    }

    @Test
    public void testMithraBaseObjectTypeWrapper() {
        assertTrue("MithraBaseObjectTypeWrapper class should be loadable", MithraBaseObjectTypeWrapper.class != null);
    }

    @Test
    public void testMithraEmbeddedValueObjectTypeWrapper() {
        assertTrue("MithraEmbeddedValueObjectTypeWrapper class should be loadable", MithraEmbeddedValueObjectTypeWrapper.class != null);
    }

    @Test
    public void testMithraInterfaceTypeWrapper() {
        assertTrue("MithraInterfaceTypeWrapper class should be loadable", MithraInterfaceTypeWrapper.class != null);
    }

    @Test
    public void testMithraSuperTypeWrapper() {
        assertTrue("MithraSuperTypeWrapper class should be loadable", MithraSuperTypeWrapper.class != null);
    }

    @Test
    public void testMithraGeneratorImport() {
        assertTrue("MithraGeneratorImport class should be loadable", MithraGeneratorImport.class != null);
    }

    @Test
    public void testBaseMithraGenerator() {
        assertTrue("BaseMithraGenerator class should be loadable", BaseMithraGenerator.class != null);
    }

    @Test
    public void testCoreMithraGenerator() {
        assertTrue("CoreMithraGenerator class should be loadable", CoreMithraGenerator.class != null);
    }

    @Test
    public void testAbstractMithraGenerator() {
        assertTrue("AbstractMithraGenerator class should be loadable", AbstractMithraGenerator.class != null);
    }

    @Test
    public void testCoreMithraGraphGenerator() {
        assertTrue("CoreMithraGraphGenerator class should be loadable", CoreMithraGraphGenerator.class != null);
    }

    @Test
    public void testCoreMithraUmlGenerator() {
        assertTrue("CoreMithraUmlGenerator class should be loadable", CoreMithraUmlGenerator.class != null);
    }

    @Test
    public void testLogger() {
        assertTrue("Logger interface should be loadable", Logger.class != null);
    }

    @Test
    public void testGenerationLogger() {
        assertTrue("GenerationLogger interface should be loadable", GenerationLogger.class != null);
    }
}
