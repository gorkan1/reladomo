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

public class SkipPageExceptionTest
{
    @Test
    public void testDefaultConstructor()
    {
        SkipPageException exception = new SkipPageException();
        assertNotNull(exception);
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    public void testIsCheckedException()
    {
        SkipPageException exception = new SkipPageException();
        assertTrue("Should be an Exception", exception instanceof Exception);
        assertFalse("Should not be a RuntimeException", exception instanceof RuntimeException);
    }

    @Test
    public void testThrowAndCatch() throws Exception
    {
        try
        {
            throw new SkipPageException();
        }
        catch (SkipPageException e)
        {
            assertNotNull(e);
        }
    }
}
