package com.nilsok.shooter.client;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.nilsok.shooter.Command;
import com.nilsok.shooter.Const;
import com.nilsok.shooter.model.Game;
import com.nilsok.shooter.model.command.*;

import java.io.IOException;
import java.net.InetAddress;

/**
 * Created by fimpen on 15-01-25.
 */
public class ClientNetworking {

    private Client client;
    private final Game game;

    public ClientNetworking(final Game game) throws IOException {
        this.game = game;
        this.client = new Client();
        registerCommandsWithKryo();
        this.client.start();

        InetAddress address = client.discoverHost(Const.UDP_PORT, 4000);
        this.client.connect(5000, Const.HOST, Const.TCP_PORT, Const.UDP_PORT);
//        this.client.connect(5000, "192.168.0.11", Const.TCP_PORT, Const.UDP_PORT);



        this.client.addListener(new Listener() {
            @Override
            public void received(Connection connection, Object command) {
                if (command instanceof Command) {
                    if (!(command instanceof UpdatePosition)) {
                        System.out.println("Client recieved command: " + command.getClass().getSimpleName());
                    }
                    game.addCommand((Command) command);
                }
            }
        });
    }

    public void sendCommand(Command c) {
        this.client.sendUDP(c);
    }

    public String getIp() {
        return String.valueOf(this.client.getID());
    }

    private void registerCommandsWithKryo() {
        Kryo kryo = client.getKryo();
        kryo.register(Join.class);
        kryo.register(Leave.class);
        kryo.register(Shoot.class);
        kryo.register(UpdatePosition.class);
        kryo.register(ShowTarget.class);
        kryo.register(TargetHit.class);
    }
}
