package com.nilsok.shooter.model;

import com.nilsok.shooter.Command;
import com.nilsok.shooter.Const;
import com.nilsok.shooter.model.command.Shoot;
import com.nilsok.shooter.model.command.ShowTarget;
import com.nilsok.shooter.model.command.TargetHit;
import com.nilsok.shooter.server.ServerNetworking;

/**
 * Created by fimpen on 15-01-30.
 */
public class GameOnServer extends Game {


    private ServerNetworking serverNetworking;
    private int framesSinceLastTargetHit = 0;

    public GameOnServer() {
        this.serverNetworking = new ServerNetworking(this);
    }

    @Override
    public void tick() {
        super.tick();

    }

    @Override
    public void executeCommand(Command nextCommand) {
        super.executeCommand(nextCommand);

        if (nextCommand instanceof Shoot) {
            checkIfHit((Shoot) nextCommand);
        }
        framesSinceLastTargetHit++;
        checkAndSpawnTarget();
    }

    protected void checkAndSpawnTarget() {
        if (framesSinceLastTargetHit > Const.TARGET_SPAWN_INTERVAL) {
            target = new Target(300, 300);
            serverNetworking.addCommand(new ShowTarget(300, 300));
            framesSinceLastTargetHit = 0;
            System.out.println("ADDED A TARGET!");
        }
    }

    protected void checkIfHit(Shoot shot) {
        if (this.target != null) {
            if (target.shotHit(shot)) {
                players.get(shot.id()).score++;
                target.isHit = true;
                serverNetworking.addCommand(new TargetHit(shot.id()));
                framesSinceLastTargetHit = 0;
            }
        }
    }

    @Override
    protected void pushToClientIfServer(Command nextCommand) {
        this.serverNetworking.addCommand(nextCommand);
    }

    public ServerNetworking getNetworking() {
        return serverNetworking;
    }

}
