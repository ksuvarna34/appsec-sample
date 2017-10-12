package com.krish.appsec;

import static org.jsoup.parser.Parser.unescapeEntities;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.web.util.HtmlUtils;

import com.aol.msi.utils.JsonUtils;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
/**
 * Sanitize all the output content to clients cleaning up HTML and scripting.
 * @author ksuvarna34
 *
 */
@JsonComponent
public class DefaultJsonSerializer 
  extends JsonSerializer<String> {

	public static final PolicyFactory POLICY_FACTORY = 
	    new HtmlPolicyBuilder()
	      .allowUrlProtocols("https")
	      .toFactory();
          
    private String cleanHtml(final String value){
        return Jsoup.clean(value, Whitelist.simpleText());
    }
    /**
     * Serialize the Objects Jackson serializer and clean up any scripting.
     */
	@Override
	public void serialize(String value, JsonGenerator gen,
			SerializerProvider serializers) throws IOException,
			JsonProcessingException {
		String originalWithUnescaped  = cleanHtml(JsonUtils.toJsonStringNonNull(value));
		String escapedHtml = HtmlUtils.htmlEscape(unescapeEntities(POLICY_FACTORY.sanitize(originalWithUnescaped), true));
        gen.writeRawValue(escapedHtml);
		
	}
}
