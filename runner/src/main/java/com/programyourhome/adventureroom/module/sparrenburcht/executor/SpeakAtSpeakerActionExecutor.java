package com.programyourhome.adventureroom.module.sparrenburcht.executor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import com.programyourhome.adventureroom.amazonpolly.service.model.PollyResult;
import com.programyourhome.adventureroom.module.sparrenburcht.model.SpeakAtSpeakerAction;
import com.programyourhome.adventureroom.module.sparrenburcht.util.WaveUtil;
import com.programyourhome.iotadventure.runner.context.ExecutionContext;

public class SpeakAtSpeakerActionExecutor extends AbstractSparrenBurchtExecutor<SpeakAtSpeakerAction> {

    @Override
    public void execute(SpeakAtSpeakerAction action, ExecutionContext context) {
        PollyResult pollyResult = this.getAmazonPolly(context).synthesizeText(action.character.voiceId, action.text);
        File cacheFile = this.cacheLocally(pollyResult);
        this.getImmerse(context).playAtSpeakers("http://localhost:19161/audio/" +
                cacheFile.getName().substring(0, cacheFile.getName().length() - 4), Arrays.asList(action.speaker), false, true);
    }

    private File cacheLocally(PollyResult pollyResult) {
        try {
            // TODO: make configurable
            File cacheFile = new File("/home/emulder/Downloads/audio/audio" + new Random().nextLong() + ".wav");
            WaveUtil.writeWave(new FileOutputStream(cacheFile), pollyResult.audioFormat, pollyResult.inputStream);
            return cacheFile;
        } catch (IOException e) {
            throw new IllegalStateException("IOException during local caching", e);
        }
    }

}
