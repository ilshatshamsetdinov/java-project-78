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
    private final int number = 5;

    @Test
    public void testStringSchema() {
        StringSchema stringSchema = Validator.string();
        assertTrue(stringSchema.isValid(null));
        assertTrue(stringSchema.isValid(""));
        assertTrue(stringSchema.isValid("what does the fox say"));
        assertTrue(stringSchema.isValid(number));
        stringSchema.minLength(number);
        assertTrue(stringSchema.isValid("what does the fox say"));
        assertTrue(stringSchema.contains("wh").isValid("what does the fox say"));
        assertTrue(stringSchema.contains("what").isValid("what does the fox say"));
        assertFalse(stringSchema.contains("whatthe").isValid("what does the fox say"));
        assertTrue(stringSchema.isValid("whatthe does fox say"));
        assertFalse(stringSchema.isValid("what does the fox say"));
    }
    @Test
    public void testStringSchemaRequired() {
        StringSchema stringSchema = Validator.string();
        stringSchema.required();
        assertTrue(stringSchema.isValid("what does the fox say"));
        assertTrue(stringSchema.isValid("hexlet"));
        assertFalse(stringSchema.isValid(null));
        assertFalse(stringSchema.isValid(""));
        assertFalse(stringSchema.isValid(number));
        stringSchema.minLength(number);
        assertTrue(stringSchema.isValid("what does the fox say"));
        assertTrue(stringSchema.contains("wh").isValid("what does the fox say"));
        assertTrue(stringSchema.contains("what").isValid("what does the fox say"));
        assertFalse(stringSchema.contains("whatthe").isValid("what does the fox say"));
        assertFalse(stringSchema.isValid("what does the fox say"));
    }
    @Test
    public void testNumberSchema() {
        NumberSchema numberSchema = Validator.number();
        assertTrue(numberSchema.isValid(null));
        assertTrue(numberSchema.positive().isValid(null));
        assertFalse(numberSchema.isValid(-10));
        assertFalse(numberSchema.isValid(0));
        numberSchema.range(5, 10);
        assertTrue(numberSchema.isValid(5));
        assertTrue(numberSchema.isValid(10));
        assertFalse(numberSchema.isValid(4));
        assertFalse(numberSchema.isValid(11));
    }
    @Test
    public void testNumberSchemaRequired() {
        NumberSchema numberSchema = Validator.number();
        numberSchema.required();
        assertFalse(numberSchema.isValid(null));
        assertFalse(numberSchema.positive().isValid(null));
        assertFalse(numberSchema.isValid(-10));
        assertFalse(numberSchema.isValid("5"));
        numberSchema.range(5, 10);
        assertTrue(numberSchema.isValid(5));
        assertTrue(numberSchema.isValid(10));
        assertFalse(numberSchema.isValid(4));
        assertFalse(numberSchema.isValid(11));
    }
    @Test
    public void testMapSchema() {
        MapSchema mapSchema = Validator.map();
        Map<String, String> data = new HashMap<>();
        assertTrue(mapSchema.isValid(null));
        mapSchema.sizeof(2);
        assertFalse(mapSchema.isValid(data));
        data.put("key2", "value2");
        data.put("key3", "value3");
        assertTrue(mapSchema.isValid(data));
    }
    @Test
    public void testMapSchemaRequired() {
        MapSchema mapSchema = Validator.map();
        Map<String, String> data = new HashMap<>();
        mapSchema.required();
        assertFalse(mapSchema.isValid(null)); // false
        assertTrue(mapSchema.isValid(new HashMap())); // true
        data.put("key1", "value1");
        assertTrue(mapSchema.isValid(data));
        data.put("key2", "value2");
        data.put("key3", "value3");
        assertTrue(mapSchema.isValid(data));
        assertFalse(mapSchema.sizeof(2).isValid(data));
    }
    @Test
    public void testShape() {
        MapSchema mapSchema = Validator.map();
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", Validator.string().required());
        schemas.put("age", Validator.number().positive());
        mapSchema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);
        assertTrue(mapSchema.isValid(human1));

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        assertTrue(mapSchema.isValid(human2));

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        assertFalse(mapSchema.isValid(human3));

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", -5);
        assertFalse(mapSchema.isValid(human4));
    }
    @Test
    public void testShapeWithoutRequired() {
        MapSchema mapSchema = Validator.map();
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", Validator.string().minLength(6));
        schemas.put("age", Validator.number().positive());
        mapSchema.shape(schemas);
        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);
        assertFalse(mapSchema.isValid(human1));
        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Mayaya");
        human2.put("age", null);
        assertTrue(mapSchema.isValid(human2));

    }
}
