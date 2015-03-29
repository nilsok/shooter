package com.nilsok.shooter.model.command;

import com.badlogic.gdx.utils.Array;
import com.nilsok.shooter.Command;
import com.nilsok.shooter.model.Game;
import com.nilsok.shooter.model.GameEntity;
import com.nilsok.shooter.model.Player;
import com.nilsok.shooter.server.GameOnServer;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Created by fimpen on 15-03-12.
 */
public class GameState implements Command {

    public Collection<Player> players;
    public GameEntity target;


    public GameState() {}

    public GameState(Game game) {
        this.players = new ArrayList<Player>(game.players.values().size());
        this.players.addAll(game.players.values());

        if (game.target != null) {
            target = new GameEntity(game.target.getX(), game.target.getY(), 10, 10);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameState gameState = (GameState) o;

        if (!players.equals(gameState.players)) return false;
        if (target != null ? !target.equals(gameState.target) : gameState.target != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = players.hashCode();
        result = 31 * result + (target != null ? target.hashCode() : 0);
        return result;
    }

    @Override
    public String id() {
        return null;
    }

    @Override
    public long frame() {
        return 0;
    }
}
