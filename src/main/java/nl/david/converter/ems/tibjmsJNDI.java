package nl.david.converter.ems;/*
 * Copyright (c) 2001-$Date: 2007-10-01 14:55:08 -0700 (Mon, 01 Oct 2007) $ TIBCO Software Inc. 
 * All rights reserved.
 * For more information, please contact:
 * TIBCO Software Inc., Palo Alto, California, USA
 * 
 * $Id: tibjmsJNDI.java 33862 2007-10-01 21:55:08Z rkutter $
 * 
 */

/*
 * Example of how to use JNDI to lookup administered objects
 * from a TIBCO Enterprise Message Service client.
 *
 * There are four types of administered objects:
 *   - TopicConnectionFactory
 *   - QueueConnectionFactory
 *   - Topic
 *   - Queue
 *
 * TIBCO Enterprise Message Service provides the ability to lookup
 * those administered objects using the TIBCO Enterprise Message Service
 * server as a JNDI provider.
 *
 * Note that TIBCO Enterprise Message Service's JNDI interface only
 * allows lookup of static topics and queues. Static topics and queues are
 * those which have direct entries in the topics and queues configuration
 * files. On the contrary, dynamic topics and queues are created
 * by the applications using wildcard matching and the rules of
 * properties inheritance and do not have explicit entries in the
 * configuration files.
 *
 * Also note that JNDI lookup of topics and queues may fail if
 * the configuration contains a topic and a queue with the same name.
 * In this case the application should use name qualifiers such as
 * $topics and $queues as to qualify as demonstrated by this sample.
 *
 * This sample assumes the use of the original sample configuration
 * files distributed with TIBCO Enterprise Message Service software. If the
 * configuration is altered this sample may not work as expected.
 *
 *
 * Usage:  java tibjmsJNDI  [-provider <providerUrl>]
 *
 *    where options are:
 *
 *      -provider   Provider URL used for JNDI access
 *                  to administered objects.
 *                  If not specified this sample assumes a
 *                  providerUrl of "tibjmsnaming://localhost:7222"
 *
 */

import javax.naming.*;
import java.util.*;


public class tibjmsJNDI
{
    static final String  providerContextFactory =
                            "com.tibco.tibjms.naming.TibjmsInitialContextFactory";


    String      providerUrl;
    String      userName;
    String      password;

    protected InitialContext initialize(String provider, String user, String passwd) {
    	this.providerUrl = provider;
    	this.userName = user;
    	this.password = passwd;
    	
        System.out.println("Connecting to server: " + providerUrl);

        try
        {
            /*
             * Init JNDI Context.
             */
            Hashtable env = new Hashtable();
            env.put(Context.INITIAL_CONTEXT_FACTORY, providerContextFactory);
            env.put(Context.PROVIDER_URL, this.providerUrl);
            env.put(Context.SECURITY_PRINCIPAL, this.userName);
            env.put(Context.SECURITY_CREDENTIALS, this.password);

            return new InitialContext(env);
        }
        
        catch(NamingException e)
        {
            e.printStackTrace();
        }
		return null;
    }
    
    protected boolean checkQueue(String queue, InitialContext jndiContext) throws NamingException {
    	/*
         * Lookup queue 'queue.sample' which must exist in the
         * queues configuration file, if not successful then it
         * does not exist...
         */
        javax.jms.Queue q = null;

        try
        {
            q = (javax.jms.Queue)jndiContext.lookup(queue);
            return true;
        }
        catch(NamingException e)
        {
        	if(e.getMessage().startsWith("Name not found:")) return false;
        	else throw e;
        }
	}
    
    protected tibjmsJNDI() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected String getProviderUrl() {
		return providerUrl;
	}

	protected void setProviderUrl(String providerUrl) {
		this.providerUrl = providerUrl;
	}

	protected String getUserName() {
		return userName;
	}

	protected void setUserName(String userName) {
		this.userName = userName;
	}

	protected String getPassword() {
		return password;
	}

	protected void setPassword(String password) {
		this.password = password;
	}

}
