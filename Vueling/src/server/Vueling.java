package server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Vueling extends UnicastRemoteObject implements IGoogle {
	
	private static final long serialVersionUID = 1L;
	public static ArrayList<FlightDTO> flightsR = new ArrayList<FlightDTO>();
	
	protected Vueling() throws RemoteException {
		FlightDTO flight1 = new FlightDTO("Bilbao", "Madrid", "30-01-2021");
		flightsR.add(flight1);
	}
	
	public ArrayList <FlightDTO> getFlights(String origin, String destination, String date) throws RemoteException {
		ArrayList<FlightDTO> flights = new ArrayList<FlightDTO>();
		for (FlightDTO f : flightsR) {
			//if(f.getOrigin().equals(origin) && f.getDestination().equals(destination) && f.getDate(date)) {
			if(f.origin == origin && f.destination == destination && f.date == date) {
				flights.add(f);
			} else {
				System.err.println("No available flights.");
			}
		}
		return flights;
	}

	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("usage: java [policy] [codebase] server.Server [host] [port] [server]");
			System.exit(0);
		}

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];

		try {		
			IVueling vuelingServer = new Vueling();
			Naming.rebind(name, vuelingServer);
			System.out.println("* Server '" + name + "' active and waiting...");
		} catch (Exception e) {
			System.err.println("- Exception running the server: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
