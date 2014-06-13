package com.atos.rental.rcp;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {
	private IAction preferencesAction;

    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
    }

    protected void makeActions(IWorkbenchWindow window) {
    	{
    		preferencesAction = ActionFactory.PREFERENCES.create(window);
    		register(preferencesAction);
    	}
    }

    protected void fillMenuBar(IMenuManager menuBar) {
    	menuBar.add(preferencesAction);
    }
    
}
