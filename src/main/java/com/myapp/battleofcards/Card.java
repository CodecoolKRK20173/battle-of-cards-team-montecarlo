package com.myapp.battleofcards;




public class Card {
    private String name;

    EngineC engine;
    HorsePower horsePower;
    Acceleration acceleration;
    MaxSpeed maxSpeed;

    public Card(String name, int maxSpeed, float acceleration, int horsePower, String engine) {
        this.name = name;
        this.maxSpeed = new MaxSpeed(maxSpeed);
        this.acceleration = new Acceleration(acceleration);
        this.horsePower = new HorsePower(horsePower);
        this.engine = new EngineC(Engine.valueOf(engine));
    }



    public String returnTable(){
        String[] headers = {"Name", "MaxSpeed", "Acceleration", "HorsePower", "Engine"};
        String[][] data = {{this.getName(),
                    String.valueOf(this.maxSpeed.getValue()),
                    String.valueOf(this.acceleration.getValue()),
                    String.valueOf(this.horsePower.getValue()),
                                    this.engine.getValue().name()}};

        return com.jakewharton.fliptables.FlipTable.of(headers, data);
    }


    private class MaxSpeed implements Comparable<MaxSpeed>{
        public MaxSpeed(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        int value;

        @Override
        public int compareTo(MaxSpeed maxSpeed) {
            return Integer.compare(value, maxSpeed.getValue());
        }
    }

    private class Acceleration implements Comparable<Acceleration>{

        public Acceleration(float value) {
            this.value = value;
        }

        public float getValue() {
            return value;
        }

        float value;

        @Override
        public int compareTo(Acceleration acceleration) {
            return Float.compare(acceleration.getValue(), value);
        }
    }

    private class HorsePower implements Comparable<HorsePower>{
        public HorsePower(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        int value;
        @Override
        public int compareTo(HorsePower horsePower) {
            return Integer.compare(value, horsePower.getValue());
        }
    }

    private class EngineC implements Comparable<EngineC>{
        public Engine getValue() {
            return value;
        }

        public EngineC(Engine value) {
            this.value = value;
        }

        Engine value;
        @Override
        public int compareTo(EngineC engineC) {
            Enum self = value;
            Enum other = engineC.getValue();
            return self.ordinal() - other.ordinal();
        }
    }

    public String getName()  {
        return name;
    }
}
