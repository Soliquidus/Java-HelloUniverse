package com.espacex.discovery;

import com.espacex.discovery.astroobjects.*;
import com.espacex.discovery.spaceships.*;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import static com.espacex.discovery.spaceships.ShipType.*;


public class HelloUniverse {
    public static void main(String[] args) {

        GasPlanet jupiter = new GasPlanet("Jupiter");
        jupiter.diameter = 142984;
        jupiter.starDistance = 778.3f;
        TelluricPlanet mercury = new TelluricPlanet("Mercury", 1);
        mercury.diameter = 4880;
        mercury.starDistance = 57.9f;
        TelluricPlanet venus = new TelluricPlanet("Venus", 2);
        venus.diameter = 12100;
        venus.starDistance = 108.2f;
        TelluricPlanet earth = new TelluricPlanet("Earth", 10);
        earth.diameter = 12756;
        earth.starDistance = 149.6f;
        TelluricPlanet mars = new TelluricPlanet("Mars", 3);
        mars.diameter = 6792;
        mars.starDistance = 227.9f;
        GasPlanet saturn = new GasPlanet("Saturn");
        saturn.diameter = 120536;
        saturn.starDistance = 1427.0f;
        GasPlanet uranus = new GasPlanet("Uranus");
        uranus.diameter = 51118;
        uranus.starDistance = 2877.38f;
        GasPlanet neptune = new GasPlanet("Neptune");
        neptune.diameter = 49532;
        neptune.starDistance = 4497.07f;

        Galaxy solarSystem = new Galaxy();
        solarSystem.name = "Solar System";
        solarSystem.planets.add(venus);
        solarSystem.planets.add(uranus);
        solarSystem.planets.add(earth);
        solarSystem.planets.add(mars);
        solarSystem.planets.add(mercury);
        solarSystem.planets.add(jupiter);
        solarSystem.planets.add(saturn);
        solarSystem.planets.add(neptune);

        for (Planet nextPlanet : solarSystem.planets) {
            System.out.println("The next planet is " + nextPlanet.name);
        }

        Ship hunter = new WarShip(HUNTER);
        hunter.nbPassengers = 3;
        hunter.armor = 156;
        hunter.shieldPower = 2;

        Ship cruiser = new WarShip(CRUISER);
        cruiser.nbPassengers = 35;
        cruiser.armor = 851;
        cruiser.shieldPower = 25;

        Ship frigate = new WarShip(FRIGATE);
        frigate.nbPassengers = 100;
        frigate.armor = 542;
        frigate.shieldPower = 50;

        Ship cargo = new CivilianShip(CARGO);
        cargo.nbPassengers = 10000;
        cargo.armor = 1520;
        cargo.shieldPower = 20;

        Ship worldShip = new CivilianShip(WORLDSHIP);
        worldShip.nbPassengers = 10000;
        worldShip.armor = 4784;
        worldShip.shieldPower = 30;

        Ship hunter2 = new WarShip(HUNTER);
        hunter2.nbPassengers = 4;
        hunter2.armor = 156;
        hunter2.shieldPower = 2;

        Ship hunter3 = new WarShip(HUNTER);
        hunter3.nbPassengers = 5;
        hunter3.armor = 156;
        hunter3.shieldPower = 2;

        Ship cargo2 = new CivilianShip(CARGO);
        cargo2.nbPassengers = 10156;
        cargo2.armor = 1520;
        cargo2.shieldPower = 20;

        earth.hostShips(hunter2, hunter3, cargo2);

        Atmosphere atmosphereMars = new Atmosphere();
        atmosphereMars.composition.put("CO²", 95f);
        atmosphereMars.composition.put("N²", 3f);
        atmosphereMars.composition.put("AR", 1.5f);
        atmosphereMars.composition.put("NO", 0.013f);

        mars.atmosphere = atmosphereMars;

        System.out.println("The atmosphere on Mars is composed by :");
        for (Map.Entry<String, Float> composition : mars.atmosphere.composition.entrySet()) {
            System.out.println(composition.getValue() + "% de " + composition.getKey());
        }

        Scanner sc = new Scanner(System.in);
        boolean restart = true;
        while (restart) {
            System.out.println("What ship do you want to use : " + HUNTER.name() + ", " + FRIGATE.name() + ", " + CRUISER.name() + ", " + CARGO.name() + " ou " + WORLDSHIP.name() + " ?");
            String shipTypeString = sc.nextLine();
            ShipType shipType = ShipType.valueOf(shipTypeString);

            Ship selectedShip = switch (shipType) {
                case HUNTER -> hunter;
                case FRIGATE -> frigate;
                case CRUISER -> cruiser;
                case CARGO -> cargo;
                case WORLDSHIP -> worldShip;
            };

            System.out.println("Choose a planet from the solar system where to land : Mercury, Venus, Earth or Mars?");
            String planetName = sc.nextLine();

            Planet selectedPlanet = null;
            for (Planet p : solarSystem.planets) {
                if (p.name.equals(planetName)) {
                    selectedPlanet = p;
                    break;
                }
            }
            if (selectedPlanet instanceof GasPlanet) {
                System.out.println("This planet isn't telluric, start again.");
                continue;
            }
            TelluricPlanet planet = (TelluricPlanet) selectedPlanet;

            int wantedTonnage;
            while (true) {
                System.out.println("How many tonnage do you want to carry with ?");
                try {
                    wantedTonnage = sc.nextInt();
                    break;
                } catch(InputMismatchException e) {
                    System.out.println("Tonnage isn't numeric");
                }
                finally {
                    sc.nextLine();
                }
            }

            if (!planet.dockingPlaceAvailable(selectedShip)) {
                System.out.println("The ship can't dock on this planet due to a lack of space.");
            } else {
                planet.hostShips(selectedShip);
                try {
                    selectedShip.carryCargo(wantedTonnage);
                } catch (CargoTonnageException e) {
                    System.out.println("Ship rejected : " + e.tonnageExcess + " tons.");
                    System.out.println("Do you want to carry a partial tonnage of " +(wantedTonnage - e.tonnageExcess) + " tons (yes/no)?");
                    String accept = sc.nextLine();
                    sc.nextLine();
                    if (accept.equals("yes")) {
                        try {
                            selectedShip.carryCargo(wantedTonnage - e.tonnageExcess);
                        } catch (CargoTonnageException ex) {
                            System.out.println("Unexpected error!");
                        }
                    } else {
                        System.out.println("Operation cancelled.");
                    }
                }
            }
            System.out.println("Do you want to restart yes/no ?");
            restart = sc.nextLine().equals("yes");
        }
    }
}
