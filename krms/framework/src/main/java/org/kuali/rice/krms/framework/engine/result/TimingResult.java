package org.kuali.rice.krms.framework.engine.result;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.EventObject;
import java.util.Map;

import org.joda.time.DateTime;
import org.kuali.rice.krms.api.engine.ExecutionEnvironment;
import org.kuali.rice.krms.api.engine.ResultEvent;

public class TimingResult extends EventObject implements ResultEvent {
    
	private static final long serialVersionUID = 5335636381355236617L;
	
    private String type;
	private DateTime start;
	private DateTime end;
	private ExecutionEnvironment environment;
	private String description;
	private Map<String, ?> resultDetails;

	public TimingResult(String description, String type, Object source, ExecutionEnvironment environment, DateTime start, DateTime end){
		super(source);
		this.type = type;
		this.environment = environment;
		this.start = start;
		this.end = end;
		this.description = description;
	}
	
	public TimingResult(String type, Object source, ExecutionEnvironment environment, DateTime start, DateTime end){
		super(source);
		this.type = type;
		this.environment = environment;
		this.start = start;
		this.end = end;
	}
	
	public Long getElapsedTimeInMilliseconds(){
		return Long.valueOf(end.getMillis() - start.getMillis());
	}
	
	public ExecutionEnvironment getEnvironment(){
		return environment;
	};
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss.SSS");
		sb.append(df.format(end));
		sb.append(" EventType: "+ type);
		sb.append(" (Start = " + df.format(start));
		sb.append(", End = " + df.format(end));
		sb.append(",  Elapsed Time = "+ getElapsedTimeInMilliseconds().toString());
		sb.append(" milliseconds.)");
		return sb.toString();
	}

	@Override
	public Boolean getResult() {
		return null;
	}

	@Override
	public DateTime getTimestamp() {
		return end;
	}

	@Override
	public String getType() {
		return type;
	}
	
	@Override
	public Map<String, ?> getResultDetails() {
	    if (resultDetails == null) {
	        return Collections.emptyMap();
	    } else {
	        return Collections.unmodifiableMap(resultDetails);
	    }
	}
	
	@Override
	public String getDescription() {
		return description;
	}
}
