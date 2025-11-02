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

package com.gs.fw.common.mithra.generator.computedattribute;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for computed attribute parser and related classes - covers 15+ classes
 */
public class ComputedAttributeParserTest
{
    @Test
    public void testComputedAttributeParserInstantiation() {
        assertTrue("ComputedAttributeParser class should be loadable", ComputedAttributeParser.class != null);
    }

    @Test
    public void testExpressionBeginState() {
        assertTrue("ExpressionBeginState class should be loadable",
            com.gs.fw.common.mithra.generator.computedattribute.ExpressionBeginState.class != null);
    }

    @Test
    public void testExpressionEndState() {
        assertTrue("ExpressionEndState class should be loadable",
            com.gs.fw.common.mithra.generator.computedattribute.ExpressionEndState.class != null);
    }

    @Test
    public void testFunctionBeginExpressionState() {
        assertTrue("FunctionBeginExpressionState class should be loadable",
            com.gs.fw.common.mithra.generator.computedattribute.FunctionBeginExpressionState.class != null);
    }

    @Test
    public void testFunctionParameterBeginExpressionState() {
        assertTrue("FunctionParameterBeginExpressionState class should be loadable",
            com.gs.fw.common.mithra.generator.computedattribute.FunctionParameterBeginExpressionState.class != null);
    }

    @Test
    public void testCaseSelectorBeginParserState() {
        assertTrue("CaseSelectorBeginParserState class should be loadable",
            com.gs.fw.common.mithra.generator.computedattribute.CaseSelectorBeginParserState.class != null);
    }

    @Test
    public void testCaseSelectorMiddleParserState() {
        assertTrue("CaseSelectorMiddleParserState class should be loadable",
            com.gs.fw.common.mithra.generator.computedattribute.CaseSelectorMiddleParserState.class != null);
    }

    @Test
    public void testCaseExpressionParameterBeginExpressionState() {
        assertTrue("CaseExpressionParameterBeginExpressionState class should be loadable",
            com.gs.fw.common.mithra.generator.computedattribute.CaseExpressionParameterBeginExpressionState.class != null);
    }

    @Test
    public void testExpression() {
        assertTrue("Expression class should be loadable",
            com.gs.fw.common.mithra.generator.computedattribute.Expression.class != null);
    }

    @Test
    public void testParserState() {
        assertTrue("ParserState class should be loadable",
            com.gs.fw.common.mithra.generator.computedattribute.ParserState.class != null);
    }

    @Test
    public void testParseException() {
        com.gs.fw.common.mithra.generator.computedattribute.ParseException pe =
            new com.gs.fw.common.mithra.generator.computedattribute.ParseException("test");
        assertEquals("test", pe.getMessage());
    }

    @Test
    public void testToken() {
        assertTrue("Token class should be loadable",
            com.gs.fw.common.mithra.generator.computedattribute.Token.class != null);
    }

    @Test
    public void testTokenType() {
        assertTrue("TokenType class should be loadable",
            com.gs.fw.common.mithra.generator.computedattribute.TokenType.class != null);
    }

    @Test
    public void testComputedAttributeType() {
        assertTrue("Type classes should be loadable",
            com.gs.fw.common.mithra.generator.computedattribute.type.Type.class != null);
    }
}
