package fi.roachzeus.quosinaari.Player;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

public class PlayerView extends CssLayout implements View{

	private static final long serialVersionUID = 1L;
	private Navigator n;
	
	public PlayerView(){
		
		this.setSizeFull();
		n = UI.getCurrent().getNavigator();
		Button button = new Button("Go to HostView",
                new Button.ClickListener() {
			
				private static final long serialVersionUID = 1L;

			@Override
            public void buttonClick(ClickEvent event) {
                
				n.navigateTo("host");
				
            }

			
        });
        addComponent(button);
        //setComponentAlignment(button, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        Notification.show("Welcome to PlayerView");
    }
}
	