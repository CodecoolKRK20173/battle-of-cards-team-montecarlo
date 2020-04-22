package com.myapp.battleofcards;

public enum Engine {
    electric("electric"),
    disel("disel"),
    trueHorse("true horse"),
    TurboS("Turbo S"),
    OHV("OHV"),
    AGT1500C("AGT-1500C"),
    WBX6("WBX6"),
    V10("V10"),
    V12("V12"),
    V8("V8"),
    W12("W12"),
    R4("R4"),
    V6("V6"),
    TSI("TSI"),
    TCe("TCe"),
    Turbo("Turbo");

    private String engine;

    Engine(String engine) {
        this.engine = engine;
    }

    public String getEngine() {
        return engine;
    }
}
