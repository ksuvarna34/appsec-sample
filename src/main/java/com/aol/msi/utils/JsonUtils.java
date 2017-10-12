package com.aol.msi.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonUtils {

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	private static Logger logger = LoggerFactory.getLogger(JsonUtils.class);
	
	private JsonUtils(){}
	
	public static String toJsonString(Object object) {
		try {
			if(object==null) {
				return null;
			}
			return OBJECT_MAPPER.writeValueAsString(object);			
		} catch (Exception e) {
			logger .warn(e.getMessage(), e);
			return null;
		}
    }
       
	public static String toJsonStringNonNull(Object object) {
		try {
			if(object==null) {
				return null;
			}			
			OBJECT_MAPPER.setSerializationInclusion(Include.NON_NULL);
			return OBJECT_MAPPER.writeValueAsString(object);			
		} catch (Exception e) {
			logger .warn(e.getMessage(), e);
			return null;
		}
    }   
}
