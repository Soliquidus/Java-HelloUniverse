package com.espacex.discovery.astroobjects;

import com.espacex.discovery.spaceships.Ship;
import com.espacex.discovery.spaceships.WarShip;

public class TelluricPlanet extends Planet implements Habitable {

    Ship[][] dockedShips;
    int totalVisitors;

    public TelluricPlanet(String name, int dockSize) {
        super(name);
        this.dockedShips = new Ship[dockSize][dockSize];
    }

    public boolean dockingPlaceAvailable(Ship ship) {

        int indexZone = switch (ship.type) {
            case CARGO, WORLDSHIP -> 1;
            default -> 0;
        };

        for (int i = 0; i < dockedShips[indexZone].length; i++) {
            if (dockedShips[indexZone][i] == null) {
                return true;
            }
        }
        return false;
    }

    public void hostShips(Ship... ships) {

        for (int i = 0; i < ships.length; i++) {
            int indexZone = switch (ships[i].type) {
                case CARGO, WORLDSHIP -> 1;
                default -> 0;
            };

            for (int index = 0; index < dockedShips[indexZone].length; index++) {
                if (dockedShips[indexZone][index] == null) {
                    dockedShips[indexZone][index] = ships[i];
                    break;
                }
            }
            if (ships[i] instanceof WarShip) {
                ((WarShip) ships[i]).deactivateWeapons();
            }

            totalVisitors = totalVisitors + ships[i].nbPassengers;

        }
    }
}
