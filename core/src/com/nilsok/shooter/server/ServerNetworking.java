package com.nilsok.shooter.server;

import com.badlogic.gdx.utils.AtomicQueue;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.nilsok.shooter.Command;
import com.nilsok.shooter.Const;
import com.nilsok.shooter.model.Game;
import com.nilsok.shooter.model.command.Join;
import com.nilsok.shooter.model.command.Leave;
import com.nilsok.shooter.model.command.Shoot;
import com.nilsok.shooter.model.command.UpdatePosition;

import java.io.IOException;

/**
 * Created by fimpen on 15-01-25.
 */
public class ServerNetworking {

    private final Game game;
    private Server server;

    private AtomicQueue<Command> executedCommands;

    public ServerNetworking(final Game game) {
        this.game = game;
        this.executedCommands = new AtomicQueue<Command>(20);
        this.server = new Server();
        registerCommandsWithKryo();

        try {
            this.server.bind(Const.TCP_PORT);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("CANNOT START SERVER!!");
        }


        this.server.start();

        this.server.addListener(new Listener() {
            @Override
            public void received(Connection connection, Object command) {
                if (command instanceof Command) {
                    System.out.println("server recieved command: " + command);
                    game.addCommand((Command) command);
                    executedCommands.put((Command) command);
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
    }

    public void pushCommands() {
        Command nextCommand;
        while ((nextCommand = executedCommands.poll()) != null) {
            server.sendToAllTCP(nextCommand);
        }
    }


}
