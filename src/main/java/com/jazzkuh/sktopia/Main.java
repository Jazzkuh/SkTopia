package com.jazzkuh.sktopia;

import ch.njol.skript.Skript;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import com.jazzkuh.sktopia.events.EvtATMOpen;
import com.jazzkuh.sktopia.events.EvtEmergencyCall;
import com.jazzkuh.sktopia.events.EvtLevelChange;
import com.jazzkuh.sktopia.expressions.*;
import nl.minetopiasdb.api.events.player.ATMOpenEvent;
import nl.minetopiasdb.api.events.player.EmergencyCallEvent;
import nl.minetopiasdb.api.events.player.PlayerChangeLevelEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nullable;

public class Main extends JavaPlugin {

    private static @lombok.Getter Main instance;

    @Override
    public void onEnable() {
        instance = this;

        if (Bukkit.getPluginManager().getPlugin("Skript") == null) {
            this.getLogger().severe("Skript has not been found, shutting down.");
            this.getPluginLoader().disablePlugin(this);
            return;
        }

        if (Bukkit.getPluginManager().getPlugin("MinetopiaSDB") == null) {
            this.getLogger().severe("MinetopiaSDB has not been found, shutting down.");
            this.getPluginLoader().disablePlugin(this);
            return;
        }

        Skript.registerAddon(this);

        ExprLevel.register();
        ExprPrefix.register();
        ExprGrayShards.register();
        ExprLuckyShards.register();
        ExprGoldShards.register();
        ExprGrayCoins.register();
        ExprFitness.register();
        ExprTime.register();

        EvtLevelChange.register();
        EvtEmergencyCall.register();
        EvtATMOpen.register();

        EventValues.registerEventValue(PlayerChangeLevelEvent.class, Player.class, new Getter<Player, PlayerChangeLevelEvent>() {
            @Nullable
            @Override
            public Player get(final PlayerChangeLevelEvent event) {
                return Bukkit.getPlayer(event.getPlayer().getUUID());
            }
        }, 0);

        EventValues.registerEventValue(PlayerChangeLevelEvent.class, Number.class, new Getter<Number, PlayerChangeLevelEvent>() {
            @Nullable
            @Override
            public Number get(final PlayerChangeLevelEvent event) {
                return event.getNewLevel();
            }
        }, 0);

        EventValues.registerEventValue(EmergencyCallEvent.class, Player.class, new Getter<Player, EmergencyCallEvent>() {
            @Nullable
            @Override
            public Player get(final EmergencyCallEvent event) {
                return event.getPlayer();
            }
        }, 0);

        EventValues.registerEventValue(EmergencyCallEvent.class, String.class, new Getter<String, EmergencyCallEvent>() {
            @Nullable
            @Override
            public String get(final EmergencyCallEvent event) {
                return event.getMessage();
            }
        }, 0);

        EventValues.registerEventValue(ATMOpenEvent.class, Player.class, new Getter<Player, ATMOpenEvent>() {
            @Nullable
            @Override
            public Player get(final ATMOpenEvent event) {
                return event.getPlayer();
            }
        }, 0);

        this.getLogger().info("Plugin has been loaded.");
    }
}