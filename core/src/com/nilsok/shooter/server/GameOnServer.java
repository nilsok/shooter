package com.nilsok.shooter.server;

import com.badlogic.gdx.Gdx;
import com.nilsok.shooter.Command;
import com.nilsok.shooter.Const;
import com.nilsok.shooter.model.Game;
import com.nilsok.shooter.model.Target;
import com.nilsok.shooter.model.command.Shoot;
import com.nilsok.shooter.model.command.ShowTarget;
import com.nilsok.shooter.model.command.TargetHit;
import com.nilsok.shooter.server.ServerNetworking;

import java.util.Random;

/**
 * Created by fimpen on 15-01-30.
 */
public class GameOnServer extends Game {

    private ServerNetworking serverNetworking;
    private int framesSinceLastTargetHit = 0;
    private Random r = new Random();

    public GameOnServer() {
        this.serverNetworking = new ServerNetworking(this);
    }

    @Override
    public void tick() {
        super.tick();
        checkAndSpawnTarget();
        framesSinceLastTargetHit++;
    }

    @Override
    public void executeCommand(Command nextCommand) {
        super.executeCommand(nextCommand);

        if (nextCommand instanceof Shoot) {
            checkIfHit((Shoot) nextCommand);
        }
    }

    protected void checkAndSpawnTarget() {
        if (framesSinceLastTargetHit > Const.TARGET_SPAWN_INTERVAL && target == null) {
            target = new Target(r.nextInt(600), r.nextInt(300));
            serverNetworking.addCommand(new ShowTarget(target.getX(), target.getY()));
            framesSinceLastTargetHit = 0;
            System.out.println("ADDED A TARGET!");
        }
    }

    protected void checkIfHit(Shoot shot) {

        System.out.println("target?: " + target);

        if (this.target != null) {

            System.out.println("shot hit?: " + target.shotHit(shot));

            if (target.shotHit(shot)) {
                players.get(shot.id()).score++;
                target = null;
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
