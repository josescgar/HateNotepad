package com.escobeitor.hatenotepad.main;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Application custom configuration
 * Created by escobeitor on 27/05/15.
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "hatenote")
public class HatenoteConfiguration {

    private int notesPerPage;

    public int getNotesPerPage() {
        return notesPerPage;
    }
}
