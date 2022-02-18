package main;

import main.metamodel.Machine;
import main.metamodel.State;
import main.metamodel.Transition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StateMachine {

	private Map<String, State> states = new HashMap<>();
	private State current;
	private State initial;
	private String currentEvent;
	private Map<String, Integer> intVariables = new HashMap<>();

	private State getState(String name) {
		if(!states.containsKey(name)) states.put(name, new State(name));
		return states.get(name);
	}

	public Machine build() {
		return new Machine(new ArrayList<>(states.values()),initial,intVariables);
	}

	public StateMachine state(String name) {

		current = getState(name);
		return this;
	}

	public StateMachine initial() {
		initial = current;
		return this;
	}


	public StateMachine when(String event) {
		currentEvent = event;
		return this;
	}

	public StateMachine to(String name) {
		Transition t = new Transition(currentEvent,getState(name));
		current.addTransition(t);
		if(intVariables.size()>0)
			current.getTransitions().get(current.getTransitions().size()-1).setOperationValue(intVariables.keySet().toArray()[0].toString(),0);

		states.put(current.getName(), current);
		return this;
	}

	public StateMachine integer(String name) {
		intVariables.put(name,0);
		return this;
	}

	public StateMachine set(String name, int i) {
		intVariables.put(name,i);
		current.getTransitions().get(current.getTransitions().size()-1).setOperation("set");
		current.getTransitions().get(current.getTransitions().size()-1).setOperationValue(name,i);

		return this;
	}

	public StateMachine increment(String name) {
		intVariables.put(name,intVariables.get(name)+1);
		current.getTransitions().get(current.getTransitions().size()-1).setOperation("increment");
		return this;
	}

	public StateMachine decrement(String name) {
		intVariables.put(name,intVariables.get(name)-1);
		current.getTransitions().get(current.getTransitions().size()-1).setOperation("decrement");


		return this;
	}

	public StateMachine ifEquals(String name, int i) {
		current.getTransitions().get(current.getTransitions().size()-1).setConditionVariables(name,i);
		current.getTransitions().get(current.getTransitions().size()-1).setConditional("equal");

		return this;
	}

	public StateMachine ifGreaterThan(String name, int i) {
		current.getTransitions().get(current.getTransitions().size()-1).setConditionVariables(name,i);
		current.getTransitions().get(current.getTransitions().size()-1).setConditional("greater");

		return this;
	}

	public StateMachine ifLessThan(String name, int i) {
		current.getTransitions().get(current.getTransitions().size()-1).setConditionVariables(name,i);
		current.getTransitions().get(current.getTransitions().size()-1).setConditional("less");

		return this;
	}

}
