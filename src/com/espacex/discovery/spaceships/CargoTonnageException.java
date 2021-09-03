package com.espacex.discovery.spaceships;

public class CargoTonnageException extends Exception {

    public int tonnageExcess;

    public CargoTonnageException(int tonnageExcess) {
        super("Cargo can't be loaded, weight limit exceeded by " + tonnageExcess + " tons.");
        this.tonnageExcess = tonnageExcess;
    }
}
