package com.gwidgets.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class SendPlace extends Place{
	

	private String name;

	public SendPlace(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Prefix("!")
	 public static class Tokenizer implements PlaceTokenizer<SendPlace> {
	        @Override
	        public String getToken(SendPlace place) {
	            return place.getName();
	        }

	        @Override
	        public SendPlace getPlace(String token) {
	            return new SendPlace(token);
	        }
	    }

}
