package com.programyourhome.adventureroom.sparrenburcht.executor;

import com.programyourhome.adventureroom.amazonpolly.module.AmazonPollyAdventureModule;
import com.programyourhome.adventureroom.amazonpolly.service.AmazonPolly;
import com.programyourhome.adventureroom.immerse.module.ImmerseAdventureModule;
import com.programyourhome.adventureroom.immerse.service.Immerse;
import com.programyourhome.adventureroom.model.script.action.Action;
import com.programyourhome.adventureroom.philipshue.module.PhilipsHueAdventureModule;
import com.programyourhome.adventureroom.philipshue.service.PhilipsHue;
import com.programyourhome.adventureroom.sparrenburcht.module.SparrenBurchtAdventureModule;
import com.programyourhome.iotadventure.runner.action.executor.ActionExecutor;
import com.programyourhome.iotadventure.runner.context.ExecutionContext;

public abstract class AbstractSparrenBurchtExecutor<A extends Action> implements ActionExecutor<A> {

    protected SparrenBurchtAdventureModule getModule(ExecutionContext context) {
        return context.getModule(SparrenBurchtAdventureModule.ID);
    }

    protected PhilipsHueAdventureModule getPhilipsHueModule(ExecutionContext context) {
        return context.getModule(PhilipsHueAdventureModule.ID);
    }

    protected AmazonPollyAdventureModule getAmazonPollyModule(ExecutionContext context) {
        return context.getModule(AmazonPollyAdventureModule.ID);
    }

    protected ImmerseAdventureModule getImmerseModule(ExecutionContext context) {
        return context.getModule(ImmerseAdventureModule.ID);
    }

    protected PhilipsHue getPhilipsHue(ExecutionContext context) {
        return this.getPhilipsHueModule(context).getPhilipsHue();
    }

    protected AmazonPolly getAmazonPolly(ExecutionContext context) {
        return this.getAmazonPollyModule(context).getAmazonPolly();
    }

    protected Immerse getImmerse(ExecutionContext context) {
        return this.getImmerseModule(context).getImmerse();
    }

}
