package com.okelloSoftwarez;

import java.util.ArrayList;

public class Pizza {
    private String pizzaType;
    private ArrayList<String> pizzaIngredients;

    public Pizza(String pizzaType, ArrayList<String> pizzaIngredients) {
        this.pizzaType = pizzaType;
        this.pizzaIngredients = pizzaIngredients;
    }

    public String getPizzaType() {
        return pizzaType;
    }

    public ArrayList<String> getPizzaIngredients() {
        return pizzaIngredients;
    }

    @Override
    public String toString() {

        return getPizzaType() + getPizzaIngredients();
    }
}
