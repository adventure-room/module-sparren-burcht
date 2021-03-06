package com.programyourhome.adventureroom.module.sparrenburcht.module;

import java.util.Arrays;
import java.util.Collection;

import com.programyourhome.adventureroom.dsl.regex.AbstractRegexDslAdventureModule;
import com.programyourhome.adventureroom.dsl.regex.RegexActionConverter;
import com.programyourhome.adventureroom.model.Adventure;
import com.programyourhome.adventureroom.model.execution.ExecutionContext;
import com.programyourhome.adventureroom.module.sparrenburcht.dsl.converters.SpeakAtImmerseActionConverter;

public class SparrenBurchtAdventureModule extends AbstractRegexDslAdventureModule {

    public static final String ID = "sparrenburcht";

    private SparrenBurchtConfig config;

    public SparrenBurchtAdventureModule() {
        this.initConfig();
    }

    private void initConfig() {
        this.config = new SparrenBurchtConfig();
        this.config.id = ID;
        this.config.name = "SparrenBurcht";
        this.config.description = "Module that combines other modules into combined actions";
    }

    @Override
    public void start(Adventure adventure, ExecutionContext context) {
        // No start actions needed.
    }

    @Override
    public SparrenBurchtConfig getConfig() {
        return this.config;
    }

    @Override
    protected Collection<RegexActionConverter<?>> getRegexActionConverters() {
        return Arrays.asList(new SpeakAtImmerseActionConverter());
    }

    @Override
    public void stop(Adventure adventure, ExecutionContext context) {
        // No action needed
    }

}
