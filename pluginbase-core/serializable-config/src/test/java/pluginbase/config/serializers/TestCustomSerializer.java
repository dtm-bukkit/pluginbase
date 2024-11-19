package pluginbase.config.serializers;

import org.junit.Test;
import pluginbase.config.TestBase;
import pluginbase.config.examples.Comprehensive;
import pluginbase.config.field.Field;
import pluginbase.config.field.FieldMap;
import pluginbase.config.field.FieldMapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestCustomSerializer extends TestBase {

    @Test
    public void testCustomSerializer() {
        FieldMap fieldMap = FieldMapper.getFieldMap(Comprehensive.class);
        Field field = fieldMap.getField("name");
        assertNotNull(field);
        Serializer serializer = field.getSerializer();
        assertEquals(StringSerializer.class, serializer.getClass());

        field = fieldMap.getField("custom");
        assertNotNull(field);
        serializer = field.getSerializer();
        assertEquals(CustomSerializer.class, serializer.getClass());

        field = fieldMap.getField("custom2");
        assertNotNull(field);
        serializer = field.getSerializer();
        assertEquals(CustomSerializer2.class, serializer.getClass());
    }
}
