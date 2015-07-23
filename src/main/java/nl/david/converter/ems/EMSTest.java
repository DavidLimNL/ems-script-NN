package nl.david.converter.ems;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import jxl.read.biff.BiffException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

import javax.naming.*;


public class EMSTest {

	private static void printEMSQueueList(HashMap<String, EMSQueue> queues) {
		for(EMSQueue q : queues.values()) {
			q.printEMSQueue();
		}
	}

	private static void createScript(TreeMap<String, EMSQueue> queues, String folderName, String username, String password, String environment) throws Exception {
		String credentials = "";
		if(username.length() > 0 && password.length() > 0)
			credentials = username + " " + password;

		// Checks which servers are used
		String emsSmall = "", emsLarge = "", p2pSmall = "", p2pLarge = "";
		for(EMSQueue q : queues.values()) {
			if(q.getServer().getType().equals("ESB_SMALL")) {
				emsSmall = q.getServer().getName();
			}
			if(q.getServer().getType().equals("ESB_LARGE")) {
				emsLarge = q.getServer().getName();
			}
			if(q.getServer().getType().equals("P2P_SMALL")) {
				p2pSmall = q.getServer().getName();
			}
			if(q.getServer().getType().equals("P2P_LARGE")) {
				p2pLarge = q.getServer().getName();
			}
		}

		PrintWriter writer;
		InitialContext jndiContext = null;

		try {
			// ESB SMALL
			if(emsSmall.length() > 0) {
				tibjmsJNDI j = new tibjmsJNDI();
				jndiContext = j.initialize(emsSmall, username, password);
				writer = new PrintWriter(folderName + System.getProperty("file.separator") + "ESB_SMALL_" + environment + ".emss", "UTF-8");
				writer.println("connect " + emsSmall + " " + username + " " + password + System.getProperty("line.separator"));
				for(EMSQueue q : queues.values()) {
					if(q.getServer().getType().equals("ESB_SMALL")) {
						if(!(j.checkQueue(q.getName(), jndiContext)))
							writer.println(q.createEMSQueue());
					}
				}
				for(EMSQueue q : queues.values()) {
					if(q.getServer().getType().equals("ESB_SMALL")) {
						writer.print(q.createQueueProperties());
					}
				}
				writer.println();
				for(EMSQueue q : queues.values()) {
					if(q.getServer().getType().equals("ESB_SMALL")) {
						if(!q.getProviders().isEmpty()) writer.println(q.createEMSGrantsProviders());
						if(!q.getConsumers().isEmpty()) writer.println(q.createEMSGrantsConsumers());
					}
				}
				writer.println(System.getProperty("line.separator") + "commit");
				writer.close();
			}

			// ESB LARGE
			if(emsLarge.length() > 0) {
				tibjmsJNDI j = new tibjmsJNDI();
				jndiContext = j.initialize(emsLarge, username, password);
				writer = new PrintWriter(folderName + System.getProperty("file.separator") + "ESB_LARGE_" + environment + ".emss", "UTF-8");
				writer.println("connect " + emsLarge + " " + username + " " + password + System.getProperty("line.separator"));
				for(EMSQueue q : queues.values()) {
					if(q.getServer().getType().equals("ESB_LARGE")) {
						if(!(j.checkQueue(q.getName(), jndiContext)))
							writer.println(q.createEMSQueue());
					}
				}
				writer.println();
				for(EMSQueue q : queues.values()) {
					if(q.getServer().getType().equals("ESB_LARGE")) {
						writer.print(q.createQueueProperties());
					}
				}
				writer.println();
				for(EMSQueue q : queues.values()) {
					if(q.getServer().getType().equals("ESB_LARGE")) {
						if(!q.getProviders().isEmpty()) writer.println(q.createEMSGrantsProviders());
						if(!q.getConsumers().isEmpty()) writer.println(q.createEMSGrantsConsumers());
					}
				}
				writer.println(System.getProperty("line.separator") + "commit");
				writer.close();
			}

			// P2P SMALL
			if(p2pSmall.length() > 0) {
				tibjmsJNDI j = new tibjmsJNDI();
				jndiContext = j.initialize(p2pSmall, username, password);
				writer = new PrintWriter(folderName + System.getProperty("file.separator") + "P2P_SMALL_" + environment + ".emss", "UTF-8");
				writer.println("connect " + p2pSmall + " " + username + " " + password + System.getProperty("line.separator"));
				for(EMSQueue q : queues.values()) {
					if(q.getServer().getType().equals("P2P_SMALL")) {
						if(!(j.checkQueue(q.getName(), jndiContext)))
							writer.println(q.createEMSQueue());
					}
				}
				writer.println();
				for(EMSQueue q : queues.values()) {
					if(q.getServer().getType().equals("P2P_SMALL")) {
						writer.print(q.createQueueProperties());
					}
				}
				writer.println();
				for(EMSQueue q : queues.values()) {
					if(q.getServer().getType().equals("P2P_SMALL")) {
						if(!q.getProviders().isEmpty()) writer.println(q.createEMSGrantsProviders());
						if(!q.getConsumers().isEmpty()) writer.println(q.createEMSGrantsConsumers());
					}
				}
				writer.println(System.getProperty("line.separator") + "commit");
				writer.close();
			}

			// P2P LARGE
			if(p2pLarge.length() > 0) {
				tibjmsJNDI j = new tibjmsJNDI();
				jndiContext = j.initialize(p2pLarge, username, password);
				writer = new PrintWriter(folderName + System.getProperty("file.separator") + "P2P_LARGE_" + environment + ".emss", "UTF-8");
				writer.println("connect " + p2pLarge + " " + username + " " + password + System.getProperty("line.separator"));
				for(EMSQueue q : queues.values()) {
					if(q.getServer().getType().equals("P2P_LARGE")) {
						if(!(j.checkQueue(q.getName(), jndiContext)))
							writer.println(q.createEMSQueue());
					}
				}
				writer.println();
				for(EMSQueue q : queues.values()) {
					if(q.getServer().getType().equals("P2P_LARGE")) {
						writer.print(q.createQueueProperties());
					}
				}
				writer.println();
				for(EMSQueue q : queues.values()) {
					if(q.getServer().getType().equals("P2P_LARGE")) {
						if(!q.getProviders().isEmpty()) writer.println(q.createEMSGrantsProviders());
						if(!q.getConsumers().isEmpty()) writer.println(q.createEMSGrantsConsumers());
					}
				}
				writer.println(System.getProperty("line.separator") + "commit");
				writer.close();
			}
		} catch (NamingException ne) {
			if(ne.getRootCause().toString().equals("javax.jms.JMSSecurityException: authentication failed"))
				throw new EMSScriptException(3, ne.getRootCause().toString());
		} finally {
			jndiContext.close();
		}
	}

	public static EMSQueue readProvidersTab(String name, Cell[] row, String username, String environment, String intake) throws EMSScriptException {
		EMSQueue q = new EMSQueue();
		q.setName(name.replaceAll("\\s+", ""));
		q.setProviders(username);
		if(row[3].getContents().toLowerCase().equals("persistent"))
			q.setPropertiesPersistent(true);
		else
			q.setPropertiesPersistent(false);
		String server = row[4].getContents().toLowerCase().trim();
		
		//Throws exception if server name is empty
		if(server.trim().length() == 0) {
			throw new EMSScriptException(3, "Server name is empty in the providers tab for the intake " + intake);
		}
		
		if(server.equalsIgnoreCase("esb small") || server.equalsIgnoreCase("esb-small"))
			q.setServer(environment, "ESB_SMALL");
		if(server.equalsIgnoreCase("esb large") || server.equalsIgnoreCase("esb-large"))
			q.setServer(environment, "ESB_LARGE");
		if(server.equalsIgnoreCase("p2p small") || server.equalsIgnoreCase("p2p-small"))
			q.setServer(environment, "P2P_SMALL");
		if(server.equalsIgnoreCase("p2p large") || server.equalsIgnoreCase("p2p-large"))
			q.setServer(environment, "P2P_LARGE");

		if(row.length >= 23) {
			if(row[20].getContents().trim().length() != 0)
				q.setPropertiesPrefetch(row[20].getContents().trim());
			if(row[21].getContents().trim().length() != 0)
				q.setPropertiesMaxRedelivery(Integer.valueOf(row[21].getContents().trim()));
			if(row[22].getContents().trim().length() != 0)
				q.setPropertiesRedeliveryDelay(Integer.valueOf(row[22].getContents().trim()));
		}

		return q;
	}

	public static void main(String[] args) throws BiffException, IOException, EMSScriptException {
		PrintWriter log = null;
		try {
			Workbook emsWorkbook;
			TreeMap<String, EMSQueue> queues = new TreeMap<String, EMSQueue>(); 

			Scanner x = new Scanner(System.in);  
			System.out.println("Enter a folder name: ");  
			String folderName = x.nextLine();
			String environment = args[0];
			log = new PrintWriter(folderName + System.getProperty("file.separator") + "logfile.log", "UTF-8");

			if(!environment.equals("D") && !environment.equals("T") && !environment.equals("A") && !environment.equals("P")) {
				throw new EMSScriptException(1, "Incorrect choice for environment! The options are D, T, A or P.");
			}

			List<String> fileNames = new ArrayList<String>();
			File[] files = new File(folderName).listFiles();

			for (File file : files) {
				if (file.isFile() && file.getName().toLowerCase().contains(".xls")) {
					fileNames.add(file.getPath());
				}
			}
			System.out.println(fileNames.toString());

			//Iterate through all Excel files in the folder specified
			for(String s : fileNames) {
				System.out.println("Processing file: " + s);
				emsWorkbook = Workbook.getWorkbook(new File(s));

				Sheet applicationInformation = emsWorkbook.getSheet(1);
				Cell username = applicationInformation.getCell(3, 18);
				if(environment.equals("D"))
					username = applicationInformation.getCell(3, 18);
				else if(environment.equals("T"))
					username = applicationInformation.getCell(3, 19);
				else if(environment.equals("A"))
					username = applicationInformation.getCell(3, 20);
				else if(environment.equals("P"))
					username = applicationInformation.getCell(3, 21);

				//Throws exception if username is empty
				if(username.getContents().trim().length() == 0) {
					throw new EMSScriptException(2, "NPA username is not filled in for this environment on the intake " + s);
				}

				// Providers tab
				Sheet providersSheet = emsWorkbook.getSheet(3);
				for(int i=9 ; i<providersSheet.getRows() ; i++) {
					EMSQueue q = new EMSQueue();
					Cell[] row = providersSheet.getRow(i);
					if(row.length > 17 && !row[1].getContents().isEmpty() && !row[1].getContents().equalsIgnoreCase("Queue name") && !row[1].getContents().endsWith("Request / Response") && !row[1].getCellFormat().getFont().isStruckout()) {
						q = readProvidersTab(row[1].getContents().trim(), row, username.getContents().trim(), environment, s);

						//Adds queue to hash map, merging duplicates
						EMSQueue qa = queues.get(q.getName());
						if(qa == null)
							queues.put(q.getName(), q);
						else {
							qa.mergeEMSQueues(q);
						}
					}

					// Processes queues with Request/Response types
					if(row.length > 17 && !row[1].getContents().isEmpty() && !row[1].getContents().equalsIgnoreCase("Queue name") && row[1].getContents().endsWith("Request / Response") && !row[1].getCellFormat().getFont().isStruckout()) {
						EMSQueue q2 = new EMSQueue();
						q = readProvidersTab(row[1].getContents().replaceFirst("/ Response", "").trim(), row, username.getContents().trim(), environment, s);
						q2 = readProvidersTab(row[1].getContents().replaceFirst("Request / ", "").trim(), row, username.getContents().trim(), environment, s);

						//Adds queue to hash map, merging duplicates
						EMSQueue qa = queues.get(q.getName());
						if(qa == null)
							queues.put(q.getName(), q);
						else {
							qa.mergeEMSQueues(q);
						}
						qa = queues.get(q2.getName());
						if(qa == null)
							queues.put(q2.getName(), q2);
						else {
							qa.mergeEMSQueues(q2);
						}
					}
				}

				// Consumers tab
				Sheet consumersSheet = emsWorkbook.getSheet(4);
				for(int i=0 ; i<consumersSheet.getRows() ; i++) {
					EMSQueue q = new EMSQueue();
					Cell[] row = consumersSheet.getRow(i);
					if((row[0].getContents().toLowerCase().equals("soap") || row[0].getContents().toLowerCase().equals("xml") || row[0].getContents().toLowerCase().equals("copybook")) && !row[1].getCellFormat().getFont().isStruckout()) {
						q.setName(row[1].getContents().replaceAll("\\s+", ""));
						Boolean bo = row[1].getCellFormat().getFont().isStruckout();
						q.setConsumers(username.getContents().trim());
						if(row[5].getContents().toLowerCase().equals("persistent"))
							q.setPropertiesPersistent(true);
						else
							q.setPropertiesPersistent(false);

						String server = row[6].getContents().toLowerCase().trim();
						//Throws exception if server name is empty
						if(server.trim().length() == 0) {
							throw new EMSScriptException(4, "Server name is empty in the consumers tab for the intake " + s);						
						}
						
						if(server.equalsIgnoreCase("esb small") || server.equalsIgnoreCase("esb-small"))
							q.setServer(environment, "ESB_SMALL");
						if(server.equalsIgnoreCase("esb large") || server.equalsIgnoreCase("esb-large"))
							q.setServer(environment, "ESB_LARGE");
						if(server.equalsIgnoreCase("p2p small") || server.equalsIgnoreCase("p2p-small"))
							q.setServer(environment, "P2P_SMALL");
						if(server.equalsIgnoreCase("p2p large") || server.equalsIgnoreCase("p2p-large"))
							q.setServer(environment, "P2P_LARGE");

						if(row.length >= 15) {
							if(row[12].getContents().trim().length() != 0)
								q.setPropertiesPrefetch(row[12].getContents().trim());
							if(row[13].getContents().trim().length() != 0)
								q.setPropertiesMaxRedelivery(Integer.valueOf(row[13].getContents().trim()));
							if(row[14].getContents().trim().length() != 0)
								q.setPropertiesRedeliveryDelay(Integer.valueOf(row[14].getContents().trim()));
						}

						//Adds queue to hash map, merging duplicates
						EMSQueue qa = queues.get(q.getName());
						if(qa == null)
							queues.put(q.getName(), q);
						else {
							qa.mergeEMSQueues(q);
						}
					}
				}

				emsWorkbook.close();
			}
			if(args.length == 3)
				createScript(queues, folderName, args[1], args[2], environment);
			else
				throw new EMSScriptException(4, "Invalid number of arguments. The syntax is: <environment> <username> <password>");
			
		} catch (EMSScriptException e) {
			log.print(e.printException());
		} catch (Exception e) {
			log.print(e.toString());
		} finally {
			log.close();
		}

	} 


}
