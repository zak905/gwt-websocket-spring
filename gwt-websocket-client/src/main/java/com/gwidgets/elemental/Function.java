package com.gwidgets.elemental;


import com.google.gwt.core.client.JavaScriptObject;
import jsinterop.annotations.JsFunction;


@JsFunction
public interface Function{
	
	public JavaScriptObject call(JavaScriptObject event);

}
