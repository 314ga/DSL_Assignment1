package main.metamodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Machine {

	private List<State> states = new ArrayList<State>();
	private State initialState;
	private Map<String, Integer> intVariables = new HashMap<>();

	public List<State> getStates() {
		 return states;
	}

	public State getInitialState() {
		return  initialState;
	}

	public Map<String, Integer> getVariables() {
		return  intVariables;
	}

	public Machine(List<State> states, State initialState,Map<String, Integer> intVariables) {
		this.states = states;
		this.initialState = initialState;
		this.intVariables = intVariables;
	}

	public State getState(String string) {
		State state = states.stream()
				.filter(stateV -> string.equals(stateV.getName()))
				.findAny()
				.orElse(null);

		return state;
	}

	public int numberOfIntegers() {
		return intVariables.size();
	}

	public boolean hasInteger(String string) {

		return intVariables.containsKey(string);
	}

}
