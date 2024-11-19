package pluginbase.testplugin;

import pluginbase.config.annotation.SerializeWith;
import pluginbase.plugin.Settings;
import pluginbase.testplugin.pie.Pie;
import pluginbase.testplugin.pie.PieListSerializer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestConfig extends Settings {

    private UUID testUUID = UUID.randomUUID();

    @SerializeWith(PieListSerializer.class)
    private List<Pie> pieList = new ArrayList<Pie>();

    public List<Pie> getPieList() {
        return pieList;
    }

    @Override
    public String toString() {
        return "TestConfig{" +
                "debugLevel=" + getDebugLevel() +
                ", locale=" + getLocale() +
                ", testUUID=" + testUUID +
                ", pieList=" + pieList +
                '}';
    }
}
