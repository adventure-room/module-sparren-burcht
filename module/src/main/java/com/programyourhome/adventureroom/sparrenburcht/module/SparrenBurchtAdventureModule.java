package com.programyourhome.adventureroom.sparrenburcht.module;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.programyourhome.adventureroom.dsl.regex.AbstractRegexDslAdventureModule;
import com.programyourhome.adventureroom.dsl.regex.RegexActionConverter;
import com.programyourhome.adventureroom.sparrenburcht.dsl.converters.SpeakAtSpeakerActionConverter;

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
    protected Map<Pattern, RegexActionConverter<?>> getRegexActionConverters() {
        Map<Pattern, RegexActionConverter<?>> converters = new HashMap<>();

        Pattern pattern = Pattern.compile("(?<characterId>[A-Za-z0-9]+) says \"(?<text>[^\"]+)\" at (?<speakerId>[A-Za-z0-9]+)");
        converters.put(pattern, new SpeakAtSpeakerActionConverter());

        return converters;
    }

}
