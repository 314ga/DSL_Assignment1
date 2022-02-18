package main.metamodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class State {

	private String name;
	private List<Transition> trans = new ArrayList<>();

	public State(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public void addTransition(Transition t) {
		this.trans.add(t);
	}

	public List<Transition> getTransitions() {
		return trans;
	}

	public Transition getTransitionByEvent(String name) {
		Transition transitionByEvent=null;
		for (int i=0; i< trans.size(); i++){
			if(trans.get(i).getEvent().equals(name)){
				transitionByEvent=trans.get(i);

			}
		}
		return transitionByEvent;
	}

}
