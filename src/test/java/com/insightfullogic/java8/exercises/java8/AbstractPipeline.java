package com.insightfullogic.java8.exercises.java8;

abstract class AbstractPipeline<E_IN, E_OUT, S extends BaseStream<E_OUT, S>> 
		extends PipelineHelper<E_OUT> implements BaseStream<E_OUT, S>{

  AbstractPipeline(AbstractPipeline<?, E_IN, ?> previousStage, int opFlags) {
//        if (previousStage.linkedOrConsumed)
//            throw new IllegalStateException(MSG_STREAM_LINKED);
//        previousStage.linkedOrConsumed = true;
//        previousStage.nextStage = this;
//
//        this.previousStage = previousStage;
//        this.sourceOrOpFlags = opFlags & StreamOpFlag.OP_MASK;
//        this.combinedFlags = StreamOpFlag.combineOpFlags(opFlags, previousStage.combinedFlags);
//        this.sourceStage = previousStage.sourceStage;
//        if (opIsStateful())
//            sourceStage.sourceAnyStateful = true;
//        this.depth = previousStage.depth + 1;
    }
	abstract StreamShape getOutputShape();
	
	abstract boolean opIsStateful();
	
	abstract Sink<E_IN> opWrapSink(int flags, Sink<E_OUT> sink);
}
