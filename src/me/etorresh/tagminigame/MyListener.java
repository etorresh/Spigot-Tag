package me.etorresh.tagminigame;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;


public class MyListener implements Listener {
    Tag main = Tag.getPlugin(Tag.class);
    long cooldown = 10 * 60000;
    long timeLastUsage = 0;

    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent event){
        Entity damager = event.getDamager();
        Entity damageTaker = event.getEntity();
        if (damager instanceof Player && damageTaker instanceof Player && damager.getName().equals(main.taggedPlayer)){
            if ((System.currentTimeMillis() - timeLastUsage) >= cooldown){
                timeLastUsage = System.currentTimeMillis();
                Player tagger = (Player) damager;
                Player tagged = (Player) damageTaker;
                main.onTag(tagger, tagged);
            }
            else{
                ((Player)damager).sendMessage(ChatColor.RED + "Wait " + ((10 - (System.currentTimeMillis() - timeLastUsage)/60000)) + " minutes before tagging another player.");
            }
        }
    }
    @EventHandler
    public void onTaggerJoin(PlayerJoinEvent event){
        if (event.getPlayer().getName().equals(main.taggedPlayer)){
            event.getPlayer().setPlayerListName(ChatColor.RED + event.getPlayer().getName());
        }
    }
}

