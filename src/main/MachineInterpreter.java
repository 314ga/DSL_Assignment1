package main;

import main.metamodel.Machine;
import main.metamodel.State;
import main.metamodel.Transition;

import java.util.HashMap;
import java.util.Map;

public class MachineInterpreter {

	private State currentState; // runtime state
	private Map<String, Integer> intVariables = new HashMap<>();

	public void run(Machine m)
	{
		currentState = m.getInitialState();
			for (Map.Entry<String, Integer> entry : m.getVariables().entrySet()) {
				intVariables.put(entry.getKey(), 0);
		}
	}

	public State getCurrentState() {
		return currentState;
	}

	public void processEvent(String event) {

		for(Transition t: currentState.getTransitions()) {
			boolean condition = true;
				if(t.getEvent().equals(event))
				{
					if(t.isConditional())
					{
						if(t.isConditionEqual())
						{
							if(t.getConditionVariables(t.getConditionVariableName())==intVariables.get(t.getConditionVariableName()))
								currentState = t.getTarget();
							else
								condition = false;

						}

						else if(t.isConditionGreaterThan())
						{
							if(t.getConditionVariables(t.getConditionVariableName())<intVariables.get(t.getConditionVariableName()))
								currentState = t.getTarget();
							else
								condition = false;
						}
						else if(t.isConditionLessThan())
						{
							if(t.getConditionVariables(t.getConditionVariableName())>intVariables.get(t.getConditionVariableName()))
								currentState = t.getTarget();
							else
								condition = false;
						}
						else
							return;

					}
					if(condition)
					{
						if(t.hasOperation())
						{
							if(t.hasSetOperation())
								intVariables.put(t.getOperationVariableName(),t.getOperationValue(t.getOperationVariableName()));
							else if(t.hasIncrementOperation())
								intVariables.put(t.getOperationVariableName(), intVariables.get(t.getOperationVariableName()) + 1);
							else if(t.hasDecrementOperation())
								intVariables.put(t.getOperationVariableName(), intVariables.get(t.getOperationVariableName()) - 1);
							else
								break;

						}
						currentState = t.getTarget();
							return;
					}




				}
			}

		}

	public int getInteger(String name) {
		return intVariables.get(name);
	}

}
