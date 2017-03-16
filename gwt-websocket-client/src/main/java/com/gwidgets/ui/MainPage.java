package com.gwidgets.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.gwidgets.elemental.MessageEvent;
import com.gwidgets.elemental.WebSocket;
import com.gwidgets.places.HomePlace;
import com.gwidgets.places.SendPlace;
import com.vaadin.polymer.Polymer;
import com.vaadin.polymer.elemental.EventListener;
import com.vaadin.polymer.iron.IronPagesElement;
import com.vaadin.polymer.paper.PaperButtonElement;
import com.vaadin.polymer.paper.PaperDrawerPanelElement;
import com.vaadin.polymer.paper.PaperInputElement;
import com.vaadin.polymer.paper.PaperMaterialElement;
import com.vaadin.polymer.paper.PaperMenuElement;
import com.vaadin.polymer.paper.PaperToastElement;

public class MainPage extends Composite {	
	
	@UiField
    PaperMenuElement  paperMenu;
	@UiField
    AnchorElement homeLink;
	@UiField
    AnchorElement sendLink;

	@UiField
	IronPagesElement ironPages;
	@UiField
	PaperMaterialElement homePanel;
	@UiField
	PaperToastElement notificationsToast;
	@UiField
	PaperInputElement messageInput;
	@UiField
	PaperButtonElement sendButton;
	
	
    public WebSocket socket;


	private static MainPageUiBinder uiBinder = GWT
			.create(MainPageUiBinder.class);

	interface MainPageUiBinder extends UiBinder<Widget, MainPage> {
	}

	//main page constructor
	public MainPage() {
		initWidget(uiBinder.createAndBindUi(this));
		Polymer.endLoading(this.getElement(), (Element) paperMenu);
		socket = new WebSocket("ws://localhost:8082/gwidgets-ws");
		
		socket.onmessage = (evt) -> {
			   MessageEvent event = evt.cast();
			   DivElement div = DOM.createDiv().cast();
			   div.setInnerText(event.getData());
			   homePanel.appendChild(div);
			   notificationsToast.toggle();
			return evt;
		};
		
		socket.onopen = (evt) -> {  
			   GWT.log("socket open");
			return evt;
		};
		
		socket.onclose = (evt) -> {
			   GWT.log("socket closed");
			return evt;
		};
	}
	
	
	public void initializeEvents(PlaceController controller){
		Event.sinkEvents(sendLink, Event.ONCLICK);
		 Event.sinkEvents(homeLink, Event.ONCLICK);
		 
		 sendButton.addEventListener("click", new EventListener(){
			@Override
			public void handleEvent(com.vaadin.polymer.elemental.Event event) {
				socket.send(messageInput.getTextContent()+"");
			}
		 });
		 
		 Event.setEventListener(homeLink, e -> {
	            if(Event.ONCLICK == e.getTypeInt()) {
	            	ironPages.select("home");
	            	paperMenu.select("home");
	           	controller.goTo(new HomePlace("home"));
	           	slideDrawerIfMobile();
	            }  
	        });
		 
		 Event.setEventListener(sendLink, e -> {
            if(Event.ONCLICK == e.getTypeInt()) {
           	 ironPages.select("users");
           	 paperMenu.select("users");
           	       controller.goTo(new SendPlace("send"));
           	    slideDrawerIfMobile();
           	    
            }  
   });
		
		
	}
	
	
	
	public AnchorElement getHomeLink(){
		return homeLink;
		
	}
	
	public AnchorElement getUsersLink(){
		return sendLink;
		
	}
	
	public PaperMenuElement getPaperMenu(){
		return paperMenu;
	}
	
	public IronPagesElement getIronPagesElement(){
		return ironPages;
		
	}
	
	public interface Presenter {
		//handles clicking on back and forward buttons of the browser
		public void placeChangeWithoutClickEvent(String placeName);
	}
	
	private void slideDrawerIfMobile(){
		if( Window.getClientWidth() < 600 ){
			PaperDrawerPanelElement drawer = (PaperDrawerPanelElement) Polymer.getDocument().getElementById("paperDrawerPanel");
			drawer.closeDrawer();
		}
		
	}

}
