package com.gxtcookbook.code.client.mvp;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.gxtcookbook.code.client.events.Action;

public class ActionsManager {
	private Map<String, Action> actions;

    public ActionsManager(){
        actions = new HashMap<String, Action>();
    }

    public Map<String, Action> getActions() {
        return actions;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public void buildActions(Map<String, Action> actns){
        Iterator iterator = actns.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Action> pair = (Map.Entry<String, Action>) iterator.next();
            actions.put((String) pair.getKey(), (Action) pair.getValue());
        }
    }

    private void setEnabled(boolean enable){
        //Iterator iterator = actions.entrySet().iterator();
        Set<Map.Entry<String, Action>> entrySet = actions.entrySet();
        Iterator<Map.Entry<String, Action>> iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Action> entry = iterator.next();
            Action actn = (Action) entry.getValue();
            actn.setEnabled(enable);
        }
    }

    public void perform(String actionKey){
        if(actions.containsKey(actionKey)){
            actions.get(actionKey).runAction();
        }
    }

    public void disableAll(){
        setEnabled(false);
    }

    public void enableAll(){
        setEnabled(true);
    }
}
