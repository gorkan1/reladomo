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

import com.gs.fw.common.mithra.generator.filesystem.GeneratedFileManager;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;

public class GenerationLogTest
{
    @Test
    public void testConstructorAndGetters()
    {
        String md5 = "abc123";
        String xmlCrc = "xyz789";

        GenerationLog log = new GenerationLog(md5, xmlCrc);

        assertEquals("MD5 should match", md5, log.getMd5());
        assertEquals("XML CRC should match", xmlCrc, log.getXmlCrc());
    }

    @Test
    public void testIsSame_IdenticalLogs()
    {
        GenerationLog log1 = new GenerationLog("md5hash", "crc123");
        GenerationLog log2 = new GenerationLog("md5hash", "crc123");

        assertTrue("Identical logs should be same", log1.isSame(log2));
    }

    @Test
    public void testIsSame_DifferentMd5()
    {
        GenerationLog log1 = new GenerationLog("md5hash1", "crc123");
        GenerationLog log2 = new GenerationLog("md5hash2", "crc123");

        assertFalse("Logs with different MD5 should not be same", log1.isSame(log2));
    }

    @Test
    public void testIsSame_DifferentXmlCrc()
    {
        GenerationLog log1 = new GenerationLog("md5hash", "crc123");
        GenerationLog log2 = new GenerationLog("md5hash", "crc456");

        assertFalse("Logs with different XML CRC should not be same", log1.isSame(log2));
    }

    @Test
    public void testIsSame_BothDifferent()
    {
        GenerationLog log1 = new GenerationLog("md5hash1", "crc123");
        GenerationLog log2 = new GenerationLog("md5hash2", "crc456");

        assertFalse("Logs with both fields different should not be same", log1.isSame(log2));
    }

    @Test
    public void testWriteAndReadLog() throws IOException
    {
        String generatedDir = "/tmp/generated";
        String classListName = "/tmp/generated/ClassList.xml";
        String md5 = "testmd5hash";
        String xmlCrc = "testcrc123";

        GenerationLog originalLog = new GenerationLog(md5, xmlCrc);

        // Use a mock file manager that stores data in memory
        TestGeneratedFileManager fileManager = new TestGeneratedFileManager();

        // Write the log
        originalLog.writeLog(generatedDir, fileManager, classListName);

        // Read the log back
        GenerationLog readLog = GenerationLog.readOldLog(generatedDir, classListName, fileManager);

        assertNotNull("Read log should not be null", readLog);
        assertEquals("MD5 should match", md5, readLog.getMd5());
        assertEquals("XML CRC should match", xmlCrc, readLog.getXmlCrc());
    }

    @Test
    public void testReadOldLog_NonExistentFile() throws IOException
    {
        TestGeneratedFileManager fileManager = new TestGeneratedFileManager();

        GenerationLog log = GenerationLog.readOldLog("/tmp/generated", "/tmp/generated/NonExistent.xml", fileManager);

        assertNotNull("Should return a default log", log);
        assertEquals("Should have default MD5", "xxx", log.getMd5());
        assertEquals("Should have default CRC", "00", log.getXmlCrc());
    }

    @Test
    public void testReadOldLog_CorruptedFile() throws IOException
    {
        TestGeneratedFileManager fileManager = new TestGeneratedFileManager();

        // Create a corrupted log file (only one line)
        byte[] corruptedData = "onlyoneline\n".getBytes();
        fileManager.writeFile(true, "", "ClassList.xml", ".log", corruptedData, new AtomicInteger());

        GenerationLog log = GenerationLog.readOldLog("/tmp/generated", "/tmp/generated/ClassList.xml", fileManager);

        assertNotNull("Should return a default log for corrupted file", log);
        assertEquals("Should have default MD5 for corrupted file", "xxx", log.getMd5());
        assertEquals("Should have default CRC for corrupted file", "00", log.getXmlCrc());
    }

    @Test
    public void testWriteLog_DifferentPaths() throws IOException
    {
        TestGeneratedFileManager fileManager = new TestGeneratedFileManager();
        GenerationLog log = new GenerationLog("md5", "crc");

        // Test with Windows-style path
        log.writeLog("C:\\projects\\generated", fileManager, "C:\\projects\\generated\\ClassList.xml");

        // Test with Unix-style path
        log.writeLog("/projects/generated", fileManager, "/projects/generated/ClassList.xml");

        // Both should work without throwing exceptions
        assertTrue("File manager should have received write calls", fileManager.getWriteCount() > 0);
    }

    // Test helper class
    private static class TestGeneratedFileManager implements GeneratedFileManager
    {
        private byte[] storedData;
        private String storedPath;
        private int writeCount = 0;

        @Override
        public void setOptions(Options options)
        {
        }

        @Override
        public boolean shouldCreateFile(boolean replaceIfExists, String packageName, String className, String fileSuffix)
        {
            return true;
        }

        @Override
        public void writeFile(boolean replaceIfExists, String packageName, String className, String fileSuffix, byte[] fileData, AtomicInteger count) throws IOException
        {
            this.storedData = fileData;
            this.storedPath = packageName + "/" + className + fileSuffix;
            this.writeCount++;
            count.incrementAndGet();
        }

        @Override
        public byte[] readFileInGeneratedDir(String relativePath) throws IOException
        {
            if (storedPath != null && storedPath.equals(relativePath))
            {
                return storedData;
            }
            return null;
        }

        public int getWriteCount()
        {
            return writeCount;
        }
    }
}
