package com.programyourhome.adventureroom.module.sparrenburcht.executor;

import java.net.URL;
import java.util.Optional;

import javax.sound.sampled.AudioInputStream;

import com.programyourhome.adventureroom.model.execution.ExecutionContext;
import com.programyourhome.adventureroom.model.toolbox.DataStream;
import com.programyourhome.adventureroom.module.immerse.executor.PlayAudioActionExecutor;
import com.programyourhome.adventureroom.module.immerse.model.PlayAudioAction.Resource;
import com.programyourhome.adventureroom.module.immerse.model.PlayAudioAction.UrlResource;
import com.programyourhome.adventureroom.module.sparrenburcht.model.SpeakAtImmerseAction;
import com.programyourhome.immerse.domain.format.ImmerseAudioFormat;

public class SpeakAtImmerseActionExecutor extends AbstractSparrenBurchtExecutor<SpeakAtImmerseAction> {

    @Override
    public void execute(SpeakAtImmerseAction action, ExecutionContext context) {
        AudioInputStream audioInputStream = this.getAmazonPolly(context).synthesizeText(action.character.voiceId, action.text);
        ImmerseAudioFormat format = ImmerseAudioFormat.fromJavaAudioFormat(audioInputStream.getFormat());
        DataStream dataStream = new DataStream(audioInputStream, "audio/pcm");
        URL url = this.getAmazonPollyModule(context).getToolbox().getDataStreamToUrl().exposeDataStream(dataStream);

        // Overwrite the (dummy) resource in the play audio action with the URL from Polly.
        UrlResource urlResource = new UrlResource();
        urlResource.urlString = url.toString();
        urlResource.audioFormat = Optional.of(format);
        action.playAudioAction.resource = Resource.url(urlResource);

        // Use the executor of the Immerse module to handle the configuration of Immerse and trigger the playback.
        PlayAudioActionExecutor executor = new PlayAudioActionExecutor();
        executor.execute(action.playAudioAction, context);
    }

}
