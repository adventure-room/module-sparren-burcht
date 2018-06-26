package com.programyourhome.adventureroom.module.sparrenburcht.model;

import com.programyourhome.adventureroom.model.script.action.Action;
import com.programyourhome.adventureroom.module.amazonpolly.model.characters.PollyCharacter;
import com.programyourhome.immerse.domain.speakers.Speaker;

public class SpeakAtSpeakerAction implements Action {

    public PollyCharacter character;
    public String text;
    public Speaker speaker;

}
