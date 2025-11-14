package net.quackersnkreme.danielmas1.core;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerSwapper {

    public static void swap(MinecraftServer server) {
        List<ServerPlayerEntity> randPlayers = new ArrayList<>(server.getPlayerManager().getPlayerList());
        List<ServerPlayerEntity> players = new ArrayList<>(server.getPlayerManager().getPlayerList());

        if (server.getCurrentPlayerCount() < 2) return;

        Collections.shuffle(randPlayers);
        List<Vec3d> playerLocations = getLocations(randPlayers);
        applySwap(playerLocations, players);
    }

    private static List<Vec3d> getLocations(List<ServerPlayerEntity> players) {
        List<Vec3d> locations = new ArrayList<>();
        double x;
        double y;
        double z;
        Vec3d pos;

        for (ServerPlayerEntity player : players) {
            x = player.getX();
            y = player.getY();
            z = player.getZ();

            pos = new Vec3d(x, y, z);

            locations.add(pos);
        }

        return locations;
    }

    private static void applySwap(List<Vec3d> locations, List<ServerPlayerEntity> players) {
        for (int i = 0; i < players.size(); i++) {
            players.get(i).setPos(locations.get(i).x, locations.get(i).y, locations.get(i).z);
        }
    }

}
