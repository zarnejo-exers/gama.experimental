package ummisco.matlab.gama.matlabengine;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

import com.mathworks.engine.MatlabEngine;

import msi.gama.runtime.IScope;
import msi.gama.runtime.exceptions.GamaRuntimeException;
import ummisco.matlab.gama.utils.TypeConverter;

public class MatlabEngineInteraction {
	
	public static void eval(final IScope scope, final MatlabEngine engine, final String exp) {
		 try {
			 engine.eval(exp);			
		 } catch (CancellationException | InterruptedException | ExecutionException e) {
		    throw GamaRuntimeException.error("MATLAB evaluation failed", scope);
		 }		
	}
	
	
	public static Object getVariable(final IScope scope, final MatlabEngine engine,  final String variableName) {
		 Object output;

		 try {
			output = engine.getVariable(variableName);			
		 } catch (CancellationException | InterruptedException | ExecutionException e) {
		    throw GamaRuntimeException.error("MATLAB evaluation failed", scope);
		 }
		 
		 output = TypeConverter.Matlab2GamaType(scope, output);

		return output;		
	}	
	
}