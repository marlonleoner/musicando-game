package me.marlon.leoner.musicando.events.configuration.log;

import org.slf4j.ILoggerFactory;
import org.slf4j.IMarkerFactory;
import org.slf4j.helpers.NOPMDCAdapter;
import org.slf4j.spi.MDCAdapter;
import org.slf4j.spi.SLF4JServiceProvider;

public class SLF4JProvider implements SLF4JServiceProvider {

    private static final String REQUESTED_API_VERSION = "2.0.16";

    private SLF4JProviderLoggerFactory providerFactory;

    @Override
    public ILoggerFactory getLoggerFactory() {
        return providerFactory;
    }

    @Override
    public IMarkerFactory getMarkerFactory() {
        return null;
    }

    @Override
    public MDCAdapter getMDCAdapter() {
        return new NOPMDCAdapter();
    }

    @Override
    public String getRequestedApiVersion() {
        return REQUESTED_API_VERSION;
    }

    @Override
    public void initialize() {
        providerFactory = new SLF4JProviderLoggerFactory();
    }
}