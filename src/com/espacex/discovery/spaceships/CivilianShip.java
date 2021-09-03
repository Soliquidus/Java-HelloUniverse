package com.espacex.discovery.spaceships;

import static com.espacex.discovery.spaceships.ShipType.CARGO;
import static com.espacex.discovery.spaceships.ShipType.WORLDSHIP;

public class CivilianShip extends Ship {
    public CivilianShip(ShipType type) {
        super(type);
        if (type == CARGO) {
            maxTonnage = 500;
        } else if (type == WORLDSHIP) {
            maxTonnage = 2000;
        }
    }

    @Override
    public void carryCargo(int cargo) throws CargoTonnageException {
        int remainingTonnage = maxTonnage - actualTonnage;
        if (cargo > remainingTonnage) {
            int cargoExcess = cargo - remainingTonnage;
            throw new CargoTonnageException(cargoExcess);
        } else {
            actualTonnage += cargo;
        }
    }
}
