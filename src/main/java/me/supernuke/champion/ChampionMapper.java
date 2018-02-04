package me.supernuke.champion;

import com.sun.javaws.exceptions.InvalidArgumentException;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;

import java.util.List;

public class ChampionMapper {

    private static World world;

    /**
     * used for champion's spawn manager (spec spawn, team spawns)
     * 1. x-cords 2. y-cords 3. z-cords
     */
    private static final Object[] blue_location = new Object[2];
    private static final Object[] red_location = new Object[2];
    private static final Object[] spectator_location = new Object[2];

    public static void update(String path, List<Object> objects) {

        switch (path) {
            case "blue":
                insertObjectsIntoArray(objects, blue_location);
                break;
            case "red":
                insertObjectsIntoArray(objects, red_location);
                break;
            case "spectator":
                insertObjectsIntoArray(objects, spectator_location);
                break;
            default:
                try {
                    throw new InvalidArgumentException(new String[]{"path doesn't equal blue, red, spectator"});
                } catch (InvalidArgumentException e) {
                    e.printStackTrace();
                }
        }
    }

    /**
     * 1. blue 2. red. 3. spectator 4... is an error, etc
     * @param position specified through 1 - 3, success on position makes the player spawn at the loc.
     */

    public static void teleport(LivingEntity entity, int position) {
        if (entity instanceof Player || entity instanceof Monster) {

            if (position == 1) {
                entity.teleport(gotoBlue());
            } else if (position == 2) {
                entity.teleport(gotoRed());
            } else if (position == 3) {
                entity.teleport(gotoSpectator());
            } else {

                // TODO: in a message class say an error occured
            }
        }
    }

    private static Location gotoBlue() {
        return new Location(world, (Double) blue_location[0], (Double) blue_location[1], (Double) blue_location[2]);
    }

    private static Location gotoRed() {
        return new Location(world, (Double) red_location[0], (Double) red_location[1], (Double) red_location[2]);
    }

    private static Location gotoSpectator() {
        return new Location(world, (Double) spectator_location[0], (Double) spectator_location[1], (Double) spectator_location[2]);
    }

    private static void insertObjectsIntoArray(List<Object> obj, Object[] objArray) {

        if (obj.size() == objArray.length) {
            for (int i = 0; i < obj.size(); i++) {
                objArray[i] = obj.get(i);
            }
        } else {
            throw new ArrayIndexOutOfBoundsException("listObject & objArray doesn't have the same size/length");
        }
    }
}
