package com.programyourhome.adventureroom.module.sparrenburcht.dsl.converters;

import java.util.Map;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import com.programyourhome.adventureroom.dsl.regex.MatchResult;
import com.programyourhome.adventureroom.model.Adventure;
import com.programyourhome.adventureroom.module.immerse.dsl.ImmerseAdventureModuleLexer;
import com.programyourhome.adventureroom.module.immerse.dsl.ImmerseAdventureModuleParser;
import com.programyourhome.adventureroom.module.immerse.dsl.ImmerseAdventureModuleParser.PlayAudioActionContext;
import com.programyourhome.adventureroom.module.immerse.dsl.converters.PlayAudioActionConverter;
import com.programyourhome.adventureroom.module.sparrenburcht.model.SpeakAtImmerseAction;

public class SpeakAtImmerseActionConverter extends AbstractSparrenBurchtActionConverter<SpeakAtImmerseAction> {

    @Override
    public Map<String, String> getRegexMap() {
        return this.createRegexes(DEFAULT, CHARACTER_ID + " says " + TEXT + REST_OF_THE_LINE);
    }

    @Override
    public SpeakAtImmerseAction convert(MatchResult matchResult, Adventure adventure) {
        SpeakAtImmerseAction action = new SpeakAtImmerseAction();
        action.character = adventure.getCharacter(matchResult.getValue(CHARACTER_ID));
        action.text = matchResult.getValue(TEXT);

        // Use the existing immerse module parser to get the whole detailed play configuration for free here!
        String playAudioActionLine = "play dummy.wav" + matchResult.getValue(REST_OF_THE_LINE);
        ImmerseAdventureModuleLexer lexer = new ImmerseAdventureModuleLexer(CharStreams.fromString(playAudioActionLine));
        ImmerseAdventureModuleParser parser = new ImmerseAdventureModuleParser(new CommonTokenStream(lexer));
        PlayAudioActionContext context = parser.playAudioAction();

        action.playAudioAction = new PlayAudioActionConverter().convert(context, adventure);
        return action;
    }

}
