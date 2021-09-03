package com.espacex.discovery.spaceships;

import static com.espacex.discovery.spaceships.ShipType.*;

public class WarShip extends Ship {
    private boolean weaponsDeactivated;

    public WarShip(ShipType type) {
        super(type);
        if (type == HUNTER) {
            maxTonnage = 0;
        } else if (type == FRIGATE) {
            maxTonnage = 50;
        } else if (type == CRUISER) {
            maxTonnage = 100;
        }
    }

    void attack(Ship shipEnnemi, String weapon, int duration) {
        if (weaponsDeactivated) {
            System.out.println("Attack impossible, weapons are deactivated.");
        } else {
            System.out.println("A ship of the type " + type + " attacks a ship of the type " + shipEnnemi.type + " using the weapon " + weapon + " for " + duration + " minutes");
            shipEnnemi.shieldPower = 0;
            shipEnnemi.armor = shipEnnemi.armor / 2;
        }
    }

    public void deactivateWeapons() {
        weaponsDeactivated = true;
        System.out.println("Weapon deactivation for a ship of the type " + type);
        weaponsDeactivated = true;
    }

    public void activateWeapons() {
        weaponsDeactivated = false;
    }

    @Override
    public void activateShield() {
        deactivateWeapons();
        super.activateShield();
    }

    @Override
    public void deactivateShield() {
        deactivateWeapons();
        super.deactivateShield();
    }

    @Override
    public void carryCargo(int cargo) throws CargoTonnageException {
        if (type == HUNTER) {
            throw new CargoTonnageException(cargo);
        } else {
            if (nbPassengers < 12) {
                throw new CargoTonnageException(cargo);
            } else {
                int passengersTonnage = 2 * nbPassengers;
                int remainingTonnage = maxTonnage - actualTonnage;
                int tonnageToConsiderate = (Math.min(passengersTonnage, remainingTonnage));
                if (cargo > tonnageToConsiderate) {
                    int tonnageExcess = cargo - tonnageToConsiderate;
                    throw new CargoTonnageException(tonnageExcess);
                } else {
                    actualTonnage += cargo;
                }
            }
        }
    }
}
