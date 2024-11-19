package pluginbase.config;

import org.junit.Before;
import pluginbase.config.examples.Comprehensive;

public class TestBase {

    @Before
    public void registerClasses() {
        SerializableConfig.registerSerializableAsClass(Comprehensive.class);
    }
}
