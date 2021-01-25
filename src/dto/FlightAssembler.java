package dto;

import java.util.ArrayList;

import data.Flight;

public class FlightAssembler {
	
	public ArrayList<FlightDTO> assemble(ArrayList<Flight> flights) {
		ArrayList<FlightDTO> flightsDTO = new ArrayList<FlightDTO>();

		for (Flight f : flights) {
			flightsDTO.add(new FlightDTO(f.getFlightCode(), f.getOrigin(), f.getDestination(), f.getDate(), f.getPrice()));
		}

		System.out.println("* Assembling Flights ...");
		
		return flightsDTO;
	}
}
