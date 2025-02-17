/*******************************************************************************************************
 *
 * Scheduler.java, in plugin irit.gama.jdeqsim, is part of the source code of the GAMA modeling and simulation
 * platform (v. 1.8.1)
 *
 * (c) 2007-2021 UMI 209 UMMISCO IRD/SU & Partners
 *
 * Visit https://github.com/gama-platform/gama for license information and contacts.
 *
 ********************************************************************************************************/

package irit.gama.core.unit;

import irit.gama.core.SkillUnit;
import irit.gama.core.message.Message;
import irit.gama.core.message.MessageQueue;
import msi.gama.metamodel.agent.IAgent;
import msi.gama.runtime.IScope;
import msi.gama.runtime.exceptions.GamaRuntimeException;
import msi.gama.util.GamaDate;

/**
 * The scheduler of the micro-simulation.
 *
 * @author rashid_waraich
 */
public class Scheduler extends SkillUnit {

	private GamaDate lastEventTime = null;
	protected final MessageQueue queue;

	/**
	 * If true execution is active
	 */
	private boolean executeActive = false;

	/**
	 * The last message executed
	 */
	private Message lastMessage = null;

	public Scheduler(IScope scope, IAgent relativeAgent) {
		super(scope, relativeAgent);
		this.queue = new MessageQueue();
		lastEventTime = scope.getSimulation().getClock().getCurrentDate();
	}

	public Message schedule(Message m) throws GamaRuntimeException {

		// Causality check
		if (executeActive) {
			if (lastMessage.isGreaterThan(m)) {
				throw GamaRuntimeException.warning("Past is not allowed (during execution)", scope);
			}
		} else {
			GamaDate simDate = scope.getSimulation().getClock().getCurrentDate();
			if (simDate.isGreaterThan(m.getMessageArrivalTime(), true)) {
				throw GamaRuntimeException.warning("Past is not allowed", scope);
			}
		}

		queue.putMessage(m);
		return m;
	}

	public void unschedule(Message m) {
		queue.removeMessage(m);
	}

	/**
	 * Execute the next messages
	 */
	public Object execute(IScope scope) throws GamaRuntimeException {
		executeActive = true;
		while (!queue.isEmpty() && isTimeReached(scope)) {
			// Execute message
			lastMessage = queue.getNextMessage();
			if (lastMessage != null) {
				lastMessage.handleMessage();
				lastEventTime = lastMessage.getMessageArrivalTime();
			}
		}
		// simTime = scope.getClock().getCurrentDate();
		executeActive = false;
		return true;
	}

	/**
	 * True if the time of the message event is reached
	 */
	public boolean isTimeReached(IScope scope) {
		return scope.getClock().getCurrentDate().isGreaterThan(queue.peek().getMessageArrivalTime(), false);
	}

	public GamaDate getLastEventTime() {
		return lastEventTime;
	}
}
