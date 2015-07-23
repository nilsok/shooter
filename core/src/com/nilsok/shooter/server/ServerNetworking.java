package com.nilsok.shooter.server;

import com.badlogic.gdx.utils.AtomicQueue;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.nilsok.shooter.Command;
import com.nilsok.shooter.Const;
import com.nilsok.shooter.model.*;
import com.nilsok.shooter.model.command.*;
import com.nilsok.shooter.utils.Utils;

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
        System.out.println("listening for tcp on: " + Const.TCP_PORT);

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
        Utils.registerCommandsWithKryo(server.getKryo());
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
