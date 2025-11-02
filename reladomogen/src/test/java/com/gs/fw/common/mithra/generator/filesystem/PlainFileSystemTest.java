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

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class PlainFileSystemTest
{
    private PlainFileSystem fileSystem;

    @Before
    public void setUp()
    {
        fileSystem = new PlainFileSystem();
    }

    @Test
    public void testNewFile_SinglePath()
    {
        String path = "/tmp/test.txt";
        FauxFile file = fileSystem.newFile(path);

        assertNotNull("File should not be null", file);
        assertTrue("Should return PlainFile instance", file instanceof PlainFile);
        assertEquals("Path should match", path, file.getPath());
    }

    @Test
    public void testNewFile_ParentAndChild()
    {
        String parent = "/tmp";
        String child = "test.txt";
        FauxFile file = fileSystem.newFile(parent, child);

        assertNotNull("File should not be null", file);
        assertTrue("Should return PlainFile instance", file instanceof PlainFile);
        String expectedPath = parent + File.separator + child;
        assertEquals("Path should combine parent and child", expectedPath, file.getPath());
    }

    @Test
    public void testNewFile_FauxFileParentAndChild()
    {
        FauxFile parent = fileSystem.newFile("/tmp");
        String child = "test.txt";
        FauxFile file = fileSystem.newFile(parent, child);

        assertNotNull("File should not be null", file);
        assertTrue("Should return PlainFile instance", file instanceof PlainFile);
        assertTrue("Path should contain parent path", file.getPath().contains("/tmp"));
        assertTrue("Path should contain child name", file.getPath().contains("test.txt"));
    }

    @Test
    public void testNewFile_MultipleNestedPaths()
    {
        String parent = "/tmp/nested/directory";
        String child = "file.txt";
        FauxFile file = fileSystem.newFile(parent, child);

        String expectedPath = parent + File.separator + child;
        assertEquals("Should handle nested paths correctly", expectedPath, file.getPath());
    }
}
