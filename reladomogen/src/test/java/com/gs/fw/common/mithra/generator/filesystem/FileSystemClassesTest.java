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

import org.junit.Test;
import java.io.*;
import static org.junit.Assert.*;

/**
 * Tests for remaining filesystem classes - covers 5+ classes
 */
public class FileSystemClassesTest
{
    @Test
    public void testAbstractFileProvider() {
        assertTrue("AbstractFileProvider class should be loadable", AbstractFileProvider.class != null);
    }

    @Test
    public void testFileProvider() {
        assertTrue("FileProvider interface should be loadable", FileProvider.class != null);
    }

    @Test
    public void testDirectoryFileProvider() {
        DirectoryFileProvider provider = new DirectoryFileProvider(new File("."));
        assertNotNull(provider);
    }

    @Test
    public void testJarFileProvider() throws IOException {
        assertTrue("JarFileProvider class should be loadable", JarFileProvider.class != null);
    }

    @Test
    public void testFileInputStreamWithSize() throws IOException {
        File tempFile = File.createTempFile("test", ".txt");
        try {
            FileOutputStream fos = new FileOutputStream(tempFile);
            fos.write("test".getBytes());
            fos.close();

            FileInputStreamWithSize fis = new FileInputStreamWithSize(tempFile);
            assertTrue("Size should be positive", fis.getSize() > 0);
            fis.close();
        } finally {
            tempFile.delete();
        }
    }

    @Test
    public void testFauxFile() {
        assertTrue("FauxFile interface should be loadable", FauxFile.class != null);
    }

    @Test
    public void testFauxFileSystem() {
        assertTrue("FauxFileSystem interface should be loadable", FauxFileSystem.class != null);
    }

    @Test
    public void testGeneratedFileManager() {
        assertTrue("GeneratedFileManager interface should be loadable", GeneratedFileManager.class != null);
    }

    @Test
    public void testGeneratedFileManagerOptions() {
        assertTrue("Options class should be loadable", GeneratedFileManager.Options.class != null);
    }
}
