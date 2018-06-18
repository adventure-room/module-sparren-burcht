package com.programyourhome.adventureroom.sparrenburcht.dsl.converters;

import com.programyourhome.adventureroom.dsl.regex.MatchResult;
import com.programyourhome.adventureroom.dsl.regex.RegexActionConverter;
import com.programyourhome.adventureroom.model.Adventure;
import com.programyourhome.adventureroom.sparrenburcht.model.SpeakAtSpeakerAction;
import com.programyourhome.immerse.domain.speakers.Speaker;

public class SpeakAtSpeakerActionConverter implements RegexActionConverter<SpeakAtSpeakerAction> {

    @Override
    public SpeakAtSpeakerAction convert(MatchResult matchResult, Adventure adventure) {
        SpeakAtSpeakerAction action = new SpeakAtSpeakerAction();
        action.character = adventure.getCharacter(matchResult.getValue("characterId"));
        action.text = matchResult.getValue("text");
        action.speaker = adventure.getResource(Speaker.class, matchResult.getValue("speakerId"));
        return action;
    }

}
