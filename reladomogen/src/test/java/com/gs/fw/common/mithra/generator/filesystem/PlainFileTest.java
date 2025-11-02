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

package com.gs.fw.common.mithra.generator.filesystem;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;

import static org.junit.Assert.*;

public class PlainFileTest
{
    private File tempDir;
    private File testFile;

    @Before
    public void setUp() throws IOException
    {
        tempDir = Files.createTempDirectory("reladomo-plainfile-test").toFile();
        testFile = new File(tempDir, "test.txt");
    }

    @After
    public void tearDown()
    {
        deleteDirectory(tempDir);
    }

    @Test
    public void testExists_NonExistentFile()
    {
        PlainFile plainFile = new PlainFile(testFile.getAbsolutePath());
        assertFalse("Non-existent file should return false", plainFile.exists());
    }

    @Test
    public void testExists_ExistingFile() throws IOException
    {
        testFile.createNewFile();
        PlainFile plainFile = new PlainFile(testFile.getAbsolutePath());
        assertTrue("Existing file should return true", plainFile.exists());
    }

    @Test
    public void testGetName()
    {
        PlainFile plainFile = new PlainFile(testFile.getAbsolutePath());
        assertEquals("test.txt", plainFile.getName());
    }

    @Test
    public void testMkdirs()
    {
        File nestedDir = new File(tempDir, "a/b/c");
        PlainFile plainFile = new PlainFile(nestedDir.getAbsolutePath());

        boolean result = plainFile.mkdirs();

        assertTrue("mkdirs should return true", result);
        assertTrue("Directory should exist", nestedDir.exists());
        assertTrue("Should be a directory", nestedDir.isDirectory());
    }

    @Test
    public void testLength() throws IOException
    {
        String content = "Hello, World!";
        Files.write(testFile.toPath(), content.getBytes());

        PlainFile plainFile = new PlainFile(testFile.getAbsolutePath());

        assertEquals("Length should match file size", content.length(), plainFile.length());
    }

    @Test
    public void testCanWrite() throws IOException
    {
        testFile.createNewFile();
        PlainFile plainFile = new PlainFile(testFile.getAbsolutePath());

        assertTrue("Should be writable", plainFile.canWrite());

        testFile.setWritable(false);
        assertFalse("Should not be writable after setWritable(false)", plainFile.canWrite());

        testFile.setWritable(true);
    }

    @Test
    public void testIsDirectory() throws IOException
    {
        File dir = new File(tempDir, "testdir");
        dir.mkdir();

        PlainFile dirFile = new PlainFile(dir.getAbsolutePath());
        PlainFile regularFile = new PlainFile(testFile.getAbsolutePath());

        testFile.createNewFile();

        assertTrue("Directory should return true", dirFile.isDirectory());
        assertFalse("Regular file should return false", regularFile.isDirectory());
    }

    @Test
    public void testNewFileOutputStream() throws IOException
    {
        PlainFile plainFile = new PlainFile(testFile.getAbsolutePath());

        OutputStream outputStream = plainFile.newFileOutputStream();
        String content = "Test content";
        outputStream.write(content.getBytes());
        outputStream.close();

        assertTrue("File should exist after writing", testFile.exists());
        String readContent = new String(Files.readAllBytes(testFile.toPath()));
        assertEquals("Content should match", content, readContent);
    }

    @Test
    public void testNewFileInputStream() throws IOException
    {
        String content = "Test content for reading";
        Files.write(testFile.toPath(), content.getBytes());

        PlainFile plainFile = new PlainFile(testFile.getAbsolutePath());
        InputStream inputStream = plainFile.newFileInputStream();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1)
        {
            baos.write(buffer, 0, bytesRead);
        }
        inputStream.close();

        assertEquals("Content should match", content, baos.toString());
    }

    @Test(expected = FileNotFoundException.class)
    public void testNewFileInputStream_NonExistentFile() throws IOException
    {
        PlainFile plainFile = new PlainFile(testFile.getAbsolutePath());
        plainFile.newFileInputStream();
    }

    @Test
    public void testGetParent()
    {
        PlainFile plainFile = new PlainFile(testFile.getAbsolutePath());
        assertEquals("Parent should match", tempDir.getAbsolutePath(), plainFile.getParent());
    }

    @Test
    public void testGetPath()
    {
        PlainFile plainFile = new PlainFile(testFile.getAbsolutePath());
        assertEquals("Path should match", testFile.getAbsolutePath(), plainFile.getPath());
    }

    @Test
    public void testConstructorWithParent()
    {
        PlainFile parentFile = new PlainFile(tempDir.getAbsolutePath());
        PlainFile childFile = new PlainFile(parentFile, "child.txt");

        assertEquals("child.txt", childFile.getName());
        assertTrue("Path should contain parent path", childFile.getPath().contains(tempDir.getAbsolutePath()));
    }

    private void deleteDirectory(File directory)
    {
        if (directory.exists())
        {
            File[] files = directory.listFiles();
            if (files != null)
            {
                for (File file : files)
                {
                    if (file.isDirectory())
                    {
                        deleteDirectory(file);
                    }
                    else
                    {
                        file.delete();
                    }
                }
            }
            directory.delete();
        }
    }
}
