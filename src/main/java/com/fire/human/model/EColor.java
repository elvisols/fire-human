package com.fire.human.model;

public enum EColor {
    RED("red"),
    ORANGE("orange"),
    YELLOW("yellow"),
    GREEN("green"),
    BLUE("blue"),
    INDIGO("indigo"),
    VIOLET("violet"),
    OTHER("other");

    private final String desc;

    EColor(String desc) {
        this.desc = desc;
    }
}
