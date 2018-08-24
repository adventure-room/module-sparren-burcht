package com.programyourhome.adventureroom.module.sparrenburcht.executor;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;

import com.programyourhome.adventureroom.model.toolbox.DataStream;
import com.programyourhome.adventureroom.module.sparrenburcht.model.SpeakAtSpeakersAction;
import com.programyourhome.immerse.domain.format.ImmerseAudioFormat;
import com.programyourhome.iotadventure.runner.context.ExecutionContext;

public class SpeakAtSpeakersActionExecutor extends AbstractSparrenBurchtExecutor<SpeakAtSpeakersAction> {

    @Override
    public void execute(SpeakAtSpeakersAction action, ExecutionContext context) {
        AudioInputStream audioInputStream = this.getAmazonPolly(context).synthesizeText(action.character.voiceId, action.text);
        ImmerseAudioFormat format = ImmerseAudioFormat.fromJavaAudioFormat(audioInputStream.getFormat());
        DataStream dataStream = new DataStream(audioInputStream, "audio/pcm");
        URL url = this.getAmazonPollyModule(context).getToolbox().getDataStreamToUrl().exposeDataStream(dataStream);
        this.getImmerse(context).playAtSpeakers(url.toString(), format, action.speakers, false, true);
    }

}
