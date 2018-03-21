package fi.roachzeus.quosinaari;

import java.io.File;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;


@Theme("qctheme")
public class QuosiUI extends UI {

	private static final long serialVersionUID = 1L;

	@Override
    protected void init(VaadinRequest vaadinRequest) {
        final GridLayout layout = new GridLayout(3, 1);
        layout.setSpacing(false);
        layout.setWidth("99%");
        layout.setHeight("99%");

        final Button hButton = new Button("Host");
        hButton.addStyleName("customButton");
        hButton.addClickListener(e -> {
            layout.addComponent(new Label("Pressed host"));
        });
        
        final Button pButton = new Button("Plr");
        pButton.addStyleName("customButton");
        pButton.addClickListener(e -> {
        	layout.addComponent(new Label("Pressed plr"));
        });
        
        // Find the application directory
        String basepath = VaadinService.getCurrent()
                          .getBaseDirectory().getAbsolutePath();

        // Image as a file resource
        FileResource resource = new FileResource(new File(basepath +
                                "/VAADIN/themes/qctheme/img/logo.png"));
        System.out.println("basepath: " + basepath);

        // Show the image in the application
        Image image = new Image(null, resource);
        image.setResponsive(true);
        image.setHeight("250px");
        image.setWidth("250px");
        image.addStyleName("customLogo");
        
        
        layout.addComponent(hButton,0,0);
        layout.addComponent(image,1,0);
        
        layout.addComponent(pButton,2,0);
        
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "QuosiUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = QuosiUI.class, productionMode = false)
    public static class QuosiUIServlet extends VaadinServlet {

		private static final long serialVersionUID = 1L;
    }
}
