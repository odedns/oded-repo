
package il.co.fmr.coord.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author oded
 *
 */

public class JsonConverter {

	
	private JsonConverter()
	{
		
	}
	
	/**
	 * Convert from map to json string
	 * @param map representing key value.
	 * @return json string
	 * @throws JsonProcessingException in case of error.
	 */
	public static String fromMap(Map<String,String> map) throws JsonProcessingException
	{
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(map);
		return(json);
	}

	
	/**
	 * convert from json to map
	 * @param json string containing json
	 * @return Map<String,String>
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException in case of error.
	 */
	public static Map<String,String> toMap(String json) throws JsonParseException, JsonMappingException, IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<HashMap<String, String>> typeRef = new TypeReference<HashMap<String,String>>() {};
		HashMap<String,String> map = mapper.readValue(json,typeRef);
		return(map);
	}
}	
	
	
	
	
	


