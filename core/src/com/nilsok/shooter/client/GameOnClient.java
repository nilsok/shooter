package com.nilsok.shooter.client;

import com.nilsok.shooter.Command;
import com.nilsok.shooter.Const;
import com.nilsok.shooter.model.Game;
import com.nilsok.shooter.model.Player;
import com.nilsok.shooter.model.Target;
import com.nilsok.shooter.model.command.GameState;
import com.nilsok.shooter.model.command.Shoot;
import com.nilsok.shooter.model.command.ShowTarget;
import com.nilsok.shooter.model.command.TargetHit;

import java.util.HashMap;

/**
 * Created by fimpen on 15-01-31.
 */
public class GameOnClient extends Game {

    String playerName;

    private volatile boolean isShooting;
    private volatile int shootingDelay;

    public GameOnClient(String playerName) {
        this.playerName = playerName;
        this.isShooting = false;
        this.shootingDelay = 0;
    }

    public String getPlayerName() {
        return playerName;
    }

    @Override
    public void tick() {
        super.tick();

        if (this.isShooting) {
            this.shootingDelay++;
            if (shootingDelay >= Const.SHOOTING_DELAY) {
                this.isShooting = false;
                this.shootingDelay = 0;
            }
        }

    }

    @Override
    protected void executeCommand(Command nextCommand, long frame) {
        super.executeCommand(nextCommand, frame);

        if (nextCommand instanceof GameState) {
            GameState gameState = (GameState) nextCommand;
            super.players = new HashMap<String, Player>();
            for (Player p : gameState.players) {
                super.players.put(p.name, p);
            }

            if (gameState.target != null) {
                super.target = new Target(gameState.target.getX(), gameState.target.getY());
            }
        }

        if (nextCommand instanceof ShowTarget) {
            System.out.println("SHOWING TARGET!");
            ShowTarget showTarget = (ShowTarget) nextCommand;
            super.target = new Target(showTarget.x, showTarget.y);
        }

        if (nextCommand instanceof TargetHit) {
            TargetHit targetHit = (TargetHit) nextCommand;
            this.target = null;
            if (this.players.containsKey(targetHit.player)) {
                this.players.get(targetHit.player).score++;
            }
        }

        if (nextCommand instanceof Shoot && !isShooting) {
            this.isShooting = true;
        }

    }

    public boolean isShooting() {
        return isShooting;
    }

    public int getShootingDelay() {
        return this.shootingDelay;
    }
}
