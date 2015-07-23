package nl.david.converter.ems;

import java.util.ArrayList;
import java.util.List;


public class EMSQueueProperty {
	private Boolean secure;
	private String prefetch;
	private Boolean persistent;
	private Integer redeliveryDelay;
	private Integer maxRedelivery;
	
	protected EMSQueueProperty(Boolean secure, String prefetch, Boolean persistent, Integer redeliveryDelay, Integer maxRedelivery) {
		super();
		this.secure = secure;
		this.prefetch = prefetch;
		this.persistent = persistent;
		this.redeliveryDelay = redeliveryDelay;
		this.maxRedelivery = maxRedelivery;
	}

	protected EMSQueueProperty() {
		super();
		this.secure = true;
		this.prefetch = "5";
		this.persistent = false;
		this.redeliveryDelay = 0;
		this.maxRedelivery = 0;
	}

	protected Boolean getSecure() {
		return secure;
	}

	protected void setSecure(Boolean secure) {
		this.secure = secure;
	}

	protected String getPrefetch() {
		return prefetch;
	}

	protected void setPrefetch(String prefetch) {
		if(prefetch.equals("-1"))
			this.prefetch = "none";
		else
			this.prefetch = prefetch;
	}

	protected Boolean getPersistent() {
		return persistent;
	}

	protected void setPersistent(Boolean persistent) {
		this.persistent = persistent;
	}
	
	protected Integer getRedeliveryDelay() {
		return redeliveryDelay;
	}

	protected void setRedeliveryDelay(Integer redeliveryDelay) {
		this.redeliveryDelay = redeliveryDelay;
	}

	protected Integer getMaxRedelivery() {
		return maxRedelivery;
	}

	protected void setMaxRedelivery(Integer maxRedelivery) {
		this.maxRedelivery = maxRedelivery;
	}

	protected void printEMSQueueProperty() {
		String s = this.secure ? "secure" : "not secure";
		String p = this.persistent ? "persistent" : "not persistent";
		System.out.println("\t--> " + s);
		System.out.println("\t--> prefetch=" + this.prefetch);
		System.out.println("\t--> " + p);
	}
	
	protected String printCreateProperties() {
		List<String> s = new ArrayList<String>();
		if(this.secure) s.add("secure");		
		s.add("prefetch=" + this.prefetch);
		if(this.persistent)
			s.add("store=$sys.failsafe");
		else
			s.add("store=$sys.nonfailsafe");
		
		return s.toString().replace("[", "").replace("]", "").replace(", ", ",");
	}
	
}
