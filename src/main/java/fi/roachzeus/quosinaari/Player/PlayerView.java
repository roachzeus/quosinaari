package fi.roachzeus.quosinaari.Player;

import java.util.HashMap;
import java.util.Map;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.WrappedHttpSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

import redis.clients.jedis.Jedis;

public class PlayerView extends CssLayout implements View{

	private static final long serialVersionUID = 1L;
	private Navigator n;
	private Jedis pj;
	private String playerId;
	private Map<String, String> playerMap;
	
	public PlayerView(){
		
		this.setSizeFull();
		n = UI.getCurrent().getNavigator();
		pj = new Jedis("localhost");
		playerId = UI.getCurrent().getSession().getSession().getId();
		playerMap = new HashMap<String, String>();
		//System.out.println("View getSessionId: " + playerId);
		
		TextField msg = new TextField();
		
		// add toHost button
		Button toHost = new Button("Go to HostView",
                new Button.ClickListener() {
			
				private static final long serialVersionUID = 1L;

			@Override
            public void buttonClick(ClickEvent event) {
                
				n.navigateTo("host");
				
            }

			
        });
        this.addComponent(toHost);
        
        
        // click me button
        Button addMsg = new Button("click me",
        		
        		new Button.ClickListener() {
			
			private static final long serialVersionUID = 1L;

		@Override
        public void buttonClick(ClickEvent event) {
            
			if(msg.getValue().toString().length() < 1){ return;}
			
			// write something
			pj.clientSetname(playerId);
			//System.out.println("textfield: " + msg.getValue().toString());
			//playerMap.put("name", msg.getValue().toString());
			//playerMap.put("id", playerId);
			//hmset: String key, Map<String, String> hash
			pj.hset("Player#" + playerId, "name", msg.getValue().toString());
			pj.hset("Player#" + playerId, "id", playerId);
			
			//System.out.println("List: " + list);
			
			
			System.out.println("get all for ID: " + pj.hgetAll("Player#" + playerId));
			
        }
        });
        this.addComponent(addMsg);
        
        
        this.addComponent(msg);
        
        
        
        
        //setComponentAlignment(button, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        Notification.show("Welcome to PlayerView");
    }
}
	