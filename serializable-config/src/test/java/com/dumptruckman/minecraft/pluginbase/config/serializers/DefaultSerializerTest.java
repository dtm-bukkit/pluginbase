package com.dumptruckman.minecraft.pluginbase.config.serializers;

import com.dumptruckman.minecraft.pluginbase.config.TestBase;
import com.dumptruckman.minecraft.pluginbase.config.examples.Comprehensive;
import com.dumptruckman.minecraft.pluginbase.config.examples.Unknown;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DefaultSerializerTest extends TestBase {

    Serializer<Object> serializer;

    @Before
    public void setUp() throws Exception {
        serializer = new DefaultSerializer();
    }

    @Test
    public void testSerializeDeserializeComprehensive() throws Exception {
        Comprehensive comprehensive = new Comprehensive();
        Object serializedForm = serializer.serializeRegisteredType(comprehensive);
        Comprehensive deserializedForm = (Comprehensive) serializer.deserialize(serializedForm, Comprehensive.class);
        assertEquals(comprehensive, deserializedForm);
    }

    @Test
    public void testSerializeDeserializeComprehensiveNullKeyMap() throws Exception {
        Comprehensive comprehensive = new Comprehensive();
        comprehensive.stringObjectMap.put(null, "something");
        Object serializedForm = serializer.serializeRegisteredType(comprehensive);
        Comprehensive deserializedForm = (Comprehensive) serializer.deserialize(serializedForm, Comprehensive.class);
        assertFalse(comprehensive.equals(deserializedForm));
    }

    @Test
    public void testSerializeDeserializeComprehensiveNullValueMap() throws Exception {
        Comprehensive comprehensive = new Comprehensive();
        comprehensive.stringObjectMap.put("something", null);
        Object serializedForm = serializer.serializeRegisteredType(comprehensive);
        Comprehensive deserializedForm = (Comprehensive) serializer.deserialize(serializedForm, Comprehensive.class);
        assertFalse(comprehensive.equals(deserializedForm));
    }

    @Test
    public void testSerializeUnknownObject() throws Exception {
        boolean thrown = false;
        try {
            serializer.serializeRegisteredType(new Unknown());
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }
}
