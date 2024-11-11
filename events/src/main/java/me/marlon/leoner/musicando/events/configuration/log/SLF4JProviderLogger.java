package me.marlon.leoner.musicando.events.configuration.log;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.Marker;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SLF4JProviderLogger implements Logger {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private final String name;

    public SLF4JProviderLogger(final String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isTraceEnabled() {
        return true;
    }

    @Override
    public void trace(String s) {

    }

    @Override
    public void trace(String s, Object o) {

    }

    @Override
    public void trace(String s, Object o, Object o1) {

    }

    @Override
    public void trace(String s, Object... objects) {

    }

    @Override
    public void trace(String s, Throwable throwable) {

    }

    @Override
    public boolean isTraceEnabled(Marker marker) {
        return true;
    }

    @Override
    public void trace(Marker marker, String s) {

    }

    @Override
    public void trace(Marker marker, String s, Object o) {

    }

    @Override
    public void trace(Marker marker, String s, Object o, Object o1) {

    }

    @Override
    public void trace(Marker marker, String s, Object... objects) {

    }

    @Override
    public void trace(Marker marker, String s, Throwable throwable) {

    }

    @Override
    public boolean isDebugEnabled() {
        return true;
    }

    @Override
    public void debug(String s) {
        logMessage(LevelEnum.DEBUG, s);
    }

    @Override
    public void debug(String s, Object o) {

    }

    @Override
    public void debug(String s, Object o, Object o1) {

    }

    @Override
    public void debug(String s, Object... objects) {

    }

    @Override
    public void debug(String s, Throwable throwable) {

    }

    @Override
    public boolean isDebugEnabled(Marker marker) {
        return true;
    }

    @Override
    public void debug(Marker marker, String s) {

    }

    @Override
    public void debug(Marker marker, String s, Object o) {

    }

    @Override
    public void debug(Marker marker, String s, Object o, Object o1) {

    }

    @Override
    public void debug(Marker marker, String s, Object... objects) {

    }

    @Override
    public void debug(Marker marker, String s, Throwable throwable) {

    }

    @Override
    public boolean isInfoEnabled() {
        return true;
    }

    @Override
    public void info(String s) {
        logMessage(LevelEnum.INFO, s);
    }

    @Override
    public void info(String s, Object o) {
        String format = s.replace("{}", "%s");
        logMessage(LevelEnum.INFO, String.format(format, o));
    }

    @Override
    public void info(String s, Object o, Object o1) {
        String format = s.replace("{}", "%s");
        logMessage(LevelEnum.INFO, String.format(format, o, o1));
    }

    @Override
    public void info(String s, Object... objects) {
        String format = s.replace("{}", "%s");
        logMessage(LevelEnum.INFO, String.format(format, objects));
    }

    @Override
    public void info(String s, Throwable throwable) {

    }

    @Override
    public boolean isInfoEnabled(Marker marker) {
        return false;
    }

    @Override
    public void info(Marker marker, String s) {

    }

    @Override
    public void info(Marker marker, String s, Object o) {

    }

    @Override
    public void info(Marker marker, String s, Object o, Object o1) {

    }

    @Override
    public void info(Marker marker, String s, Object... objects) {

    }

    @Override
    public void info(Marker marker, String s, Throwable throwable) {

    }

    @Override
    public boolean isWarnEnabled() {
        return true;
    }

    @Override
    public void warn(String s) {
        logMessage(LevelEnum.WARN, s);
    }

    @Override
    public void warn(String s, Object o) {
        String format = s.replace("{}", "%s");
        logMessage(LevelEnum.WARN, String.format(format, o));
    }

    @Override
    public void warn(String s, Object o, Object o1) {
        String format = s.replace("{}", "%s");
        logMessage(LevelEnum.WARN, String.format(format, o, o1));
    }

    @Override
    public void warn(String s, Object... objects) {
        String format = s.replace("{}", "%s");
        logMessage(LevelEnum.WARN, String.format(format, objects));
    }

    @Override
    public void warn(String s, Throwable throwable) {

    }

    @Override
    public boolean isWarnEnabled(Marker marker) {
        return false;
    }

    @Override
    public void warn(Marker marker, String s) {

    }

    @Override
    public void warn(Marker marker, String s, Object o) {

    }

    @Override
    public void warn(Marker marker, String s, Object o, Object o1) {

    }

    @Override
    public void warn(Marker marker, String s, Object... objects) {

    }

    @Override
    public void warn(Marker marker, String s, Throwable throwable) {

    }

    @Override
    public boolean isErrorEnabled() {
        return true;
    }

    @Override
    public void error(String s) {
        logMessage(LevelEnum.ERROR, s);
    }

    @Override
    public void error(String s, Object o) {
        String format = s.replace("{}", "%s");
        logMessage(LevelEnum.ERROR, String.format(format, o));
    }

    @Override
    public void error(String s, Object o, Object o1) {
        String format = s.replace("{}", "%s");
        logMessage(LevelEnum.ERROR, String.format(format, o, o1));
    }

    @Override
    public void error(String s, Object... objects) {
        String format = s.replace("{}", "%s");
        logMessage(LevelEnum.ERROR, String.format(format, objects));
    }

    @Override
    public void error(String s, Throwable throwable) {

    }

    @Override
    public boolean isErrorEnabled(Marker marker) {
        return false;
    }

    @Override
    public void error(Marker marker, String s) {

    }

    @Override
    public void error(Marker marker, String s, Object o) {

    }

    @Override
    public void error(Marker marker, String s, Object o, Object o1) {

    }

    @Override
    public void error(Marker marker, String s, Object... objects) {

    }

    @Override
    public void error(Marker marker, String s, Throwable throwable) {

    }

    private void logMessage(final LevelEnum level, final String message) {
        String now = dateFormat.format(new Date());
        String lvl = getLevel(level);
        String name = getFormattedName();

        System.out.println(String.format("%s | %s --- %s: %s", now, lvl, name, message));
    }

    private String getFormattedName() {
        return String.format("%s%s%s", ANSI_YELLOW, StringUtils.rightPad(name, 48, " "), ANSI_RESET);
    }

    private String getLevel(LevelEnum level) {
        String color = ANSI_WHITE;
        switch (level) {
            case DEBUG:
                color = ANSI_GREEN;
                break;
            case INFO:
                color = ANSI_CYAN;
                break;
            case WARN:
                color = ANSI_BLUE;
                break;
            case ERROR:
                color = ANSI_RED;
                break;
        }

        return String.format("%s%5s%s", color, StringUtils.rightPad(level.toString(), 5, " "), ANSI_RESET);
    }

}