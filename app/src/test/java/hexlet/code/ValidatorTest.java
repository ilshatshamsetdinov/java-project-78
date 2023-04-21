package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ValidatorTest {
    private StringSchema stringSchema = Validator.string();
    private MapSchema mapSchema = Validator.map();
    private NumberSchema numberSchema = Validator.number();
    private final int number = 5;
    private Map<String, String> data = new HashMap<>();
    @Test
    public void testStringSchema() {
        assertTrue(stringSchema.isValid(null));
        assertTrue(stringSchema.isValid(""));
        assertTrue(stringSchema.isValid("what does the fox say"));
        assertTrue(stringSchema.isValid(number));
    }
    @Test
    public void testStringSchemaRequired() {
        stringSchema.required();

        assertTrue(stringSchema.isValid("what does the fox say"));
        assertTrue(stringSchema.isValid("hexlet"));
        assertFalse(stringSchema.isValid(null));
        assertFalse(stringSchema.isValid(""));
        assertFalse(stringSchema.isValid(number));
    }
    @Test
    public void testStringSchemaMinLength() {
        stringSchema.minLength(number);
        assertTrue(stringSchema.isValid("what does the fox say"));
    }
    @Test
    public void testStringSchemaContainsWithRequired() {
        stringSchema.required();
        assertTrue(stringSchema.contains("wh").isValid("what does the fox say"));
        assertTrue(stringSchema.contains("what").isValid("what does the fox say"));
        assertFalse(stringSchema.contains("whatthe").isValid("what does the fox say"));
        assertTrue(stringSchema.isValid("whatthe does fox say"));
        assertFalse(stringSchema.isValid("what does the fox say"));
    }
    @Test
    public void testStringSchemaContainsWithoutRequired() {
        assertTrue(stringSchema.contains("wh").isValid("what does the fox say"));
        assertTrue(stringSchema.contains("what").isValid("what does the fox say"));
        assertTrue(stringSchema.contains("whatthe").isValid("what does the fox say"));
        assertTrue(stringSchema.isValid("whatthe does fox say"));
        assertTrue(stringSchema.isValid("what does the fox say"));
    }
    @Test
    public void testNumberSchema() {
        assertTrue(numberSchema.isValid(null)); // true
        assertTrue(numberSchema.positive().isValid(null)); // true
    }
    @Test
    public void testNumberSchemaIsPositive() {
        numberSchema.positive();

        assertTrue(numberSchema.positive().isValid(null)); // true
        assertTrue(numberSchema.isValid(-10)); // false
        assertTrue(numberSchema.isValid(0)); // false
    }
    @Test
    public void testNumberSchemaRequired() {
        numberSchema.required();

        assertFalse(numberSchema.isValid(null)); // false
        assertTrue(numberSchema.isValid(10)); // true
        assertFalse(numberSchema.isValid("5")); // false
    }
    @Test
    public void testNumberSchemaRange() {
        numberSchema.range(5, 10);
        assertTrue(numberSchema.isValid(5)); // true
        assertTrue(numberSchema.isValid(10)); // true
        assertTrue(numberSchema.isValid(4)); // true
        assertTrue(numberSchema.isValid(11)); // true
    }
    @Test
    public void testMapSchema() {
        assertTrue(mapSchema.isValid(null)); // true
    }
    @Test
    public void testMapSchemaRequired() {
        mapSchema.required();
        assertFalse(mapSchema.isValid(null)); // false
        assertTrue(mapSchema.isValid(new HashMap())); // true
        data.put("key1", "value1");
        assertTrue(mapSchema.isValid(data)); // true
    }
    @Test
    public void testMapSchemaSizeOf() {
        mapSchema.required();
        mapSchema.sizeof(2);
        assertFalse(mapSchema.isValid(data));  // false
        data.put("key2", "value2");
        data.put("key3", "value3");
        assertTrue(mapSchema.isValid(data)); // true
    }
    @Test
    public void testMapSchemaSizeOfWithoutRequired() {
        mapSchema.sizeof(2);
        assertTrue(mapSchema.isValid(data));  // true
        data.put("key2", "value2");
        data.put("key3", "value3");
        assertTrue(mapSchema.isValid(data)); // true
    }
    @Test
    public void testShape() {
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", Validator.string().required());
        schemas.put("age", Validator.number().positive());
        mapSchema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);
        mapSchema.isValid(human1); // true

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        mapSchema.isValid(human2); // true

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        mapSchema.isValid(human3); // false

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", -5);
        mapSchema.isValid(human4); // false
    }
    @Test
    public void testShapeWithoutRequired() {
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", Validator.string().minLength(6));
        schemas.put("age", Validator.number().positive());
        mapSchema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);
        mapSchema.isValid(human1); // true

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        mapSchema.isValid(human2); // true

    }
}
