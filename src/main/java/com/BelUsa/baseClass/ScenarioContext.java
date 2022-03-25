package com.BelUsa.baseClass;

import java.util.HashMap;
import java.util.Map;

import com.BelUsa.enums.Context;



public class ScenarioContext{
	
	private  Map<String, Object> scenarioContext;
	 
    public ScenarioContext(){
        scenarioContext = new HashMap<>();
    }

    public void setContext(Context key, Object value) {
        scenarioContext.put(key.toString(), value);
    }

    public Object getContext(Context key){
        try {
            return scenarioContext.get(key.toString());
        }
        catch (NullPointerException e){
            System.out.println(key + " on scenario context is null\n"+ e.toString());
            return null;
        }
    }

    public Boolean isContains(Context key){
    	boolean flag = scenarioContext.containsKey(key.toString());
        return flag;
    }

    public void removeData(Context key){
        scenarioContext.remove(key.toString());
    }

}
