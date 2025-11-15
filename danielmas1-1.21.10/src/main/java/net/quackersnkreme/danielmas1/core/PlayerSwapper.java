package net.quackersnkreme.danielmas1.core;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static net.minecraft.text.Text.literal;

public class PlayerSwapper {

    //The swap method

    public static void swap(MinecraftServer server) {

        //create list of players in world

        List<ServerPlayerEntity> players = new ArrayList<>(server.getPlayerManager().getPlayerList());

        //don't do anything if there is one player

        //if (server.getCurrentPlayerCount() < 2) return;

        //shuffle the list of players

        //Collections.shuffle(players);

        //get all the player locations
        players.getFirst().sendMessage(Text.of("" + players.size()));
       //List<Vec3d> playerLocations = getLocations(players);
//        for (int i = 0; i < players.size(); i++) {
//            int finalI = i;
//            server.getCommandSource().sendFeedback(() -> literal("" + playerLocations.get(finalI).x + playerLocations.get(finalI).y + playerLocations.get(finalI).z), false);
//        }


        //moves the players to the new location

        //applySwap(playerLocations, players);
    }

    //method to get locations

    private static List<Vec3d> getLocations(List<ServerPlayerEntity> players) {

        /*initialisation of variables
        * vec3d is used for player locations, you can probably do this more efficiently because setPos takes the individual double values
        * */

        List<Vec3d> locations = new ArrayList<>();
        double x;
        double y;
        double z;
        Vec3d pos;

        //for each play in the list of players, get their x,y, and z coords, and store them as a vec3d and put them in the locations arraylist

        for (ServerPlayerEntity player : players) {
            x = player.getX();
            y = player.getY();
            z = player.getZ();

            pos = new Vec3d(x, y, z);

            locations.add(pos);
        }

        return locations;
    }

    //apply swap method

    private static void applySwap(List<Vec3d> locations, List<ServerPlayerEntity> players) {

        /* because arraylist only has .size(), nextIndex has to be like this
        *  while i is smaller than players.size(), get player[i] and set their location to locations[nextIndex]
        */

        for (int i = 0; i < players.size(); i++) {
            int nextIndex = (i + 1) % players.size();
            players.get(i).setPos(locations.get(nextIndex).x, locations.get(nextIndex).y, locations.get(nextIndex).z);
        }
    }

}
