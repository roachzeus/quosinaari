package fi.roachzeus.quosinaari;

import java.io.File;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;

import fi.roachzeus.quosinaari.utils.QuosiUtils;

import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

import redis.clients.jedis.Jedis;

public class StartView extends CssLayout implements View {
	
	private static final long serialVersionUID = 1153823238547034151L;
	private Navigator n;
	private String playerId;
	
    public StartView() {
    	
        setSizeFull();
        
        n = UI.getCurrent().getNavigator();
        this.setId("mainPage");
        playerId = UI.getCurrent().getSession().getSession().getId();
        
        //UI.getCurrent().getSession().getAttribute("");

        final Button hButton = new Button("Host");
        hButton.addStyleName("customButton");
        hButton.addClickListener(e -> {
        	
        	// check if host exists in redis. only one host allowed. check against sessionID.
        	if(QuosiUtils.isAllowedHost(playerId)){
            	n.navigateTo("host");
            }
        	
            
        });
        
        final Button pButton = new Button("Plr");
        pButton.addStyleName("customButton");
        pButton.addClickListener(e -> {
        	n.navigateTo("player");
        });
        
        // Find the application directory
        String basepath = VaadinService.getCurrent()
                          .getBaseDirectory().getAbsolutePath();

        // Image as a file resource
        FileResource resource = new FileResource(new File(basepath +
                                "/VAADIN/themes/qctheme/img/logo.png"));
        //System.out.println("basepath: " + basepath);

        // Show the image in the application
        Image image = new Image(null, resource);
        image.setResponsive(true);
        image.setHeight("250px");
        image.setWidth("250px");
        image.addStyleName("customLogo");
        
        
        this.addComponent(hButton,0);
        this.addComponent(image,1);
        
        this.addComponent(pButton,2);
        
        //setContent(this);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        Notification.show("Welcome to startview");
    }
}