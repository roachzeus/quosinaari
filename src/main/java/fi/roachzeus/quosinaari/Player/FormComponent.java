package fi.roachzeus.quosinaari.Player;

import java.util.ArrayList;

import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

import redis.clients.jedis.Jedis;

public class FormComponent extends CustomComponent{

	private static final long serialVersionUID = 2049937040068696255L;
	private VerticalLayout panelContent;
	private Panel panel;
	private Label question;
	private Button a, b, c, d;
	private Button ok, clear, start;
	private StringBuilder bob;
	private ArrayList<String> data;
	private long startTime, endTime;
	
	
	public FormComponent(){
		
		Jedis j = new Jedis("localhost");
		
		this.addStyleName("qForm");
		panel = new Panel("Kysymys");
        VerticalLayout panelContent = new VerticalLayout();
        panelContent.setStyleName("qFormInner");
        panelContent.setWidth("100%");
        panel.setContent(panelContent);
        
        clear = new Button("Clear");
        clear.addClickListener(e -> this.clear());
        clear.setWidth("40%");
        clear.setStyleName("margin5");
        
        start = new Button("Start");
        start.addClickListener(e -> this.start());
        start.setWidth("40%");
        start.setStyleName("margin5");
        
        data = this.getQuestionData();
        
		question = new Label(data.get(0));
		question.setStyleName("question");
		
		
		a = new Button("A: " + data.get(1));
		a.setStyleName("qLabel");
		a.addClickListener(e -> this.tap(e));
        a.setStyleName("selectBtn");
		
		b = new Button("B: " + data.get(2));
		b.setStyleName("qLabel");
		b.addClickListener(e -> this.tap(e));
        b.setStyleName("selectBtn");
		
		c = new Button("C: " + data.get(2));
		c.setStyleName("qLabel");
		c.addClickListener(e -> this.tap(e));
        c.setStyleName("selectBtn");
		
		d = new Button("D: " + data.get(3));
		d.setStyleName("qLabel");
		d.addClickListener(e -> this.tap(e));
        d.setStyleName("selectBtn");
		
		ok = new Button("V A L M I S !");
		ok.addClickListener(e -> this.submit());
		ok.setEnabled(false);
		ok.setWidth("100%");
		
		panelContent.addComponent(clear);
		panelContent.addComponent(start);
		panelContent.addComponent(question);
		panelContent.addComponent(a);
		panelContent.addComponent(b);
		panelContent.addComponent(c);
		panelContent.addComponent(d);
		panelContent.addComponent(ok);
		
		setCompositionRoot(panel);
        // Set the size as undefined at all levels
        panelContent.setSizeUndefined();
        panel.setSizeUndefined();
        // this is not needed for a Composite
        setSizeUndefined();
        
        bob = new StringBuilder();
        bob.setLength(0);
		
	}
	public void start(){
		
		startTime = System.currentTimeMillis();
		a.setCaption("A: " + data.get(1));
		b.setCaption("B: " + data.get(2));
		c.setCaption("C: " + data.get(3));
		d.setCaption("D: " + data.get(4));
		
	}
	
	private void clear(){
		a.setEnabled(true);
		b.setEnabled(true);
		c.setEnabled(true);
		d.setEnabled(true);
		bob.setLength(0);
		
		//System.out.println("");	
		
	}
	private void tap(Button.ClickEvent e){
		e.getComponent().setEnabled(false);
		
		switch (e.getComponent().getCaption().charAt(0)){
			case ('A'):
				bob.append("A");
				break;
			case ('B'):
				bob.append("B");
				break;
			case ('C'):
				bob.append("C");
				break;
			case ('D'):
				bob.append("D");
				break;
			default:
				//nada
				break;
		
		}
		System.out.println("bob: " + bob);
		if(bob.length() > 3 && bob.length() < 5){
			ok.setEnabled(true);
		}
		
	}
	private void submit(){
		
		// stuff todo...
		
		endTime = System.currentTimeMillis();
		//finally flush bob
		if(bob.equals(data.get(5))){
			
			//oikein
		}
		
		bob.setLength(0);
		ok.setEnabled(false);
		System.out.println("Time: "  + String.format("%.2f", (endTime-startTime)/1000.00d));
		
		// TODO: submit to redis
		
		
		
	}
	public void show(){
		this.setVisible(true);
	}
	public void hide(){
		this.setVisible(false);
	}
	private ArrayList<String> getQuestionData(){
		
		// TODO: this should come from redis
		ArrayList<String> list = new ArrayList<String>();
		
		list.add("Laita seuraavat asiat järjestykseen.");
		list.add("Vaihtoehto A");
		list.add("Vaihtoehto B");
		list.add("Vaihtoehto C");
		list.add("Vaihtoehto D");
		list.add("ABCD");
		
		return list;
		
	}

}
