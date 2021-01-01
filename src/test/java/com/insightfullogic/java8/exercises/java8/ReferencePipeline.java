package com.insightfullogic.java8.exercises.java8;

abstract class ReferencePipeline<P_IN, P_OUT>
	extends AbstractPipeline<P_IN, P_OUT, Stream<P_OUT>>
	implements Stream<P_OUT>  {

	ReferencePipeline(AbstractPipeline<?, P_IN, ?> upstream, int opFlags) {
        super(upstream, opFlags);
    }
		
	  abstract static class StatelessOp<E_IN, E_OUT>
	  extends ReferencePipeline<E_IN, E_OUT> {
	  /**
	   * Construct a new Stream by appending a stateless intermediate
	   * operation to an existing stream.
	   *
	   * @param upstream The upstream pipeline stage
	   * @param inputShape The stream shape for the upstream pipeline stage
	   * @param opFlags Operation flags for the new stage
	   */
      StatelessOp(AbstractPipeline<?, E_IN, ?> upstream,
                  StreamShape inputShape,
                  int opFlags) {
          super(upstream, opFlags);
          assert upstream.getOutputShape() == inputShape;
      }

      @Override
      final boolean opIsStateful() {
          return false;
      }
  }
	  
	@Override
    public final Stream<P_OUT> filter(Predicate<? super P_OUT> predicate) {
		return new StatelessOp<P_OUT,P_OUT>(this, StreamShape.REFERENCE,3) {
		
			@Override
			Sink<P_OUT> opWrapSink(int flags, Sink<P_OUT> sink) {
				// TODO Auto-generated method stub
				return null;
			}
			
		};
    	
      
    }

    @Override
    final StreamShape getOutputShape() {
        return StreamShape.REFERENCE;
    }  
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
