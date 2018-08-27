package com.programyourhome.adventureroom.module.sparrenburcht.dsl.converters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import com.programyourhome.adventureroom.dsl.regex.MatchResult;
import com.programyourhome.adventureroom.model.Adventure;
import com.programyourhome.adventureroom.module.immerse.model.SpeakerExternalResource;
import com.programyourhome.adventureroom.module.sparrenburcht.model.SpeakAtSpeakersAction;

import one.util.streamex.StreamEx;

public class SpeakAtSpeakersActionConverter extends AbstractSparrenBurchtActionConverter<SpeakAtSpeakersAction> {

    @Override
    public Map<String, String> getRegexMap() {
        return this.createRegexes(
                "single", CHARACTER_ID + " says " + TEXT + " at speaker " + SPEAKER_ID,
                "multiple", CHARACTER_ID + " says " + TEXT + " at speakers " + SPEAKER_IDS,
                "all", CHARACTER_ID + " says " + TEXT + " at all speakers",
                "default", CHARACTER_ID + " says " + TEXT);
    }

    @Override
    public SpeakAtSpeakersAction convert(MatchResult matchResult, Adventure adventure) {
        SpeakAtSpeakersAction action = new SpeakAtSpeakersAction();
        action.character = adventure.getCharacter(matchResult.getValue(CHARACTER_ID));
        action.text = matchResult.getValue(TEXT);
        Collection<String> speakerIds = new ArrayList<>();
        if (matchResult.getRegexName().equals("single")) {
            speakerIds = Arrays.asList(matchResult.getValue(SPEAKER_ID));
        } else if (matchResult.getRegexName().equals("multiple")) {
            speakerIds = Arrays.asList(matchResult.getValue(SPEAKER_IDS).split(","));
        } else if (matchResult.getRegexName().equals("all")) {
            speakerIds = StreamEx.of(adventure.getExternalResources(SpeakerExternalResource.class))
                    .map(speaker -> "" + speaker.getId())
                    .toSet();
        }
        action.defaultSpeakers = matchResult.getRegexName().equals("default");
        action.speakerIds = speakerIds;
        return action;
    }

}
