package com.jazzkuh.sktopia.events;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import nl.minetopiasdb.api.events.player.EmergencyCallEvent;
import nl.minetopiasdb.api.events.player.PlayerChangeLevelEvent;
import org.bukkit.event.Event;

@Name("Emergency Call")
@Description("Called when a player uses /112.")
public class EvtEmergencyCall extends SkriptEvent {

    public static void register() {
        Skript.registerEvent("Emergency Call", EvtEmergencyCall.class, EmergencyCallEvent.class, "(minetopiasdb|sdb) emergency call");
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Literal<?>[] args, int i, SkriptParser.ParseResult parseResult) {
        return true;
    }

    @Override
    public boolean check(Event event) {
        return event instanceof EmergencyCallEvent;
    }

    @Override
    public String toString(Event event, boolean debug) {
        return this.getClass().getAnnotation(Name.class).value();
    }
}
