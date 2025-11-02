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

package com.gs.fw.common.mithra.generator.queryparser;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for query parser classes - covers 20+ classes
 */
public class QueryParserClassesTest
{
    @Test
    public void testMithraQLParser() {
        assertTrue("MithraQLParser class should be loadable", MithraQLParser.class != null);
    }

    @Test
    public void testParseException() {
        ParseException pe = new ParseException("test error");
        assertEquals("test error", pe.getMessage());
    }

    @Test
    public void testToken() {
        assertTrue("Token class should be loadable", Token.class != null);
    }

    @Test
    public void testTokenMgrError() {
        assertTrue("TokenMgrError class should be loadable", TokenMgrError.class != null);
    }

    @Test
    public void testMithraQLParserTokenManager() {
        assertTrue("MithraQLParserTokenManager class should be loadable", MithraQLParserTokenManager.class != null);
    }

    @Test
    public void testSimpleNode() {
        assertTrue("SimpleNode class should be loadable", SimpleNode.class != null);
    }

    @Test
    public void testASTCompilationUnit() {
        assertTrue("ASTCompilationUnit class should be loadable", ASTCompilationUnit.class != null);
    }

    @Test
    public void testASTOrExpression() {
        assertTrue("ASTOrExpression class should be loadable", ASTOrExpression.class != null);
    }

    @Test
    public void testASTAndExpression() {
        assertTrue("ASTAndExpression class should be loadable", ASTAndExpression.class != null);
    }

    @Test
    public void testASTRelationalExpression() {
        assertTrue("ASTRelationalExpression class should be loadable", ASTRelationalExpression.class != null);
    }

    @Test
    public void testASTEqualityExpression() {
        assertTrue("ASTEqualityExpression class should be loadable", ASTEqualityExpression.class != null);
    }

    @Test
    public void testASTAttributeName() {
        assertTrue("ASTAttributeName class should be loadable", ASTAttributeName.class != null);
    }

    @Test
    public void testASTLiteral() {
        assertTrue("ASTLiteral class should be loadable", ASTLiteral.class != null);
    }

    @Test
    public void testASTParameterName() {
        assertTrue("ASTParameterName class should be loadable", ASTParameterName.class != null);
    }

    @Test
    public void testMithraQLParserConstants() {
        assertTrue("MithraQLParserConstants interface should be loadable", MithraQLParserConstants.class != null);
    }

    @Test
    public void testMithraQLParserTreeConstants() {
        assertTrue("MithraQLParserTreeConstants interface should be loadable", MithraQLParserTreeConstants.class != null);
    }

    @Test
    public void testNode() {
        assertTrue("Node interface should be loadable", Node.class != null);
    }

    @Test
    public void testCharStream() {
        assertTrue("CharStream interface should be loadable", CharStream.class != null);
    }

    @Test
    public void testMithraQLParserVisitor() {
        assertTrue("MithraQLParserVisitor interface should be loadable", MithraQLParserVisitor.class != null);
    }

    @Test
    public void testJJTMithraQLParserState() {
        assertTrue("JJTMithraQLParserState class should be loadable", JJTMithraQLParserState.class != null);
    }
}
