package me.etorresh.tagminigame;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;

public class Tag extends JavaPlugin{
    private FileConfiguration config;
    public String taggedPlayer;
    public Integer cooldown;

    @Override
    public void onEnable(){
        this.saveDefaultConfig();
        config = this.getConfig();
        taggedPlayer = config.get("tagged-player").toString();
        this.getCommand("tag").setExecutor(new TagCommandExecutor());
        getServer().getPluginManager().registerEvents(new MyListener(), this);
    }

    @Override
    public void onDisable(){
        config.set("tagged-player", taggedPlayer);
        config.set("cooldown", cooldown);
        saveConfig();
    }

    public void onTag(Player tagger, Player tagged){
        Bukkit.broadcastMessage(ChatColor.RED + tagged.getName() + ChatColor.WHITE + " has been tagged!");
        tagger.setPlayerListName(ChatColor.WHITE + tagger.getName());
        tagged.setPlayerListName(ChatColor.RED + tagged.getName());
        taggedPlayer = tagged.getName();

    }

    public void clear(Player target){
        target.setPlayerListName(ChatColor.WHITE + target.getName());
        if (target.getName().equals(taggedPlayer)){
            taggedPlayer = "";
        }
    }

    public void changeCooldown(Integer timeInMinutes){
        cooldown = timeInMinutes;
    }
}
