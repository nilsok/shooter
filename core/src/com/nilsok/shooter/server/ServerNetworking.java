package com.nilsok.shooter.server;

import com.badlogic.gdx.utils.AtomicQueue;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.nilsok.shooter.Command;
import com.nilsok.shooter.Const;
import com.nilsok.shooter.model.Game;
import com.nilsok.shooter.model.GameEntity;
import com.nilsok.shooter.model.Player;
import com.nilsok.shooter.model.command.*;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by fimpen on 15-01-25.
 */
public class ServerNetworking {

    private final GameOnServer game;
    private Server server;

    private AtomicQueue<Command> executedCommands;

    public ServerNetworking(final GameOnServer game) {
        this.game = game;
        this.executedCommands = new AtomicQueue<Command>(20);
        this.server = new Server();
        registerCommandsWithKryo();

        try {
            this.server.bind(Const.TCP_PORT, Const.UDP_PORT);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("CANNOT START SERVER!!");
        }


        this.server.start();

        this.server.addListener(new Listener() {
            @Override
            public void received(Connection connection, Object command) {
                if (command instanceof Command) {
                    game.addCommand((Command) command);
                }
            }
        });
    }

    private void registerCommandsWithKryo() {
        Kryo kryo = server.getKryo();
        kryo.register(Join.class);
        kryo.register(Leave.class);
        kryo.register(Shoot.class);
        kryo.register(UpdatePosition.class);
        kryo.register(ShowTarget.class);
        kryo.register(TargetHit.class);
        kryo.register(GameState.class);
        kryo.register(Player.class);
        kryo.register(GameEntity.class);
        kryo.register(ArrayList.class);
    }

    public void pushCommands() {
        Command nextCommand;
        while ((nextCommand = executedCommands.poll()) != null) {
            server.sendToAllUDP(nextCommand);
        }
    }


    public void addCommand(Command command) {
        this.executedCommands.put(command);
    }
}
