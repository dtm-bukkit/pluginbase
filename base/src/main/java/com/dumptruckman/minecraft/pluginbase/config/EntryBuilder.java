package com.dumptruckman.minecraft.pluginbase.config;

import java.util.ArrayList;
import java.util.List;

public class EntryBuilder<T> {

    private String path;
    private T def = null;
    private List<String> comments = new ArrayList<String>();
    private Class<T> type;
    private EntrySerializer<T> serializer;
    private EntryValidator validator;

    public EntryBuilder(Class<T> type, String name) {
        this.path = name;
        this.type = type;
        this.serializer = new DefaultSerializer<T>(type);
        this.validator = new DefaultValidator();
    }
    
    public EntryBuilder<T> def(T def) {
        this.def = def;
        return this;
    }
    
    public EntryBuilder<T> comment(String comment) {
        comments.add(comment);
        return this;
    }
    
    public EntryBuilder<T> stringSerializer() {
        serializer = new DefaultStringSerializer<T>(this.type);
        return this;
    }

    public EntryBuilder<T> serializer(EntrySerializer<T> customSerializer) {
        serializer = customSerializer;
        return this;
    }

    public EntryBuilder<T> validator(EntryValidator validator) {
        this.validator = validator;
        return this;
    }

    public ConfigEntry<T> build() {
        return new DefaultConfigEntry<T>(type, path, def, comments, serializer, validator);
    }

    public MappedConfigEntry<T> buildMap() {
        return new DefaultMappedConfigEntry<T>(type, path, def, comments, serializer, validator);
    }
}