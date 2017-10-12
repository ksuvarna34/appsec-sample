package com.krish.appsec.controller;

import java.beans.PropertyEditorSupport;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.util.HtmlUtils;
/**
 * Sanitize all the input content to Controllers from XSS attacks cleaning up HTML and scripting.
 * @author ksuvarna34
 *
 */
@ControllerAdvice
public class ContentControllerAdvice {
	/**
	 * Create Property Editor to clean up all input data
	 *
	 */
	public static class StringCleaner extends PropertyEditorSupport {
		@Override
		public void setAsText(String text) {
			if (text == null) {
				setValue(null);
			}
			else {
				String unescaped = Jsoup.clean(text, Whitelist.simpleText());
				setValue(HtmlUtils.htmlEscape(unescaped));
			}
		}
	}
	/**
	 * Register propertyEditor with WebDataBinder
	 * @param webDataBinder
	 */
	@InitBinder
	public void bindStringCleaner(WebDataBinder webDataBinder) {
		StringCleaner stringCleaner = new StringCleaner();
		webDataBinder.registerCustomEditor(String.class, stringCleaner);
	}
 
}
