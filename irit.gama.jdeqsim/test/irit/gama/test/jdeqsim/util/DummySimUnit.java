/*******************************************************************************************************
*
* DummySimUnit.java, in plugin irit.gama.jdeqsim,
* is part of the source code of the GAMA modeling and simulation platform (v. 1.8.1)
*
* (c) 2007-2020 UMI 209 UMMISCO IRD/SU & Partners
*
* Visit https://github.com/gama-platform/gama for license information and contacts.
* 
********************************************************************************************************/

package irit.gama.test.jdeqsim.util;

import irit.gama.core.SchedulingUnit;
import irit.gama.core.message.Message;
import irit.gama.core.unit.Scheduler;
import msi.gama.runtime.IScope;

public class DummySimUnit extends SchedulingUnit {

	public DummySimUnit(IScope scope, Scheduler scheduler) {
		super(scope, null, scheduler);
	}

	public void handleMessage(Message m) {
	}

}
