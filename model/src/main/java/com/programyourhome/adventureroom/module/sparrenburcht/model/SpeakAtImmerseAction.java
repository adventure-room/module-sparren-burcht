package com.programyourhome.adventureroom.module.sparrenburcht.model;

import com.programyourhome.adventureroom.model.script.action.Action;
import com.programyourhome.adventureroom.module.amazonpolly.model.characters.PollyCharacter;
import com.programyourhome.adventureroom.module.immerse.model.PlayAudioAction;

public class SpeakAtImmerseAction implements Action {

    public PollyCharacter character;
    public String text;
    public PlayAudioAction playAudioAction;

}
