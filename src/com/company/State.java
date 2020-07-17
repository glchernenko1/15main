package com.company;

public abstract class State {

    private int g; // расстояние от начального состояния до текущего.
    private int h; // оценку расстояния от текущего состояния
    private State parent;
    /*
     * Возвращает вес состояния как сумму расстояния, от начального состояния до текущего и предполагаемого расстояния
     */
    public int getF() {
        return g + h;
    }


    public int getG() {
        return g;
    }


    public void setG(int g) {
        this.g = g;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public State getParent() {
        return parent;
    } //преведущее состояние

    public void setParent(State parent) {
        this.parent = parent;
    }

    public State(State parent) {
        this.parent = parent;
    }


}
