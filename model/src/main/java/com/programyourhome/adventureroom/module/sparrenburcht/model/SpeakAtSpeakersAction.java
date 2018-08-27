package com.programyourhome.adventureroom.module.sparrenburcht.model;

import java.util.Collection;

import com.programyourhome.adventureroom.model.script.action.Action;
import com.programyourhome.adventureroom.module.amazonpolly.model.characters.PollyCharacter;

public class SpeakAtSpeakersAction implements Action {

    public PollyCharacter character;
    public String text;
    public boolean defaultSpeakers;
    // If defaultSpeakers is false, this must be filled with the specific speaker ids
    public Collection<String> speakerIds;

}
