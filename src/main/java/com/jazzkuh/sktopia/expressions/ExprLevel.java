package com.jazzkuh.sktopia.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import nl.minetopiasdb.api.enums.LevelChangeReason;
import nl.minetopiasdb.api.playerdata.PlayerManager;
import nl.minetopiasdb.api.playerdata.objects.SDBPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

@Name("Level")
@Description({"The level of a player"})
public class ExprLevel extends SimpleExpression<Number> {
    private Expression<Player> player;

    public static void register() {
        Skript.registerExpression(ExprLevel.class, Number.class, ExpressionType.COMBINED, "[the] (minetopiasdb|sdb) level of %player%");
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(final Expression<?>[] exprs, final int matchedPattern, final Kleenean isDelayed, final SkriptParser.ParseResult parser) {
        player = (Expression<Player>) exprs[0];
        return true;
    }

    @Override
    protected Number[] get(final Event event) {
        Player basePlayer = player.getSingle(event);
        SDBPlayer sdbPlayer = PlayerManager.getOnlinePlayer(basePlayer.getUniqueId());
        if (sdbPlayer != null) {
            return new Number[] {sdbPlayer.getLevel()};
        }
        return null;
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @Override
    public String toString(@Nullable Event e, final boolean debug) {
        return this.getClass().getAnnotation(Name.class).value();
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.DELETE || mode == Changer.ChangeMode.ADD || mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.RESET || mode == Changer.ChangeMode.REMOVE) {
            return CollectionUtils.array(Number.class);
        }
        return null;
    }

    @Override
    public void change(Event event, Object[] delta, Changer.ChangeMode mode){
        Player basePlayer = player.getSingle(event);
        SDBPlayer sdbPlayer = PlayerManager.getOnlinePlayer(basePlayer.getUniqueId());
        if (sdbPlayer != null) {
            if (mode == Changer.ChangeMode.SET) {
                sdbPlayer.setLevel(((Number) delta[0]).intValue(), LevelChangeReason.API_CHANGED);
            } else if (mode == Changer.ChangeMode.ADD) {
                sdbPlayer.setLevel(sdbPlayer.getLevel() + ((Number) delta[0]).intValue(), LevelChangeReason.API_CHANGED);
            } else if (mode == Changer.ChangeMode.REMOVE) {
                sdbPlayer.setLevel(sdbPlayer.getLevel() - ((Number) delta[0]).intValue(), LevelChangeReason.API_CHANGED);
            } else if (mode == Changer.ChangeMode.DELETE || mode == Changer.ChangeMode.RESET) {
                sdbPlayer.setLevel(0, LevelChangeReason.API_CHANGED);
            }
        }
    }
}
