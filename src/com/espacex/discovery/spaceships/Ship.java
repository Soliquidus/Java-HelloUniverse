package com.espacex.discovery.spaceships;

public abstract class Ship {
    public final ShipType type;
    public int nbPassengers;
    public int armor;
    public int shieldPower;
    public int maxTonnage;
    protected int actualTonnage;

    protected Ship(ShipType type) {
        this.type = type;
    }

    public void activateShield() {
        System.out.println("Shield activation from a ship of the type " + type.name);
    }

    public void deactivateShield() {
        System.out.println("Shield deactivation from a ship of the type " + type.name);
    }

   public abstract void carryCargo(int tonnage) throws CargoTonnageException;
}
