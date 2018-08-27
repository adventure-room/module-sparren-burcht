package com.programyourhome.adventureroom.module.sparrenburcht.dsl.converters;

import com.programyourhome.adventureroom.dsl.regex.RegexActionConverter;
import com.programyourhome.adventureroom.dsl.regex.RegexVariable;
import com.programyourhome.adventureroom.model.script.action.Action;

public abstract class AbstractSparrenBurchtActionConverter<A extends Action> implements RegexActionConverter<A> {

    public static final RegexVariable SPEAKER_ID = RegexActionConverter.RESOURCE_ID.withName("speakerId");
    public static final RegexVariable SPEAKER_IDS = RegexActionConverter.RESOURCE_IDS.withName("speakerIds");

}
