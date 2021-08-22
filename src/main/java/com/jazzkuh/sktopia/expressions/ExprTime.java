package com.jazzkuh.sktopia.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import nl.minetopiasdb.api.playerdata.PlayerManager;
import nl.minetopiasdb.api.playerdata.objects.SDBPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

@Name("Time")
@Description({"The play time of a player"})
public class ExprTime extends SimpleExpression<String> {
    private Expression<Player> player;

    public static void register() {
        Skript.registerExpression(ExprTime.class, String.class, ExpressionType.COMBINED, "[the] (minetopiasdb|sdb) (playtime|playertime|time) of %player%");
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(final Expression<?>[] exprs, final int matchedPattern, final Kleenean isDelayed, final SkriptParser.ParseResult parser) {
        player = (Expression<Player>) exprs[0];
        return true;
    }

    @Override
    protected String[] get(final Event event) {
        Player basePlayer = player.getSingle(event);
        SDBPlayer sdbPlayer = PlayerManager.getOnlinePlayer(basePlayer.getUniqueId());
        if (sdbPlayer != null) {
            return new String[] {sdbPlayer.getTimeDays() + " days", sdbPlayer.getTimeHours() + " hours", sdbPlayer.getTimeMinutes() + " minutes", sdbPlayer.getTimeSeconds() + " seconds"};
        }
        return null;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public String toString(@Nullable Event e, final boolean debug) {
        return this.getClass().getAnnotation(Name.class).value();
    }

    @Override
    public boolean isSingle() {
        return false;
    }
}
