package com.jazzkuh.sktopia.events;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import nl.minetopiasdb.api.events.player.ATMOpenEvent;
import nl.minetopiasdb.api.events.player.EmergencyCallEvent;
import org.bukkit.event.Event;

@Name("ATM Open")
@Description("Called when a player opens an ATM.")
public class EvtATMOpen extends SkriptEvent {

    public static void register() {
        Skript.registerEvent("ATM Open", EvtATMOpen.class, ATMOpenEvent.class, "(minetopiasdb|sdb) atm open");
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Literal<?>[] args, int i, SkriptParser.ParseResult parseResult) {
        return true;
    }

    @Override
    public boolean check(Event event) {
        return event instanceof ATMOpenEvent;
    }

    @Override
    public String toString(Event event, boolean debug) {
        return this.getClass().getAnnotation(Name.class).value();
    }
}
