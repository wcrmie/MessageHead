package one.endst.messageHead;

import one.endst.messageHead.chat.Display;
import one.endst.messageHead.chat.Listener;
import one.endst.messageHead.commands.OpenVoiceMenu;
import one.endst.messageHead.commands.SetPitch;
import one.endst.messageHead.commands.SetVoice;
import one.endst.messageHead.inventory.MenuListener;
import one.endst.messageHead.util.ConfigFetcher;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class MessageHead extends JavaPlugin {

    private static MessageHead plugin;

    private ConfigFetcher config;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;

        int pluginId = 27012;
        Metrics metrics = new Metrics(this, pluginId);

        // Config
        this.saveDefaultConfig();
        this.config = new ConfigFetcher();

        // Listeners
        Bukkit.getPluginManager().registerEvents(new Listener(), this);
        Bukkit.getPluginManager().registerEvents(new MenuListener(), this);

        // Commands
        this.getCommand("setvoice").setExecutor(new SetVoice());
        this.getCommand("setpitch").setExecutor(new SetPitch());
        this.getCommand("voicemenu").setExecutor(new OpenVoiceMenu());

        getLogger().info("Plugin enabled");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        for (Player player : Bukkit.getOnlinePlayers()) {
            Display.purge(player);
        }

        getLogger().info("Plugin disabled");
    }

    public static MessageHead getPlugin() {
        return plugin;
    }

    public ConfigFetcher getConfigFetcher() {
        return config;
    }
}
