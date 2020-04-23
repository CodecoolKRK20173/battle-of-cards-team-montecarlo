package com.myapp.battleofcards;

public enum Engine {
    trueHorse("true horse"),
    electric("electric"),
    diesel("diesel"),
    AGT1500C("AGT-1500C"),
    TSI("TSI"),
    OHV("OHV"),
    TCe("TCe"),
    R4("R4"),
    WBX6("WBX6"),
    Turbo("Turbo"),
    TurboS("Turbo S"),
    V6("V6"),
    V8("V8"),
    V10("V10"),
    V12("V12"),
    W12("W12");

    private String engine;

    Engine(String engine) {
        this.engine = engine;
    }

    public String getEngine() {
        return engine;
    }
}
