package com.espacex.discovery.astroobjects;

public abstract class Planet implements Comparable{
    public String name;
    public long diameter;
    public Atmosphere atmosphere;
    static String form = "Spherical";
    static int nbDiscoveredPlanetes;

    public float starDistance;

    public Planet(String name) {
        this.name = name;
        nbDiscoveredPlanetes++;
    }

    public final int revolution(int degrees) {
        System.out.println("The planet " + name + " turns over its star with " + degrees + " degrees.");
        return degrees / 360;
    }

    public final int rotation(int degrees) {
        System.out.println("The planet " + name + " turns over itself with" + degrees + " degrees.");
        return degrees / 360;
    }

    static String expansion (double distance) {
        if (distance < 14) {
            return "Wow, that's fast!";
        } else {
            return "Is it me or is this faster than lightspeed?";
        }
    }

    @Override
    public int compareTo(Object o) {
        Planet otherPlanet = (Planet) o;
        return Float.compare(starDistance, otherPlanet.starDistance);
    }
}
