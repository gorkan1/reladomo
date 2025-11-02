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

package com.gs.fw.common.mithra.generator.databasetype;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for all 9 database type implementations
 */
public class DatabaseTypesTest
{
    @Test
    public void testCommonDatabaseType() {
        CommonDatabaseType db = new CommonDatabaseType();
        assertNotNull(db);
        assertNotNull(db.getSqlDataTypeForBoolean());
        assertNotNull(db.getSqlDataTypeForTimestamp());
        assertNotNull(db.getSqlDataTypeForTime());
        assertNotNull(db.getSqlDataTypeForDate());
        assertNotNull(db.getSqlDataTypeForChar());
        assertNotNull(db.getSqlDataTypeForString());
        assertNotNull(db.getSqlDataTypeForByte());
        assertNotNull(db.getSqlDataTypeForShort());
        assertNotNull(db.getSqlDataTypeForInt());
        assertNotNull(db.getSqlDataTypeForLong());
        assertNotNull(db.getSqlDataTypeForDouble());
        assertNotNull(db.getSqlDataTypeForFloat());
        assertNotNull(db.getSqlDataTypeForBigDecimal());
    }

    @Test
    public void testOracleDatabaseType() {
        OracleDatabaseType db = new OracleDatabaseType();
        assertNotNull(db);
        assertEquals("Oracle", db.getDatabaseType());
        assertTrue(db.getMaxSearchableArguments() > 0);
        assertTrue(db.supportsMultiValueTempTableInsert());
    }

    @Test
    public void testPostgresDatabaseType() {
        PostgresDatabaseType db = new PostgresDatabaseType();
        assertNotNull(db);
        assertEquals("Postgres", db.getDatabaseType());
        assertTrue(db.getMaxSearchableArguments() > 0);
    }

    @Test
    public void testMsSqlDatabaseType() {
        MsSqlDatabaseType db = new MsSqlDatabaseType();
        assertNotNull(db);
        assertEquals("MsSql", db.getDatabaseType());
        assertTrue(db.getMaxSearchableArguments() > 0);
    }

    @Test
    public void testSybaseDatabaseType() {
        SybaseDatabaseType db = new SybaseDatabaseType();
        assertNotNull(db);
        assertEquals("Sybase", db.getDatabaseType());
        assertTrue(db.getMaxSearchableArguments() > 0);
    }

    @Test
    public void testH2DatabaseType() {
        H2DatabaseType db = new H2DatabaseType();
        assertNotNull(db);
        assertEquals("H2", db.getDatabaseType());
        assertTrue(db.getMaxSearchableArguments() > 0);
    }

    @Test
    public void testDerbyDatabaseType() {
        DerbyDatabaseType db = new DerbyDatabaseType();
        assertNotNull(db);
        assertEquals("Derby", db.getDatabaseType());
        assertTrue(db.getMaxSearchableArguments() > 0);
    }

    @Test
    public void testMariaDatabaseType() {
        MariaDatabaseType db = new MariaDatabaseType();
        assertNotNull(db);
        assertEquals("MariaDB", db.getDatabaseType());
        assertTrue(db.getMaxSearchableArguments() > 0);
    }

    @Test
    public void testUdb82DatabaseType() {
        Udb82DatabaseType db = new Udb82DatabaseType();
        assertNotNull(db);
        assertEquals("Udb82", db.getDatabaseType());
        assertTrue(db.getMaxSearchableArguments() > 0);
    }

    @Test
    public void testDatabaseTypeSqlGeneration() {
        CommonDatabaseType db = new OracleDatabaseType();
        assertNotNull(db.getSqlDataTypeForInt());
        assertNotNull(db.getSqlDataTypeForString());
        assertNotNull(db.getSqlDataTypeForTimestamp());
    }

    @Test
    public void testDatabaseTypeNullable() {
        CommonDatabaseType db = new PostgresDatabaseType();
        assertNotNull(db.getNullableColumnConstraint(true));
        assertNotNull(db.getNullableColumnConstraint(false));
    }

    @Test
    public void testDatabaseTypeDefault() {
        CommonDatabaseType db = new H2DatabaseType();
        assertNotNull(db.getDefaultValue(false, "test"));
    }

    @Test
    public void testAllDatabaseTypesInstantiable() {
        // Ensure all database types can be instantiated
        assertNotNull(new CommonDatabaseType());
        assertNotNull(new OracleDatabaseType());
        assertNotNull(new PostgresDatabaseType());
        assertNotNull(new MsSqlDatabaseType());
        assertNotNull(new SybaseDatabaseType());
        assertNotNull(new H2DatabaseType());
        assertNotNull(new DerbyDatabaseType());
        assertNotNull(new MariaDatabaseType());
        assertNotNull(new Udb82DatabaseType());
    }
}
