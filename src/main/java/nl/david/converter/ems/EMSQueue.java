package nl.david.converter.ems;

import java.util.ArrayList;
import java.util.List;


public class EMSQueue implements Comparable<EMSQueue>{
	private List<String> consumers;
	private List<String> providers;
	private String name;
	private EMSQueueProperty properties;
	private EMSServer server;
	
	private Boolean prefetchChanged;
	private Boolean maxRedeliveryChanged;
	private Boolean redeliveryDelayChanged;
	
	protected EMSQueue() {
		super();
		this.consumers = new ArrayList<String>();
		this.providers = new ArrayList<String>();
		this.properties = new EMSQueueProperty();
		
		this.prefetchChanged = false;
		this.maxRedeliveryChanged = false;
		this.redeliveryDelayChanged = false;
	}

	protected List<String> getConsumers() {
		return consumers;
	}

	protected void setConsumers(List<String> consumers) {
		this.consumers = consumers;
	}
	
	protected void setConsumers(String consumers) {
		this.consumers.add(consumers);
	}

	protected List<String> getProviders() {
		return providers;
	}

	protected void setProviders(List<String> providers) {
		this.providers = providers;
	}
	
	protected void setProviders(String providers) {
		this.providers.add(providers);
	}

	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	protected EMSQueueProperty getProperties() {
		return properties;
	}

	protected void setProperties(EMSQueueProperty properties) {
		this.properties = properties;
	}
	
	protected void setPropertiesSecure(Boolean secure) {
		this.properties.setSecure(secure);
	}
	
	protected void setPropertiesPersistent(Boolean persistent) {
		this.properties.setPersistent(persistent);
	}
	
	protected void setPropertiesPrefetch(String prefetch) {
		this.properties.setPrefetch(prefetch);
		this.prefetchChanged = true;
	}
	
	protected void setPropertiesMaxRedelivery(Integer maxRedelivery) {
		this.properties.setMaxRedelivery(maxRedelivery);
		this.maxRedeliveryChanged = true;
	}
	
	protected void setPropertiesRedeliveryDelay(Integer redeliveryDelay) {
		this.properties.setRedeliveryDelay(redeliveryDelay);
		this.redeliveryDelayChanged = true;
	}
	
	protected EMSServer getServer() {
		return server;
	}

	protected void setServer(EMSServer server) {
		this.server = server;
	}
	
	protected void setServer(String environment, String type) {
		this.server = new EMSServer(environment, type);
	}
	
	protected void printEMSQueue() {
		System.out.println(this.name);
		System.out.println("Providers: " + this.providers.toString());
		System.out.println("Consumers: " + this.consumers.toString());
		this.properties.printEMSQueueProperty();
		this.server.printEMSServer();
	}
	
	protected String createEMSQueue() {
		return "create queue " + this.name + " " + this.properties.printCreateProperties();
	}
	
	protected String createEMSGrantsProviders() {
		String result = "";
		for(int i=0 ; i<this.providers.size() ; i++) {
			String p = this.providers.get(i);				
			result += "grant queue " + this.name + " user=" + p.toString().replace("[", "").replace("]", "").replace(", ", ",") + " receive";
			if(i+1 < this.providers.size()) result += System.getProperty("line.separator");
		}
		return result;
	}
	
	protected String createEMSGrantsConsumers() {
		String result = "";
		for(int i=0 ; i<this.consumers.size() ; i++) {
				String c = this.consumers.get(i);				
				result += "grant queue " + this.name + " user=" + c.toString().replace("[", "").replace("]", "").replace(", ", ",") + " send";
				if(i+1 < this.consumers.size()) result += System.getProperty("line.separator");
			}
		return result;
	}
	
	protected String createQueueProperties() {
		String result = "";
		
		if(this.prefetchChanged) result += "addprop queue " + this.getName() + " prefetch=" + this.properties.getPrefetch() + System.getProperty("line.separator");
		if(this.maxRedeliveryChanged) result += "addprop queue " + this.getName() + " maxRedelivery=" + this.properties.getMaxRedelivery() + System.getProperty("line.separator");
		if(this.redeliveryDelayChanged) result += "addprop queue " + this.getName() + " redeliveryDelay=" + this.properties.getRedeliveryDelay() + System.getProperty("line.separator");
	
		return result;
	}
	
	protected void mergeEMSQueues(EMSQueue q) {
		this.consumers.addAll(q.consumers);
		this.providers.addAll(q.providers);
	}


	public int compareTo(EMSQueue q) {
		return this.name.compareTo(q.getName());
	}

}
