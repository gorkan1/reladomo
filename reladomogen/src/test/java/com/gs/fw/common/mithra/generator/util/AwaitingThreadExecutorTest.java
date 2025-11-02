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
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class AwaitingThreadExecutorTest
{
    @Test
    public void testBasicExecution() throws InterruptedException
    {
        AwaitingThreadExecutor executor = new AwaitingThreadExecutor(2, "test");
        final AtomicInteger counter = new AtomicInteger(0);

        executor.submit(new Runnable() {
            public void run() {
                counter.incrementAndGet();
            }
        });

        executor.shutdown();
        executor.waitUntilDone();

        assertEquals("Task should have executed", 1, counter.get());
    }

    @Test
    public void testMultipleTasks() throws InterruptedException
    {
        AwaitingThreadExecutor executor = new AwaitingThreadExecutor(4, "test-multiple");
        final AtomicInteger counter = new AtomicInteger(0);

        int taskCount = 10;
        for (int i = 0; i < taskCount; i++)
        {
            executor.submit(new Runnable() {
                public void run() {
                    counter.incrementAndGet();
                }
            });
        }

        executor.shutdown();
        executor.waitUntilDone();

        assertEquals("All tasks should have executed", taskCount, counter.get());
    }

    @Test
    public void testWaitUntilDone() throws InterruptedException
    {
        AwaitingThreadExecutor executor = new AwaitingThreadExecutor(2, "test-wait");
        final AtomicInteger counter = new AtomicInteger(0);
        final CountDownLatch latch = new CountDownLatch(1);

        executor.submit(new Runnable() {
            public void run() {
                try {
                    latch.await(5, TimeUnit.SECONDS);
                    counter.incrementAndGet();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // Release the latch after a short delay
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(100);
                    latch.countDown();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();

        executor.shutdown();
        executor.waitUntilDone();

        assertEquals("Task should complete before waitUntilDone returns", 1, counter.get());
    }

    @Test
    public void testConcurrentExecution() throws InterruptedException
    {
        AwaitingThreadExecutor executor = new AwaitingThreadExecutor(4, "test-concurrent");
        final AtomicInteger counter = new AtomicInteger(0);
        final int taskCount = 20;

        for (int i = 0; i < taskCount; i++)
        {
            final int taskNum = i;
            executor.submit(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(10); // Simulate work
                        counter.incrementAndGet();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
        }

        executor.shutdown();
        executor.waitUntilDone();

        assertEquals("All concurrent tasks should complete", taskCount, counter.get());
    }

    @Test
    public void testExceptionHandler() throws InterruptedException
    {
        AwaitingThreadExecutor executor = new AwaitingThreadExecutor(2, "test-exception");
        final AtomicInteger handledExceptions = new AtomicInteger(0);

        executor.setExceptionHandler(new AutoShutdownThreadExecutor.ExceptionHandler() {
            public void handleException(AutoShutdownThreadExecutor exec, Runnable target, Throwable exception) {
                handledExceptions.incrementAndGet();
            }
        });

        executor.submit(new Runnable() {
            public void run() {
                throw new RuntimeException("Test exception");
            }
        });

        executor.shutdown();
        // Give some time for exception handling
        Thread.sleep(500);

        assertTrue("Exception should have been handled", handledExceptions.get() > 0);
    }

    @Test
    public void testShutdownBeforeWait() throws InterruptedException
    {
        AwaitingThreadExecutor executor = new AwaitingThreadExecutor(2, "test-shutdown");
        final AtomicInteger counter = new AtomicInteger(0);

        for (int i = 0; i < 5; i++)
        {
            executor.submit(new Runnable() {
                public void run() {
                    counter.incrementAndGet();
                }
            });
        }

        executor.shutdown();
        executor.waitUntilDone();

        assertEquals("All tasks should complete after shutdown", 5, counter.get());
    }

    @Test
    public void testEmptyExecution() throws InterruptedException
    {
        AwaitingThreadExecutor executor = new AwaitingThreadExecutor(2, "test-empty");

        executor.shutdown();
        executor.waitUntilDone();

        // Should complete without hanging
        assertTrue("Empty executor should complete", true);
    }

    @Test(timeout = 5000)
    public void testWaitDoesNotHang() throws InterruptedException
    {
        AwaitingThreadExecutor executor = new AwaitingThreadExecutor(2, "test-no-hang");

        executor.submit(new Runnable() {
            public void run() {
                // Quick task
            }
        });

        executor.shutdown();
        executor.waitUntilDone();

        // If we get here without timeout, test passes
        assertTrue(true);
    }
}
