package il.co.fmr.coord.service;

import il.co.fmr.coord.app.FaasException;
import il.co.fmr.coord.rest.FaasEvent;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class FaasEventMapper implements RowMapper<FaasEvent> {

	@Override
	public FaasEvent mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		FaasEvent event = new FaasEvent();
		event.setId(rs.getInt("ID"));
		event.setEventType(rs.getString("EVENT_TYPE"));
		event.setEventSource(rs.getString("EVENT_SOURCE"));
		event.setEventTime(rs.getDate("EVENT_TIME"));
		String json = rs.getString("DATA");
		try {
			event.setParams(JsonConverter.toMap(json));
		} catch (IOException e) {
			e.printStackTrace();
			throw new FaasException();
		}
		return (event);
	}
	
}
