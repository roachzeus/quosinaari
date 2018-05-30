package fi.roachzeus.quosinaari;

import java.io.File;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.server.WrappedHttpSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

import fi.roachzeus.quosinaari.Error.ErrorView;
import fi.roachzeus.quosinaari.Host.HostView;
import fi.roachzeus.quosinaari.Player.PlayerView;


@Theme("qctheme")
public class QuosiUI extends UI {

	private static final long serialVersionUID = 1L;
	protected static Navigator nav;
	protected static final String MAINVIEW = "main";
	protected static final String HOSTVIEW = "host";
	protected static final String PLAYERVIEW = "player";

	@Override
    protected void init(VaadinRequest vaadinRequest) {
		
		this.getPage().setTitle("Example");
		this.nav = new Navigator(this, this);
		nav.addView("", new StartView());
		nav.addView(HOSTVIEW, new HostView());
		nav.addView(PLAYERVIEW, new PlayerView());
		
		// Session
		WrappedHttpSession ws = (WrappedHttpSession) vaadinRequest.getWrappedSession(true);
	    //HttpSession httpSession = ws.getHttpSession();
	    
	    //VaadinSession s = this.getSession();
	    
	    //s.setAttribute(type, value);
	    // maybe use this to identify users
		System.out.println("Session ID: " + ws.getId());
        
        // Finally set error view
        //this.nav.setErrorView(new ErrorView());
    }

    @WebServlet(urlPatterns = "/*", name = "QuosiUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = QuosiUI.class, productionMode = false)
    public static class QuosiUIServlet extends VaadinServlet {

		private static final long serialVersionUID = 1L;
    }
}
