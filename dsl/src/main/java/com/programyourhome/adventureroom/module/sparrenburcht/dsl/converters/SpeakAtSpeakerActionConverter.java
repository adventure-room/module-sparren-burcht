package com.programyourhome.adventureroom.module.sparrenburcht.dsl.converters;

import com.programyourhome.adventureroom.dsl.regex.MatchResult;
import com.programyourhome.adventureroom.dsl.regex.RegexActionConverter;
import com.programyourhome.adventureroom.model.Adventure;
import com.programyourhome.adventureroom.module.immerse.model.SpeakerExternalResource;
import com.programyourhome.adventureroom.module.sparrenburcht.model.SpeakAtSpeakerAction;

public class SpeakAtSpeakerActionConverter implements RegexActionConverter<SpeakAtSpeakerAction> {

    @Override
    public String getRegexLine() {
        return CHARACTER_ID + " says " + TEXT + " at " + RESOURCE_ID;
    }

    @Override
    public SpeakAtSpeakerAction convert(MatchResult matchResult, Adventure adventure) {
        SpeakAtSpeakerAction action = new SpeakAtSpeakerAction();
        action.character = adventure.getCharacter(matchResult.getValue(CHARACTER_ID));
        action.text = matchResult.getValue(TEXT);
        action.speaker = adventure.getResource(SpeakerExternalResource.class, matchResult.getValue(RESOURCE_ID)).getWrappedObject();
        return action;
    }

}
