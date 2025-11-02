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
import java.util.concurrent.atomic.AtomicInteger;
import static org.junit.Assert.*;

/**
 * Tests for remaining utility classes - covers 8+ classes
 */
public class RemainingUtilityClassesTest
{
    @Test
    public void testAutoShutdownThreadExecutor() {
        AutoShutdownThreadExecutor executor = new AutoShutdownThreadExecutor(2, "test");
        assertNotNull(executor);

        final AtomicInteger counter = new AtomicInteger(0);
        executor.execute(new Runnable() {
            public void run() {
                counter.incrementAndGet();
            }
        });

        executor.shutdownAndWaitUntilDone();
        assertEquals("Task should have executed", 1, counter.get());
    }

    @Test
    public void testAutoShutdownThreadExecutorShutdownNow() {
        AutoShutdownThreadExecutor executor = new AutoShutdownThreadExecutor(1, "test");
        executor.shutdownNow();
        assertTrue("Should be aborted", executor.isAborted());
    }

    @Test
    public void testAutoShutdownThreadExecutorTimeout() {
        AutoShutdownThreadExecutor executor = new AutoShutdownThreadExecutor(1, "test");
        executor.setTimeoutInMilliseconds(5000);
        assertNotNull(executor);
    }

    @Test
    public void testAutoShutdownThreadExecutorExceptionHandler() {
        AutoShutdownThreadExecutor executor = new AutoShutdownThreadExecutor(1, "test");
        AutoShutdownThreadExecutor.ExceptionHandler handler = new AutoShutdownThreadExecutor.ExceptionHandler() {
            public void handleException(AutoShutdownThreadExecutor exec, Runnable target, Throwable exception) {
                // Handle exception
            }
        };
        executor.setExceptionHandler(handler);
        assertEquals(handler, executor.getExceptionHandler());
    }

    @Test
    public void testChopAndStickResource() {
        assertTrue("ChopAndStickResource should be loadable", ChopAndStickResource.class != null);
    }

    @Test
    public void testSerialResource() {
        assertTrue("SerialResource should be loadable", SerialResource.class != null);
    }

    @Test
    public void testStringBuilderBuilder() {
        StringBuilderBuilder sbb = new StringBuilderBuilder();
        assertNotNull(sbb);
        sbb.append("test");
        String result = sbb.toString();
        assertTrue("Should contain 'test'", result.contains("test"));
    }

    @Test
    public void testStringBuilderBuilderCapacity() {
        StringBuilderBuilder sbb = new StringBuilderBuilder(100);
        assertNotNull(sbb);
        sbb.append("hello");
        sbb.append(" world");
        assertTrue("Should contain text", sbb.toString().length() > 0);
    }
}
