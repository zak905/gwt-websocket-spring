package com.gwidgets.client;



import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;
import com.gwidgets.places.*;


@WithTokenizers({HomePlace.Tokenizer.class, SendPlace.Tokenizer.class})
public interface MyHistoryMapper extends PlaceHistoryMapper  {

}

