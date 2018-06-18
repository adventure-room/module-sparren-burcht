package com.programyourhome.adventureroom.sparrenburcht.model;

import com.programyourhome.adventureroom.amazonpolly.model.characters.PollyCharacter;
import com.programyourhome.adventureroom.model.script.action.Action;
import com.programyourhome.immerse.domain.speakers.Speaker;

public class SpeakAtSpeakerAction implements Action {

    public PollyCharacter character;
    public String text;
    public Speaker speaker;

}
