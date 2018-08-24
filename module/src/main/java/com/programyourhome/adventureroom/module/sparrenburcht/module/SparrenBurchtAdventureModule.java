package com.programyourhome.adventureroom.module.sparrenburcht.module;

import java.util.Arrays;
import java.util.Collection;

import com.programyourhome.adventureroom.dsl.regex.AbstractRegexDslAdventureModule;
import com.programyourhome.adventureroom.dsl.regex.RegexActionConverter;
import com.programyourhome.adventureroom.module.sparrenburcht.dsl.converters.SpeakAtSpeakersActionConverter;

public class SparrenBurchtAdventureModule extends AbstractRegexDslAdventureModule {

    public static final String ID = "sparrenburcht";

    private SparrenBurchtConfig config;

    public SparrenBurchtAdventureModule() {
        // We assume there will be one implementation available on the classpath. If not, behavior is undefined.
        this.initConfig();
    }

    private void initConfig() {
        this.config = new SparrenBurchtConfig();
        this.config.id = ID;
        this.config.name = "SparrenBurcht";
        this.config.description = "Module that combines other modules into combined actions";
    }

    @Override
    public SparrenBurchtConfig getConfig() {
        return this.config;
    }

    @Override
    protected Collection<RegexActionConverter<?>> getRegexActionConverters() {
        return Arrays.asList(new SpeakAtSpeakersActionConverter());
    }

    @Override
    public void stop() {
        // No action needed
    }

}
