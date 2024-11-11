package me.marlon.leoner.musicando.events.configuration.log;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

public class SLF4JProviderLoggerFactory implements ILoggerFactory {

    @Override
    public Logger getLogger(final String name) {
        return new SLF4JProviderLogger(name);
    }
}