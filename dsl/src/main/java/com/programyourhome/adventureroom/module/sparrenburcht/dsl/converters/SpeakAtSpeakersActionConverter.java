package com.programyourhome.adventureroom.module.sparrenburcht.dsl.converters;

import com.programyourhome.adventureroom.dsl.regex.MatchResult;
import com.programyourhome.adventureroom.dsl.regex.RegexActionConverter;
import com.programyourhome.adventureroom.model.Adventure;
import com.programyourhome.adventureroom.module.immerse.model.SpeakerExternalResource;
import com.programyourhome.adventureroom.module.sparrenburcht.model.SpeakAtSpeakersAction;

import one.util.streamex.StreamEx;

public class SpeakAtSpeakersActionConverter implements RegexActionConverter<SpeakAtSpeakersAction> {

    @Override
    public String getRegexLine() {
        return CHARACTER_ID + " says " + TEXT + " at " + RESOURCE_ID_LIST;
    }

    @Override
    public SpeakAtSpeakersAction convert(MatchResult matchResult, Adventure adventure) {
        SpeakAtSpeakersAction action = new SpeakAtSpeakersAction();
        action.character = adventure.getCharacter(matchResult.getValue(CHARACTER_ID));
        action.text = matchResult.getValue(TEXT);
        String[] resourceIds = matchResult.getValue(RESOURCE_ID_LIST).split(",");
        action.speakers = StreamEx.of(resourceIds)
                .map(id -> adventure.getResource(SpeakerExternalResource.class, id).getWrappedObject())
                .toSet();
        return action;
    }

}
