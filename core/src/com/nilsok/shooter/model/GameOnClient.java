package com.nilsok.shooter.model;

import com.nilsok.shooter.Command;
import com.nilsok.shooter.model.command.ShowTarget;
import com.nilsok.shooter.model.command.TargetHit;

/**
 * Created by fimpen on 15-01-31.
 */
public class GameOnClient extends Game {

    String playerName;

    public GameOnClient(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    @Override
    protected void executeCommand(Command nextCommand) {
        super.executeCommand(nextCommand);

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
    }
}
