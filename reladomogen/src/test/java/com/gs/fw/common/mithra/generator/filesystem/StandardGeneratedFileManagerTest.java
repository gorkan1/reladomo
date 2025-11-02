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

import com.gs.fw.common.mithra.generator.GenerationLog;
import com.gs.fw.common.mithra.generator.GenerationLogger;
import com.gs.fw.common.mithra.generator.Logger;
import com.gs.fw.common.mithra.generator.MithraGeneratorException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;

public class StandardGeneratedFileManagerTest
{
    private File tempDir;
    private File generatedDir;
    private File nonGeneratedDir;
    private StandardGeneratedFileManager fileManager;
    private TestLogger logger;
    private TestGenerationLogger generationLogger;
    private PlainFileSystem fileSystem;

    @Before
    public void setUp() throws IOException
    {
        // Create temporary directories for testing
        tempDir = Files.createTempDirectory("reladomo-test").toFile();
        generatedDir = new File(tempDir, "generated");
        nonGeneratedDir = new File(tempDir, "nongenerated");
        generatedDir.mkdirs();
        nonGeneratedDir.mkdirs();

        // Set up test infrastructure
        logger = new TestLogger();
        generationLogger = new TestGenerationLogger();
        fileSystem = new PlainFileSystem();
        fileManager = new StandardGeneratedFileManager();
    }

    @After
    public void tearDown()
    {
        // Clean up temporary directories
        deleteDirectory(tempDir);
    }

    @Test
    public void testShouldCreateFile_NewFile_ReplaceIfExists()
    {
        GeneratedFileManager.Options options = createOptions(true, false);
        fileManager.setOptions(options);

        boolean result = fileManager.shouldCreateFile(true, "com.example", "TestClass", "");

        assertTrue("Should create new file when replaceIfExists is true", result);
    }

    @Test
    public void testShouldCreateFile_ExistingFile_ReplaceIfExists()
    {
        GeneratedFileManager.Options options = createOptions(true, false);
        fileManager.setOptions(options);

        // Create existing file
        createTestFile(generatedDir, "com.example", "TestClass", ".java");

        boolean result = fileManager.shouldCreateFile(true, "com.example", "TestClass", "");

        assertTrue("Should create file when replaceIfExists is true even if file exists", result);
    }

    @Test
    public void testShouldCreateFile_ExistingFile_DoNotReplace()
    {
        GeneratedFileManager.Options options = createOptions(true, false);
        fileManager.setOptions(options);

        // Create existing file in non-generated directory
        createTestFile(nonGeneratedDir, "com.example", "TestClass", ".java");

        boolean result = fileManager.shouldCreateFile(false, "com.example", "TestClass", "");

        assertFalse("Should not create file when replaceIfExists is false and file exists", result);
    }

    @Test
    public void testShouldCreateFile_NonExistingFile_DoNotReplace_GenerateConcreteClasses()
    {
        GeneratedFileManager.Options options = createOptions(true, true);
        fileManager.setOptions(options);

        boolean result = fileManager.shouldCreateFile(false, "com.example", "TestClass", "");

        assertTrue("Should create file when generateConcreteClasses is true even if file doesn't exist", result);
    }

    @Test
    public void testShouldCreateFile_NonExistingFile_DoNotReplace_NoGenerateConcreteClasses_WarnEnabled()
    {
        GeneratedFileManager.Options options = createOptions(false, true);
        fileManager.setOptions(options);

        boolean result = fileManager.shouldCreateFile(false, "com.example", "TestClass", "");

        assertFalse("Should not create file when generateConcreteClasses is false and file doesn't exist", result);
        assertTrue("Should have logged warning", logger.hasInfoMessage("concrete class file"));
    }

    @Test
    public void testShouldCreateFile_ExistingFile_NoChanges()
    {
        // Set up generation logs with same content
        GenerationLog log = new GenerationLog("md5hash", "crc123");
        generationLogger.setOldGenerationLog(log);
        generationLogger.setNewGenerationLog(log);

        GeneratedFileManager.Options options = new GeneratedFileManager.Options(
                generatedDir.getAbsolutePath(),
                nonGeneratedDir.getAbsolutePath(),
                false,
                true,
                generationLogger,
                logger,
                fileSystem
        );
        fileManager.setOptions(options);

        // Create existing file
        createTestFile(generatedDir, "com.example", "TestClass", ".java");

        boolean result = fileManager.shouldCreateFile(true, "com.example", "TestClass", "");

        assertFalse("Should skip file when no changes to generator have been made", result);
    }

    @Test
    public void testWriteFile_NewFile() throws IOException
    {
        GeneratedFileManager.Options options = createOptions(true, false);
        fileManager.setOptions(options);

        String packageName = "com.example";
        String className = "TestClass";
        byte[] content = "public class TestClass {}".getBytes();
        AtomicInteger count = new AtomicInteger(0);

        fileManager.writeFile(true, packageName, className, "", content, count);

        // Verify file was created
        File expectedFile = new File(new File(generatedDir, "com.example"), "TestClass");
        assertTrue("File should be created", expectedFile.exists());
        assertEquals("Counter should be incremented", 1, count.get());
    }

    @Test
    public void testWriteFile_UpdatedContent() throws IOException
    {
        GeneratedFileManager.Options options = createOptions(true, false);
        fileManager.setOptions(options);

        String packageName = "com.example";
        String className = "TestClass";
        byte[] originalContent = "public class TestClass {}".getBytes();
        byte[] updatedContent = "public class TestClass { int x; }".getBytes();
        AtomicInteger count = new AtomicInteger(0);

        // Write original file
        fileManager.writeFile(true, packageName, className, "", originalContent, count);
        assertEquals("Counter should be incremented for new file", 1, count.get());

        // Write updated file
        fileManager.writeFile(true, packageName, className, "", updatedContent, count);
        assertEquals("Counter should be incremented for updated file", 2, count.get());
    }

    @Test
    public void testWriteFile_SameContent() throws IOException
    {
        GeneratedFileManager.Options options = createOptions(true, false);
        fileManager.setOptions(options);

        String packageName = "com.example";
        String className = "TestClass";
        byte[] content = "public class TestClass {}".getBytes();
        AtomicInteger count = new AtomicInteger(0);

        // Write original file
        fileManager.writeFile(true, packageName, className, "", content, count);
        assertEquals("Counter should be incremented for new file", 1, count.get());

        // Write same content again
        fileManager.writeFile(true, packageName, className, "", content, count);
        assertEquals("Counter should not be incremented for same content", 1, count.get());
    }

    @Test
    public void testReadFileInGeneratedDir_ExistingFile() throws IOException
    {
        GeneratedFileManager.Options options = createOptions(true, false);
        fileManager.setOptions(options);

        // Create a test file
        String relativePath = "com.example/TestClass.java";
        String content = "public class TestClass {}";
        createTestFileWithContent(generatedDir, relativePath, content.getBytes());

        byte[] result = fileManager.readFileInGeneratedDir(relativePath);

        assertNotNull("Should read existing file", result);
        assertEquals("Content should match", content, new String(result));
    }

    @Test
    public void testReadFileInGeneratedDir_NonExistingFile() throws IOException
    {
        GeneratedFileManager.Options options = createOptions(true, false);
        fileManager.setOptions(options);

        byte[] result = fileManager.readFileInGeneratedDir("com.example/NonExistent.java");

        assertNull("Should return null for non-existing file", result);
    }

    @Test
    public void testReadFileInGeneratedDir_Directory() throws IOException
    {
        GeneratedFileManager.Options options = createOptions(true, false);
        fileManager.setOptions(options);

        // Create a directory
        File dir = new File(generatedDir, "com.example");
        dir.mkdirs();

        byte[] result = fileManager.readFileInGeneratedDir("com.example");

        assertNull("Should return null for directory", result);
    }

    @Test(expected = MithraGeneratorException.class)
    public void testCopyIfChanged_ReadOnlyFile() throws IOException
    {
        GeneratedFileManager.Options options = createOptions(true, false);
        fileManager.setOptions(options);

        // Create a read-only file
        File packageDir = new File(generatedDir, "com.example");
        packageDir.mkdirs();
        File readOnlyFile = new File(packageDir, "ReadOnlyClass");
        readOnlyFile.createNewFile();
        readOnlyFile.setWritable(false);

        try
        {
            byte[] newContent = "public class ReadOnlyClass { int x; }".getBytes();
            FauxFile fauxFile = fileSystem.newFile(readOnlyFile.getAbsolutePath());
            fileManager.copyIfChanged(newContent, fauxFile, new AtomicInteger());
        }
        finally
        {
            // Clean up - restore write permission
            readOnlyFile.setWritable(true);
        }
    }

    // Helper methods

    private GeneratedFileManager.Options createOptions(boolean generateConcreteClasses, boolean warnAboutConcreteClasses)
    {
        generationLogger.setOldGenerationLog(new GenerationLog("old", "old"));
        generationLogger.setNewGenerationLog(new GenerationLog("new", "new"));

        return new GeneratedFileManager.Options(
                generatedDir.getAbsolutePath(),
                nonGeneratedDir.getAbsolutePath(),
                warnAboutConcreteClasses,
                generateConcreteClasses,
                generationLogger,
                logger,
                fileSystem
        );
    }

    private void createTestFile(File baseDir, String packageName, String className, String suffix)
    {
        File packageDir = new File(baseDir, packageName);
        packageDir.mkdirs();
        File file = new File(packageDir, className + suffix);
        try
        {
            file.createNewFile();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    private void createTestFileWithContent(File baseDir, String relativePath, byte[] content)
    {
        File file = new File(baseDir, relativePath);
        file.getParentFile().mkdirs();
        try
        {
            Files.write(file.toPath(), content);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
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

    // Test helper classes

    private static class TestLogger implements Logger
    {
        private StringBuilder log = new StringBuilder();

        @Override
        public void info(String msg)
        {
            log.append("INFO: ").append(msg).append("\n");
        }

        @Override
        public void info(String msg, Throwable t)
        {
            log.append("INFO: ").append(msg).append(" - ").append(t.getMessage()).append("\n");
        }

        @Override
        public void warn(String msg)
        {
            log.append("WARN: ").append(msg).append("\n");
        }

        @Override
        public void warn(String msg, Throwable t)
        {
            log.append("WARN: ").append(msg).append(" - ").append(t.getMessage()).append("\n");
        }

        @Override
        public void error(String msg)
        {
            log.append("ERROR: ").append(msg).append("\n");
        }

        @Override
        public void error(String msg, Throwable t)
        {
            log.append("ERROR: ").append(msg).append(" - ").append(t.getMessage()).append("\n");
        }

        @Override
        public void debug(String msg)
        {
            log.append("DEBUG: ").append(msg).append("\n");
        }

        @Override
        public void debug(String msg, Throwable t)
        {
            log.append("DEBUG: ").append(msg).append(" - ").append(t.getMessage()).append("\n");
        }

        public boolean hasInfoMessage(String substring)
        {
            return log.toString().contains("INFO") && log.toString().contains(substring);
        }
    }

    private static class TestGenerationLogger implements GenerationLogger
    {
        private GenerationLog oldLog;
        private GenerationLog newLog;

        @Override
        public GenerationLog getOldGenerationLog()
        {
            return oldLog;
        }

        @Override
        public GenerationLog getNewGenerationLog()
        {
            return newLog;
        }

        @Override
        public void setOldGenerationLog(GenerationLog log)
        {
            this.oldLog = log;
        }

        @Override
        public void setNewGenerationLog(GenerationLog log)
        {
            this.newLog = log;
        }
    }
}
