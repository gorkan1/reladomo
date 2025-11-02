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

package com.gs.fw.common.mithra.generator.util;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class StringUtilityTest
{
    @Test
    public void testFirstLetterToLower()
    {
        assertEquals("hello", StringUtility.firstLetterToLower("Hello"));
        assertEquals("hello", StringUtility.firstLetterToLower("hello"));
        assertEquals("hELLO", StringUtility.firstLetterToLower("HELLO"));
        assertEquals("a", StringUtility.firstLetterToLower("A"));
    }

    @Test
    public void testFirstLetterToUpper()
    {
        assertEquals("Hello", StringUtility.firstLetterToUpper("hello"));
        assertEquals("Hello", StringUtility.firstLetterToUpper("Hello"));
        assertEquals("HELLO", StringUtility.firstLetterToUpper("hELLO"));
        assertEquals("A", StringUtility.firstLetterToUpper("a"));
    }

    @Test
    public void testRemoveLastCharacter()
    {
        assertEquals("Hell", StringUtility.removeLastCharacter("Hello"));
        assertEquals("", StringUtility.removeLastCharacter("A"));
        assertEquals("", StringUtility.removeLastCharacter(""));
        assertEquals("Test", StringUtility.removeLastCharacter("Tests"));
    }

    @Test
    public void testRemoveLastCharacter_Multiple()
    {
        assertEquals("Hel", StringUtility.removeLastCharacter("Hello", 2));
        assertEquals("", StringUtility.removeLastCharacter("Hi", 2));
        assertEquals("", StringUtility.removeLastCharacter("A", 5));
        assertEquals("Test", StringUtility.removeLastCharacter("Testing", 3));
        assertEquals("", StringUtility.removeLastCharacter("Test", 4));
    }

    @Test
    public void testTrimPackage()
    {
        assertEquals("ClassName", StringUtility.trimPackage("com.example.ClassName"));
        assertEquals("ClassName", StringUtility.trimPackage("ClassName"));
        assertEquals("Inner", StringUtility.trimPackage("com.example.Outer.Inner"));
        assertEquals("Test", StringUtility.trimPackage("a.b.c.d.e.Test"));
    }

    @Test
    public void testReplaceStr()
    {
        assertEquals("Hello World", StringUtility.replaceStr("Hello_World", "_", " "));
        assertEquals("aaabbbccc", StringUtility.replaceStr("xxxbbbccc", "xxx", "aaa"));
        assertEquals("abc", StringUtility.replaceStr("abc", "xyz", "123"));
        assertEquals("Hello World", StringUtility.replaceStr("Hello World", "", "x"));
        assertEquals("", StringUtility.replaceStr("xxxx", "x", ""));
        assertEquals("aaa_aaa_aaa", StringUtility.replaceStr("xxx_xxx_xxx", "xxx", "aaa"));
    }

    @Test
    public void testReplaceStr_MultipleOccurrences()
    {
        assertEquals("This and that and those", StringUtility.replaceStr("This & that & those", "&", "and"));
        assertEquals("---", StringUtility.replaceStr("xxx", "x", "-"));
        assertEquals("ababab", StringUtility.replaceStr("aaa", "a", "ab"));
    }

    @Test
    public void testEqualsIgnoreCaseFirstLetter_True()
    {
        assertTrue(StringUtility.equalsIgnoreCaseFirstLetter("hello", "Hello"));
        assertTrue(StringUtility.equalsIgnoreCaseFirstLetter("Hello", "hello"));
        assertTrue(StringUtility.equalsIgnoreCaseFirstLetter("TestClass", "testClass"));
        assertTrue(StringUtility.equalsIgnoreCaseFirstLetter("ABC", "abc"));
        assertTrue(StringUtility.equalsIgnoreCaseFirstLetter("a", "A"));
    }

    @Test
    public void testEqualsIgnoreCaseFirstLetter_False()
    {
        assertFalse(StringUtility.equalsIgnoreCaseFirstLetter("hello", "world"));
        assertFalse(StringUtility.equalsIgnoreCaseFirstLetter("hello", "Hello!"));
        assertFalse(StringUtility.equalsIgnoreCaseFirstLetter("Test", "Tests"));
        assertFalse(StringUtility.equalsIgnoreCaseFirstLetter("abc", "abd"));
        assertFalse(StringUtility.equalsIgnoreCaseFirstLetter("TestClass", "testclass"));
    }

    @Test
    public void testVectorContainsIgnoreCaseFirstLetter()
    {
        Vector<String> vector = new Vector<>();
        vector.add("Hello");
        vector.add("World");
        vector.add("Test");

        assertTrue(StringUtility.vectorContainsIgnoreCaseFirstLetter(vector, "hello"));
        assertTrue(StringUtility.vectorContainsIgnoreCaseFirstLetter(vector, "Hello"));
        assertTrue(StringUtility.vectorContainsIgnoreCaseFirstLetter(vector, "test"));
        assertFalse(StringUtility.vectorContainsIgnoreCaseFirstLetter(vector, "NotThere"));
        assertFalse(StringUtility.vectorContainsIgnoreCaseFirstLetter(vector, "hello world"));
    }

    @Test
    public void testHashMapContainsKeyIgnoreCaseFirstLetter()
    {
        HashMap<String, String> map = new HashMap<>();
        map.put("Hello", "value1");
        map.put("World", "value2");
        map.put("Test", "value3");

        assertTrue(StringUtility.hashMapContainsKeyIgnoreCaseFirstLetter(map, "hello"));
        assertTrue(StringUtility.hashMapContainsKeyIgnoreCaseFirstLetter(map, "Hello"));
        assertTrue(StringUtility.hashMapContainsKeyIgnoreCaseFirstLetter(map, "test"));
        assertFalse(StringUtility.hashMapContainsKeyIgnoreCaseFirstLetter(map, "NotThere"));
    }

    @Test
    public void testListContainsIgnoreCaseFirstLetter()
    {
        List<String> list = new ArrayList<>();
        list.add("Hello");
        list.add("World");
        list.add("Test");

        assertTrue(StringUtility.listContainsIgnoreCaseFirstLetter(list, "hello"));
        assertTrue(StringUtility.listContainsIgnoreCaseFirstLetter(list, "Hello"));
        assertTrue(StringUtility.listContainsIgnoreCaseFirstLetter(list, "test"));
        assertFalse(StringUtility.listContainsIgnoreCaseFirstLetter(list, "NotThere"));
    }

    @Test
    public void testArrayListContainsIgnoreCaseFirstLetter()
    {
        ArrayList<String> list = new ArrayList<>();
        list.add("Hello");
        list.add("World");
        list.add("Test");

        assertTrue(StringUtility.arrayListContainsIgnoreCaseFirstLetter(list, "hello"));
        assertTrue(StringUtility.arrayListContainsIgnoreCaseFirstLetter(list, "Hello"));
        assertTrue(StringUtility.arrayListContainsIgnoreCaseFirstLetter(list, "test"));
        assertFalse(StringUtility.arrayListContainsIgnoreCaseFirstLetter(list, "NotThere"));
    }

    @Test
    public void testToCamelCaseIgnoringLastChar_LowerFirst()
    {
        assertEquals("helloWorld", StringUtility.toCamelCaseIgnoringLastChar("hello_world", "_", false));
        assertEquals("myTestClass", StringUtility.toCamelCaseIgnoringLastChar("my_test_class", "_", false));
        assertEquals("userId", StringUtility.toCamelCaseIgnoringLastChar("user_id", "_", false));
        assertEquals("simpleTest", StringUtility.toCamelCaseIgnoringLastChar("simple_test", "_", false));
    }

    @Test
    public void testToCamelCaseIgnoringLastChar_UpperFirst()
    {
        assertEquals("HelloWorld", StringUtility.toCamelCaseIgnoringLastChar("hello_world", "_", true));
        assertEquals("MyTestClass", StringUtility.toCamelCaseIgnoringLastChar("my_test_class", "_", true));
        assertEquals("UserId", StringUtility.toCamelCaseIgnoringLastChar("user_id", "_", true));
    }

    @Test
    public void testToCamelCaseIgnoringLastChar_IgnoreTrailingUnderscore()
    {
        assertEquals("userId", StringUtility.toCamelCaseIgnoringLastChar("user_id_", "_", false));
        assertEquals("userI", StringUtility.toCamelCaseIgnoringLastChar("user_i_", "_", false));
    }

    @Test
    public void testToCamelCaseIgnoringLastChar_PreserveTrailingDigit()
    {
        assertEquals("userId1", StringUtility.toCamelCaseIgnoringLastChar("user_id_1", "_", false));
        assertEquals("test123", StringUtility.toCamelCaseIgnoringLastChar("test_123", "_", false));
    }

    @Test
    public void testToCamelCaseIgnoringLastChar_EmptyDelimiter()
    {
        assertEquals("helloworld", StringUtility.toCamelCaseIgnoringLastChar("helloworld", "", false));
        assertEquals("Helloworld", StringUtility.toCamelCaseIgnoringLastChar("helloworld", "", true));
    }

    @Test
    public void testToCamelCasePresevingExisting()
    {
        assertEquals("HelloWorld", StringUtility.toCamelCasePresevingExisting("HELLOWORLD"));
        assertEquals("Helloworld", StringUtility.toCamelCasePresevingExisting("HELLO WORLD"));
        assertEquals("TestClass", StringUtility.toCamelCasePresevingExisting("TESTCLASS"));
        assertEquals("UserId", StringUtility.toCamelCasePresevingExisting("USERId"));
        assertEquals("UserId", StringUtility.toCamelCasePresevingExisting("USERID"));
    }

    @Test
    public void testEnglishPluralize()
    {
        assertEquals("tests", StringUtility.englishPluralize("test"));
        assertEquals("classes", StringUtility.englishPluralize("class"));
        assertEquals("boxes", StringUtility.englishPluralize("box"));
        assertEquals("cities", StringUtility.englishPluralize("city"));
        assertEquals("countries", StringUtility.englishPluralize("country"));
        assertEquals("days", StringUtility.englishPluralize("day"));
        assertEquals("items", StringUtility.englishPluralize("item"));
    }

    @Test
    public void testMakeIntoJavaIdentifier()
    {
        assertEquals("HelloWorld", StringUtility.makeIntoJavaIdentifier("Hello World"));
        assertEquals("HelloWorld", StringUtility.makeIntoJavaIdentifier("Hello_World"));
        assertEquals("HelloWorld", StringUtility.makeIntoJavaIdentifier("Hello$World"));
        assertEquals("TestClass", StringUtility.makeIntoJavaIdentifier("Test_Class"));
        assertEquals("MyVariable", StringUtility.makeIntoJavaIdentifier("My Variable"));
        assertEquals("AbcDef", StringUtility.makeIntoJavaIdentifier("ABC_DEF"));
    }

    @Test
    public void testMakeIntoJavaIdentifier_Complex()
    {
        assertEquals("HelloWorldTest", StringUtility.makeIntoJavaIdentifier("Hello World_Test"));
        assertEquals("MyVarName", StringUtility.makeIntoJavaIdentifier("My$Var_Name"));
        assertEquals("TestIdentifier", StringUtility.makeIntoJavaIdentifier("Test Identifier"));
    }
}
