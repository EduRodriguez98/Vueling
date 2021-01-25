package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import dto.FlightDTO;

public interface IVueling extends Remote {
	public ArrayList <FlightDTO> getFlights(String origin, String destination, String date) throws RemoteException;

}
