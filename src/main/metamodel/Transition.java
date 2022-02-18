package main.metamodel;

import java.util.HashMap;
import java.util.Map;

public class Transition {

	private String event;
	private State to;
	String operation;
	String conditional;
	private Map<String, Integer> intVariables = new HashMap<>();
	private Map<String, Integer> conditionVariables = new HashMap<>();
	public Transition(String event, State to) {

		this.event = event;
		this.to = to;

	}

	public String getEvent() {
		return event;
	}

	public State getTarget() {
		return to;
	}
	/********************************/
	public void setOperation(String operation)
	{
		this.operation = operation;
	}
	public void setConditional(String condition)
	{
		this.conditional = condition;
	}
	public void setOperationValue(String name,int value)
	{
		intVariables.put(name,value);
	}
	public void setConditionVariables(String name,int value)
	{
		conditionVariables.put(name,value);
	}
	public String getOperation()
	{
		return operation;
	}
	public String getConditional()
	{
		return conditional;
	}
	public int getOperationValue(String name)
	{
		return intVariables.get(name);
	}
	public int getConditionVariables(String name)
	{
		return conditionVariables.get(name);
	}
	/**********************************/
	public boolean hasSetOperation() {
			if(operation.equals("set"))
				return true;
			else
				return false;
	}

	public boolean hasIncrementOperation() {
		if(operation.equals("increment"))
			return true;
		else
			return false;
	}

	public boolean hasDecrementOperation() {
		if(operation.equals("decrement"))
			return true;
		else
			return false;
	}

	public String getOperationVariableName() {
		return intVariables.keySet().toArray()[0].toString();
	}

	public boolean isConditional() {
		if(conditional != null)
			return true;
		else
			return false;
	}

	public String getConditionVariableName() {
		return conditionVariables.keySet().toArray()[0].toString();
	}

	public Integer getConditionComparedValue() {
		return conditionVariables.get(getConditionVariableName());
	}

	public boolean isConditionEqual() {
		if(conditional.equals("equal"))
			return true;
		else
			return false;
	}

	public boolean isConditionGreaterThan() {
		if(conditional.equals("greater"))
			return true;
		else
			return false;
	}

	public boolean isConditionLessThan() {
		if(conditional.equals("less"))
			return true;
		else
			return false;
	}

	public boolean hasOperation() {
		if(operation != null)
			return true;
		else
			return false;
	}

}
