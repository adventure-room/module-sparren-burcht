package com.programyourhome.adventureroom.module.sparrenburcht.executor;

import java.net.URL;
import java.util.Collection;
import java.util.UUID;

import javax.sound.sampled.AudioInputStream;

import com.programyourhome.adventureroom.model.execution.ExecutionContext;
import com.programyourhome.adventureroom.model.toolbox.DataStream;
import com.programyourhome.adventureroom.module.immerse.model.SpeakerExternalResource;
import com.programyourhome.adventureroom.module.immerse.service.Immerse;
import com.programyourhome.adventureroom.module.sparrenburcht.model.SpeakAtSpeakersAction;
import com.programyourhome.immerse.domain.Scenario;
import com.programyourhome.immerse.domain.format.ImmerseAudioFormat;

import one.util.streamex.StreamEx;

public class SpeakAtSpeakersActionExecutor extends AbstractSparrenBurchtExecutor<SpeakAtSpeakersAction> {

    @Override
    public void execute(SpeakAtSpeakersAction action, ExecutionContext context) {
        Collection<String> speakerIdStrings;
        if (action.defaultSpeakers) {
            // TODO: util / list of variables used in certain modules
            String variableName = action.character + "-default-speakers";
            if (!context.isVariableDefined(variableName)) {
                System.out.println("WARN: no default speakers defined, using all speakers");
                speakerIdStrings = context.getAdventure().getExternalResourceMap(SpeakerExternalResource.class).keySet();
            } else {
                speakerIdStrings = context.getVariableValue(variableName);
            }
        } else {
            speakerIdStrings = action.speakerIds;
        }
        Collection<Integer> speakerIds = StreamEx.of(speakerIdStrings)
                .map(Integer::valueOf)
                .toList();

        AudioInputStream audioInputStream = this.getAmazonPolly(context).synthesizeText(action.character.voiceId, action.text);
        ImmerseAudioFormat format = ImmerseAudioFormat.fromJavaAudioFormat(audioInputStream.getFormat());
        DataStream dataStream = new DataStream(audioInputStream, "audio/pcm");
        URL url = this.getAmazonPollyModule(context).getToolbox().getDataStreamToUrl().exposeDataStream(dataStream);
        Immerse immerse = this.getImmerse(context);
        Scenario scenario = immerse.scenarioBuilder()
                .name("Speak at speakers")
                .description("Speaking the text '" + action.text + "' at some speakers")
                .urlWithFormat(url.toString(), format)
                .sourceAtSpeakers(speakerIds)
                .playOnce()
                .build();
        UUID playbackId = immerse.playScenario(scenario);
        immerse.waitForPlayback(playbackId);
    }

}
