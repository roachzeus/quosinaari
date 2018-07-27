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
	public static final String REDIS_HOST = "localhost";
	private Navigator n;
	private String playerId;
	private final Label h1;
	protected RegisterComponent r;
	protected FormComponent f;
	
	
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
        
        r = new RegisterComponent();
        f = new FormComponent();
        // show this after register
        f.hide();
        
        Runnable run = new Runnable() {
            public void run() {
            	boolean shouldRun = true;
                try {
                    while(shouldRun){
                    	Thread.sleep(1000);
                    	System.out.println("Lol, thread is running...");
                    	if(r.registerOk()){
                    		f.show();
                    		f.setVisible(true);
                    		shouldRun = false;
                    	}
                    }
                } catch (InterruptedException e) {
                    System.out.println(" interrupted");
                }
            }
         };
         new Thread(run).start();
        
        
        
        this.addComponent(r);
        this.addComponent(f);

        //setComponentAlignment(button, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        Notification.show("Welcome to PlayerView");
    }
    public void registerOK(){
    	
    	r.setVisible(false);
    	f.setVisible(true);
    }
}
	