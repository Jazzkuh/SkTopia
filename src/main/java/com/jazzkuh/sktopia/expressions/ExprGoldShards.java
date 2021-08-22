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
import nl.minetopiasdb.api.playerdata.PlayerManager;
import nl.minetopiasdb.api.playerdata.objects.SDBPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

@Name("GoldShards")
@Description({"The amount of goldshards of a player"})
public class ExprGoldShards extends SimpleExpression<Double> {
    private Expression<Player> player;

    public static void register() {
        Skript.registerExpression(ExprGoldShards.class, Double.class, ExpressionType.COMBINED, "[the] [(minetopiasdb|sdb)] (goldshards|gold shards) of %player%");
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(final Expression<?>[] exprs, final int matchedPattern, final Kleenean isDelayed, final SkriptParser.ParseResult parser) {
        player = (Expression<Player>) exprs[0];
        return true;
    }

    @Override
    protected Double[] get(final Event event) {
        Player basePlayer = player.getSingle(event);
        SDBPlayer sdbPlayer = PlayerManager.getOnlinePlayer(basePlayer.getUniqueId());
        if (sdbPlayer != null) {
            return new Double[] {sdbPlayer.getGoldShards()};
        }
        return null;
    }

    @Override
    public Class<? extends Double> getReturnType() {
        return Double.class;
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
            return CollectionUtils.array(Double.class);
        }
        return null;
    }

    @Override
    public void change(Event event, Object[] delta, Changer.ChangeMode mode){
        Player basePlayer = player.getSingle(event);
        SDBPlayer sdbPlayer = PlayerManager.getOnlinePlayer(basePlayer.getUniqueId());
        if (sdbPlayer != null) {
            if (mode == Changer.ChangeMode.SET) {
                sdbPlayer.setGoldShards((Double) delta[0]);
            } else if (mode == Changer.ChangeMode.ADD) {
                sdbPlayer.setGoldShards(sdbPlayer.getGoldShards() + (Double) delta[0]);
            } else if (mode == Changer.ChangeMode.REMOVE) {
                sdbPlayer.setGoldShards(sdbPlayer.getGoldShards() - (Double) delta[0]);
            } else if (mode == Changer.ChangeMode.DELETE || mode == Changer.ChangeMode.RESET) {
                sdbPlayer.setGoldShards(0D);
            }
        }
    }
}
