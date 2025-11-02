# Reladomogen Test Coverage Roadmap

## Current Status (2025-01-02)

### Tests Created: 9 files, ~101 test methods
- **Coverage:** ~4-5% (9/214 files)
- **Target:** 80% coverage
- **Estimated Effort to Target:** 7-9 months

---

## Test Files Completed ✅

1. **StandardGeneratedFileManagerTest** (14 tests) - File generation management
2. **PlainFileTest** (14 tests) - File system operations
3. **PlainFileSystemTest** (4 tests) - File system factory
4. **GenerationLogTest** (9 tests) - Log persistence
5. **CardinalityTest** (11 tests) - Relationship cardinality
6. **StringUtilityTest** (30+ tests) - String utilities
7. **MithraGeneratorExceptionTest** (6 tests) - Exception handling
8. **SkipPageExceptionTest** (3 tests) - Skip page exception
9. **FullFileBufferTest** (10 tests) - File buffering

---

## Remaining Work Breakdown

### Phase 1: Utility Classes (2-3 weeks) - Target: 15% coverage
**Remaining Classes (~11 files):**
- AutoShutdownThreadExecutor
- AwaitingThreadExecutor
- ChopAndStickResource
- SerialResource
- StringBuilderBuilder
- MithraXMLUtil
- HttpServletRequest/Response (mock implementations)
- JspFactory/JspWriter (mock implementations)

### Phase 2: Type System (2 weeks) - Target: 18% coverage
**Classes (~10 files):**
- JavaType and subclasses
- All type classes in `com.gs.fw.common.mithra.generator.type`
- Type conversion logic
- Primitive type handling

### Phase 3: Database Types (2-3 weeks) - Target: 22% coverage
**Classes (9 files):**
- CommonDatabaseType
- OracleDatabaseType
- PostgresDatabaseType
- MsSqlDatabaseType
- SybaseDatabaseType
- H2DatabaseType
- DerbyDatabaseType
- MariaDatabaseType
- Udb82DatabaseType

**Test Approach:**
- SQL generation validation
- DDL generation tests
- Type mapping tests
- Use testcontainers for integration tests

### Phase 4: Parsers & State Machines (3-4 weeks) - Target: 32% coverage
**Classes (~20 files):**
- ComputedAttributeParser (complex!)
- All parser state classes
- Expression parser
- Query parser
- Tokenization logic

**Test Approach:**
- Valid expression parsing
- Error cases and messages
- Nested expressions
- Edge cases

### Phase 5: Metadata & Attributes (3 weeks) - Target: 40% coverage
**Classes (~15 files):**
- AbstractAttribute
- Attribute
- AsOfAttribute
- EmbeddedValue
- EmbeddedValueMapping
- Index
- BeanState
- RelationshipAttribute (complex!)

**Dependencies:**
- Requires mock MithraObjectTypeWrapper
- Requires mock type system

### Phase 6: XML Parsing (3 weeks) - Target: 48% coverage
**Classes (~10 files):**
- MithraXMLObjectTypeParser
- MithraImportXMLObjectTypeParser
- XML validation
- Schema parsing

**Test Approach:**
- Sample XML files as test fixtures
- Validation error testing
- Checksum calculation
- Import resolution

### Phase 7: Core Generation (6-8 weeks) - Target: 65% coverage
**Classes (~15 files):**
- BaseMithraGenerator
- CoreMithraGenerator
- AbstractMithraGenerator
- Template handling
- Code formatting
- Multi-threaded generation

**Test Approach:**
- Golden file tests (compare generated code)
- Mock template engine
- Thread pool testing
- Error handling

### Phase 8: MithraObjectTypeWrapper (6-8 weeks) - Target: 75% coverage
**The Big One: 3,973 lines, single most critical class**

**Break into test classes:**
1. MithraObjectTypeWrapperBasicTest - Construction, basic properties
2. MithraObjectTypeWrapperAttributeTest - Attribute management
3. MithraObjectTypeWrapperRelationshipTest - Relationship handling
4. MithraObjectTypeWrapperValidationTest - Validation logic
5. MithraObjectTypeWrapperSqlTest - SQL generation
6. MithraObjectTypeWrapperIndexTest - Index management
7. MithraObjectTypeWrapperAsOfTest - Temporal attribute handling
8. MithraObjectTypeWrapperForeignKeyTest - Foreign key processing
9. MithraObjectTypeWrapperSourceAttributeTest - Source attribute logic
10. MithraObjectTypeWrapperIntegrationTest - End-to-end scenarios

**Estimated:** 200+ test methods needed

### Phase 9: Integration Tests (4 weeks) - Target: 80% coverage
**Test Types:**
- End-to-end generation from XML to Java code
- Golden file comparisons
- Multi-module generation
- Error scenarios
- Performance tests

### Phase 10: Gap Filling (2-3 weeks) - Target: 80%+ coverage
- Run JaCoCo coverage reports
- Identify untested branches
- Add parameterized tests for edge cases
- Test error paths
- Test concurrent scenarios

---

## How to Measure Coverage

### Setting Up JaCoCo

1. Add to your build configuration (Ant/Maven):

```xml
<!-- For Maven -->
<plugin>
    <groupId>org.jacoco</groupId>
    <artifactId>jacoco-maven-plugin</artifactId>
    <version>0.8.11</version>
    <executions>
        <execution>
            <goals>
                <goal>prepare-agent</goal>
            </goals>
        </execution>
        <execution>
            <id>report</id>
            <phase>test</phase>
            <goals>
                <goal>report</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

2. Run coverage:
```bash
mvn clean test jacoco:report
```

3. View report:
```bash
open target/site/jacoco/index.html
```

### Coverage Metrics to Track
- **Line Coverage:** Target 80%+
- **Branch Coverage:** Target 70%+
- **Method Coverage:** Target 85%+
- **Class Coverage:** Target 80%+

---

## Testing Patterns & Best Practices

### 1. Mock Complex Dependencies
For classes with heavy dependencies (like MithraObjectTypeWrapper), create test doubles:

```java
private static class MockMithraObjectTypeWrapper extends MithraObjectTypeWrapper {
    // Minimal implementation for testing
}
```

### 2. Use Test Fixtures
Create reusable test data:
```
src/test/resources/
  ├── test-xml/
  │   ├── simple-object.xml
  │   ├── temporal-object.xml
  │   └── relationship-object.xml
  └── expected-output/
      ├── SimpleObject.java
      └── SimpleObjectFinder.java
```

### 3. Golden File Testing
For code generation:
```java
@Test
public void testGenerateSimpleObject() {
    String generated = generator.generate(testXml);
    String expected = readFile("expected-output/SimpleObject.java");
    assertEquals(expected, generated);
}
```

### 4. Parameterized Tests
For testing multiple scenarios:
```java
@RunWith(Parameterized.class)
public class DatabaseTypeTest {
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            { new OracleDatabaseType(), "Oracle" },
            { new PostgresDatabaseType(), "PostgreSQL" },
            // ...
        });
    }
}
```

### 5. Test Utilities
Create test helper classes:
```java
public class TestGeneratorHelper {
    public static MithraObjectTypeWrapper createSimpleObject() { ... }
    public static Attribute createStringAttribute() { ... }
    public static Index createUniqueIndex() { ... }
}
```

---

## Priority Classes by Business Value

### P0 - Critical (Must have 90%+ coverage)
1. **MithraObjectTypeWrapper** - Core object model (3,973 lines)
2. **CoreMithraGenerator** - Main generation orchestrator
3. **StandardGeneratedFileManager** - File generation ✅ Done
4. **MithraXMLObjectTypeParser** - XML parsing

### P1 - High (Target 80%+ coverage)
5. **RelationshipAttribute** - Relationship management
6. **AbstractAttribute** - Attribute base logic
7. **ComputedAttributeParser** - Expression parsing
8. **DatabaseType classes** - SQL generation (all 9)
9. **BaseMithraGenerator** - Generator infrastructure

### P2 - Medium (Target 70%+ coverage)
10. **Index** - Index management
11. **AsOfAttribute** - Temporal logic
12. **EmbeddedValue** - Embedded value objects
13. **StringUtility** - String utilities ✅ Done
14. **Validation classes** - Input validation

### P3 - Lower Priority (Target 60%+ coverage)
- UI mock classes (HttpServlet*, Jsp*)
- Graph generator
- UML generator
- Documentation generators

---

## Estimated Timeline

| Phase | Duration | Cumulative Coverage | Cumulative Time |
|-------|----------|---------------------|-----------------|
| Phase 1: Utilities | 3 weeks | 15% | 3 weeks |
| Phase 2: Type System | 2 weeks | 18% | 5 weeks |
| Phase 3: Database Types | 3 weeks | 22% | 8 weeks |
| Phase 4: Parsers | 4 weeks | 32% | 12 weeks |
| Phase 5: Metadata | 3 weeks | 40% | 15 weeks |
| Phase 6: XML Parsing | 3 weeks | 48% | 18 weeks |
| Phase 7: Core Generation | 8 weeks | 65% | 26 weeks |
| Phase 8: MithraObjectTypeWrapper | 8 weeks | 75% | 34 weeks |
| Phase 9: Integration | 4 weeks | 80% | 38 weeks |
| Phase 10: Gap Filling | 3 weeks | 82%+ | 41 weeks |

**Total: ~9-10 months** (1 FTE)

---

## Quick Start for Continuing

To continue this work:

1. **Set up your build tool** (Maven recommended)
2. **Add JUnit 4 dependency**
3. **Add JaCoCo plugin** for coverage
4. **Start with Phase 1** (remaining utilities)
5. **Run existing tests** to ensure they pass
6. **Measure baseline coverage**
7. **Pick one class at a time** from the roadmap
8. **Aim for 80%+ coverage per class** before moving on

---

## Success Criteria

### Per-Class Acceptance Criteria
✅ 80%+ line coverage
✅ 70%+ branch coverage
✅ All public methods tested
✅ Error paths tested
✅ Edge cases covered
✅ Tests are maintainable and clear

### Module Acceptance Criteria
✅ 80% overall line coverage
✅ All P0 classes at 90%+
✅ All P1 classes at 80%+
✅ Integration tests pass
✅ Tests run in < 5 minutes
✅ Zero test failures

---

## Resources

- **JUnit 4 Documentation:** https://junit.org/junit4/
- **JaCoCo Documentation:** https://www.jacoco.org/jacoco/
- **Mockito** (for mocking): https://site.mockito.org/
- **AssertJ** (better assertions): https://assertj.github.io/doc/

---

## Questions?

For questions about this testing initiative:
1. Review existing test examples in `src/test/java`
2. Follow patterns established in completed tests
3. Refer to this roadmap for prioritization
4. Measure progress with JaCoCo regularly

**Good luck! You're 4-5% of the way there!** 🚀
