package me.noip.ksmigrod.spikes.cdiconfig;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class OurBean {

    @Inject
    @ConfigProperty(name = "test.property")
    private String testProperty;

    public String getTestProperty() {
        return testProperty;
    }
}
