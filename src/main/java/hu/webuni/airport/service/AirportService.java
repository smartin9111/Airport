package hu.webuni.airport.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import hu.webuni.airport.model.Airport;

@Service
public class AirportService {
	
	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public Airport save(Airport airport) {
		checkUniqueIata(airport.getIata());
		em.persist(airport);
		return airport;
	}
	
	@Transactional
	public Airport update(Airport airport) {
		checkUniqueIata(airport.getIata());
		return em.merge(airport);
	}
	
	private void checkUniqueIata(String iata) {
		
		Long count = em.createNamedQuery("Airport.countByIata", Long.class)
		.setParameter("iata", iata)
		.getSingleResult();
		
		if (count >0)
			throw new NonUniqueIataException(iata);
	}
	
	public List<Airport> findAll(){
		return em.createQuery("SELECt a FROM Airport a", Airport.class).getResultList();
	}
	
	public Airport findById(long id){
		return em.find(Airport.class, id);
	}
	
	@Transactional
	public void delete(long id) {
		em.remove(findById(id));
	}

}
