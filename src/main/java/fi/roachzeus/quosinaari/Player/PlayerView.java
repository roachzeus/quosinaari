package fi.roachzeus.quosinaari.Player;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

import redis.clients.jedis.Jedis;

public class PlayerView extends CssLayout implements View{

	private static final long serialVersionUID = 1L;
	private Navigator n;
	private String playerId;
	private final Label h1;
	
	
	public PlayerView(){
		
		//this.setSizeFull();
		this.setWidth("100%");
		this.setHeight("100%");
		n = UI.getCurrent().getNavigator();
		playerId = UI.getCurrent().getSession().getSession().getId();
		h1 = new Label("Quosinaari");
		h1.addStyleName(ValoTheme.LABEL_H1);
		h1.setWidth("100%");
		
        
        this.addComponent(h1);
        
        RegisterComponent r = new RegisterComponent();
        FormComponent f = new FormComponent();
        
        
        
        this.addComponent(r);
        this.addComponent(f);

        //setComponentAlignment(button, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        Notification.show("Welcome to PlayerView");
    }
}
	