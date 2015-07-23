package nl.david.converter.ems;

public class EMSServer {
	private String environment;
	private String name;
	private String type;
	
	protected EMSServer(String environment, String type) {
		this.environment = environment;
		this.type = type;
		
		if(environment.equals("D") && type.equals("ESB_SMALL")) {
			this.name = "tcp://DEVESBSMALLDC1.insim.biz:37222";
		}
		if(environment.equals("D") && type.equals("ESB_LARGE")) {
			this.name = "tcp://DEVESBLARGEDC1.insim.biz:37222";
		}
		if(environment.equals("D") && type.equals("P2P_SMALL")) {
			this.name = "tcp://DEVP2PSMALLDC1.insim.biz:37222";
		}
		if(environment.equals("D") && type.equals("P2P_LARGE")) {
			this.name = "tcp://DEVP2PLARGEDC1.insim.biz:37222";
		}
		
		if(environment.equals("T") && type.equals("ESB_SMALL")) {
			this.name = "tcp://TSTESBSMALLDC1.insim.biz:37222";
		}
		if(environment.equals("T") && type.equals("ESB_LARGE")) {
			this.name = "tcp://TSTESBLARGEDC1.insim.biz:37222";
		}
		if(environment.equals("T") && type.equals("P2P_SMALL")) {
			this.name = "tcp://TSTP2PSMALLDC1.insim.biz:37222";
		}
		if(environment.equals("T") && type.equals("P2P_LARGE")) {
			this.name = "tcp://TSTP2PLARGEDC1.insim.biz:37222";
		}
		
		if(environment.equals("A") && type.equals("ESB_SMALL")) {
			this.name = "tcp://ACCESBSMALLDC1.insim.biz:37222,tcp://ACCESBSMALLDC2.insim.biz:37222";
		}
		if(environment.equals("A") && type.equals("ESB_LARGE")) {
			this.name = "tcp://ACCESBLARGEDC1.insim.biz:37222,tcp://ACCESBLARGEDC2.insim.biz:37222";
		}
		if(environment.equals("A") && type.equals("P2P_SMALL")) {
			this.name = "tcp://ACCP2PSMALLDC1.insim.biz:37222,tcp://ACCP2PSMALLDC2.insim.biz:37222";
		}
		if(environment.equals("A") && type.equals("P2P_LARGE")) {
			this.name = "tcp://ACCP2PLARGEDC1.insim.biz:37222,tcp://ACCP2PLARGEDC2.insim.biz:37222";
		}
		
		if(environment.equals("P") && type.equals("ESB_SMALL")) {
			this.name = "tcp://PRDESBSMALLDC1.insim.biz:37222";
		}
		if(environment.equals("P") && type.equals("ESB_LARGE")) {
			this.name = "tcp://PRDESBLARGEDC1.insim.biz:37222";
		}
		if(environment.equals("P") && type.equals("P2P_SMALL")) {
			this.name = "tcp://PRDP2PSMALLDC1.insim.biz:37222";
		}
		if(environment.equals("P") && type.equals("P2P_LARGE")) {
			this.name = "tcp://PRDP2PLARGEDC1.insim.biz:37222";
		}
	}

	protected String getEnvironment() {
		return environment;
	}

	protected void setEnvironment(String environment) {
		this.environment = environment;
	}

	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	protected String getType() {
		return type;
	}

	protected void setType(String type) {
		this.type = type;
	}
	
	protected void printEMSServer() {
		System.out.println("\t--> " + this.name);
	}
	
}
