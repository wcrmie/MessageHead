package one.endst.messageHead.util;

import one.endst.messageHead.MessageHead;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigFetcher {

    private final int charLimit;

    public ConfigFetcher() {

        MessageHead.getPlugin().reloadConfig();
        FileConfiguration config = MessageHead.getPlugin().getConfig();

        charLimit = config.getInt("char-limit", 256);

    }

    public int getCharLimit() {
        return charLimit;
    }

}
