package com.jazzkuh.sktopia.events;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import nl.minetopiasdb.api.events.player.PlayerChangeLevelEvent;
import org.bukkit.event.Event;

@Name("Level Change")
@Description("Called when the level of a player is changed.")
public class EvtLevelChange extends SkriptEvent {

    public static void register() {
        Skript.registerEvent("Level Change", EvtLevelChange.class, PlayerChangeLevelEvent.class, "(minetopiasdb|sdb) level change");
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Literal<?>[] args, int i, SkriptParser.ParseResult parseResult) {
        return true;
    }

    @Override
    public boolean check(Event event) {
        return event instanceof PlayerChangeLevelEvent;
    }

    @Override
    public String toString(Event event, boolean debug) {
        return this.getClass().getAnnotation(Name.class).value();
    }
}
