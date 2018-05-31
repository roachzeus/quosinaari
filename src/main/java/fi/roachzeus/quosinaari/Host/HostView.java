package fi.roachzeus.quosinaari.Host;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

import fi.roachzeus.quosinaari.utils.QuosiUtils;
import redis.clients.jedis.Jedis;

public class HostView extends CssLayout implements View{

	private static final long serialVersionUID = 1L;
	private Navigator n;
	private String playerId;
	
	public HostView(){
		
		this.setSizeFull();
		n = UI.getCurrent().getNavigator();
		playerId = UI.getCurrent().getSession().getSession().getId();
		
		Jedis j = new Jedis("localhost");
		j.set("host", playerId);
		j.close();
		
		
		
		Button testRedis = new Button("Get data", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;
			
			@Override
			public void buttonClick(ClickEvent event) {
				// get known players
				Jedis j = new Jedis("localhost");
				//System.out.println("");
				j.clientSetname("host");
				Set<String> keyz = j.keys("Player*");
				//Map<String, String> fields = j.hgetAll("Player#");
				System.out.println("Set: " + keyz);
				for(String k : keyz){
					System.out.println("Found key: " + k);
					
				}
				j.close();
				
			}
		});
		
		
		addComponent(testRedis);
        //setComponentAlignment(button, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // we got so far, now set host key to redis
        if(!QuosiUtils.isAllowedHost(playerId)){
        	n.navigateTo("main");
        }
        Notification.show("Welcome to HostView");
    }
}
	