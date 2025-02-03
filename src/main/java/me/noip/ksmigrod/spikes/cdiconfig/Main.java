package me.noip.ksmigrod.spikes.cdiconfig;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

public class Main {
    public static void main(String[] args) {
        SeContainerInitializer containerInitializer = SeContainerInitializer.newInstance();
        try (SeContainer container = containerInitializer.initialize()) {
            OurBean ourBean = container.select(OurBean.class).get();
            System.out.println(ourBean.getTestProperty());
        }
    }
}
