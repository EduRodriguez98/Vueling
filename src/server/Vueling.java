package server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import data.Flight;
import dto.FlightAssembler;
import dto.FlightDTO;


public class Vueling extends UnicastRemoteObject implements IVueling {
	
	private static final long serialVersionUID = 1L;
	
	public static ArrayList<Flight> flights = new ArrayList<Flight>();
	
	private FlightAssembler assembler = new FlightAssembler();
	
	protected Vueling() throws RemoteException {
		Flight flight1 = new Flight("VY1101", "Bilbao", "Madrid", "30-01-2021", 45);
		flights.add(flight1);
	}
	
	public ArrayList <FlightDTO> getFlights(String origin, String destination, String date) throws RemoteException {
		ArrayList<Flight> flightsR = new ArrayList<Flight>();
		for (Flight f : flights) {
			if(f.getOrigin().equals(origin) && f.getDestination().equals(destination) && f.getDate().equals(date)) {
				flightsR.add(f);
			} else {
				System.err.println("No available flights.");
			}
		}
		if(flightsR.size() == 0) {
			return null;
		}
		// GETTING A LIST OF FLIGHTS AND ASSEMBLING THEM FOR TRANSMISSION
		return assembler.assemble(flightsR);
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
