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

import java.io.*;
import java.util.zip.CRC32;

import static org.junit.Assert.*;

public class FullFileBufferTest
{
    @Test
    public void testBufferSmallFile() throws IOException
    {
        byte[] testData = "Hello, World!".getBytes();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(testData);

        FullFileBuffer buffer = new FullFileBuffer();
        buffer.bufferFile(inputStream, testData.length);

        InputStream bufferedStream = buffer.getBufferedInputStream();
        byte[] result = readAllBytes(bufferedStream);

        assertArrayEquals("Buffered content should match original", testData, result);
    }

    @Test
    public void testBufferLargeFile() throws IOException
    {
        // Create a large file (10KB) that exceeds initial buffer size of 8096 bytes
        byte[] testData = new byte[10240];
        for (int i = 0; i < testData.length; i++)
        {
            testData[i] = (byte) (i % 256);
        }

        ByteArrayInputStream inputStream = new ByteArrayInputStream(testData);

        FullFileBuffer buffer = new FullFileBuffer();
        buffer.bufferFile(inputStream, testData.length);

        InputStream bufferedStream = buffer.getBufferedInputStream();
        byte[] result = readAllBytes(bufferedStream);

        assertArrayEquals("Large file should be buffered correctly", testData, result);
    }

    @Test
    public void testBufferEmptyFile() throws IOException
    {
        byte[] testData = new byte[0];
        ByteArrayInputStream inputStream = new ByteArrayInputStream(testData);

        FullFileBuffer buffer = new FullFileBuffer();
        buffer.bufferFile(inputStream, 0);

        InputStream bufferedStream = buffer.getBufferedInputStream();
        byte[] result = readAllBytes(bufferedStream);

        assertEquals("Empty file should produce empty buffer", 0, result.length);
    }

    @Test
    public void testMultipleBufferCalls() throws IOException
    {
        FullFileBuffer buffer = new FullFileBuffer();

        // First buffer
        byte[] firstData = "First content".getBytes();
        buffer.bufferFile(new ByteArrayInputStream(firstData), firstData.length);

        InputStream firstStream = buffer.getBufferedInputStream();
        byte[] firstResult = readAllBytes(firstStream);
        assertArrayEquals("First buffer should match", firstData, firstResult);

        // Second buffer (different size)
        byte[] secondData = "Second content is longer".getBytes();
        buffer.bufferFile(new ByteArrayInputStream(secondData), secondData.length);

        InputStream secondStream = buffer.getBufferedInputStream();
        byte[] secondResult = readAllBytes(secondStream);
        assertArrayEquals("Second buffer should match", secondData, secondResult);
    }

    @Test
    public void testUpdateCrc() throws IOException
    {
        byte[] testData = "Test data for CRC".getBytes();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(testData);

        FullFileBuffer buffer = new FullFileBuffer();
        buffer.bufferFile(inputStream, testData.length);

        CRC32 crc = new CRC32();
        buffer.updateCrc(crc);

        // Calculate expected CRC
        CRC32 expectedCrc = new CRC32();
        expectedCrc.update(testData);

        assertEquals("CRC should match", expectedCrc.getValue(), crc.getValue());
    }

    @Test
    public void testUpdateCrcMultipleTimes() throws IOException
    {
        byte[] testData = "Test data".getBytes();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(testData);

        FullFileBuffer buffer = new FullFileBuffer();
        buffer.bufferFile(inputStream, testData.length);

        CRC32 crc = new CRC32();
        buffer.updateCrc(crc);
        buffer.updateCrc(crc);

        // CRC should accumulate
        CRC32 expectedCrc = new CRC32();
        expectedCrc.update(testData);
        expectedCrc.update(testData);

        assertEquals("CRC should accumulate correctly", expectedCrc.getValue(), crc.getValue());
    }

    @Test(expected = RuntimeException.class)
    public void testBufferFile_InsufficientData() throws IOException
    {
        byte[] testData = "Short".getBytes();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(testData);

        FullFileBuffer buffer = new FullFileBuffer();
        // Request more bytes than available
        buffer.bufferFile(inputStream, testData.length + 100);
    }

    @Test
    public void testBufferFile_ExactSize() throws IOException
    {
        byte[] testData = new byte[8096]; // Exactly the initial buffer size
        for (int i = 0; i < testData.length; i++)
        {
            testData[i] = (byte) (i % 256);
        }

        ByteArrayInputStream inputStream = new ByteArrayInputStream(testData);

        FullFileBuffer buffer = new FullFileBuffer();
        buffer.bufferFile(inputStream, testData.length);

        InputStream bufferedStream = buffer.getBufferedInputStream();
        byte[] result = readAllBytes(bufferedStream);

        assertArrayEquals("Exact buffer size should work", testData, result);
    }

    @Test
    public void testGetBufferedInputStream_MultipleReads() throws IOException
    {
        byte[] testData = "Test content for multiple reads".getBytes();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(testData);

        FullFileBuffer buffer = new FullFileBuffer();
        buffer.bufferFile(inputStream, testData.length);

        // Read multiple times from buffered stream
        InputStream stream1 = buffer.getBufferedInputStream();
        byte[] result1 = readAllBytes(stream1);

        InputStream stream2 = buffer.getBufferedInputStream();
        byte[] result2 = readAllBytes(stream2);

        assertArrayEquals("First read should match", testData, result1);
        assertArrayEquals("Second read should also match", testData, result2);
    }

    // Helper method to read all bytes from an input stream
    private byte[] readAllBytes(InputStream inputStream) throws IOException
    {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int bytesRead;
        byte[] data = new byte[1024];

        while ((bytesRead = inputStream.read(data, 0, data.length)) != -1)
        {
            buffer.write(data, 0, bytesRead);
        }

        return buffer.toByteArray();
    }
}
