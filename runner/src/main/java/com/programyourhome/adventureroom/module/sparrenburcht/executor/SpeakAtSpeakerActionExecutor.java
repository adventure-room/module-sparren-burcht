package com.programyourhome.adventureroom.module.sparrenburcht.executor;

import java.net.URL;
import java.util.Arrays;

import javax.sound.sampled.AudioInputStream;

import com.programyourhome.adventureroom.model.toolbox.DataStream;
import com.programyourhome.adventureroom.module.sparrenburcht.model.SpeakAtSpeakerAction;
import com.programyourhome.immerse.domain.format.ImmerseAudioFormat;
import com.programyourhome.iotadventure.runner.context.ExecutionContext;

public class SpeakAtSpeakerActionExecutor extends AbstractSparrenBurchtExecutor<SpeakAtSpeakerAction> {

    @Override
    public void execute(SpeakAtSpeakerAction action, ExecutionContext context) {
        AudioInputStream audioInputStream = this.getAmazonPolly(context).synthesizeText(action.character.voiceId, action.text);
        ImmerseAudioFormat format = ImmerseAudioFormat.fromJavaAudioFormat(audioInputStream.getFormat());
        DataStream dataStream = new DataStream(audioInputStream, "audio/pcm");
        URL url = this.getAmazonPollyModule(context).getToolbox().getDataStreamToUrl().exposeDataStream(dataStream);
        this.getImmerse(context).playAtSpeakers(url.toString(), format, Arrays.asList(action.speaker), false, true);
    }

}
