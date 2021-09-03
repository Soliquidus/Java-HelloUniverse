package com.espacex.discovery.spaceships;

public enum ShipType {
    HUNTER("Hunter"),
    FRIGATE("Frigate"),
    CRUISER("Cruiser"),
    CARGO("Cargo"),
    WORLDSHIP("World-Ship");

    public String name;

    ShipType(String name){
            this.name = name;
    }
}
