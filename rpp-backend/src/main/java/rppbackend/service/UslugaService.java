package rppbackend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rppbackend.model.KorisnikUsluge;
import rppbackend.model.Usluga;
import rppbackend.repository.UslugaRepository;

@Service
public class UslugaService {

	@Autowired
	private UslugaRepository uslugaRepository;
	
	public List<Usluga> getAll(){
		return uslugaRepository.findAll();
	}
	
	public Optional<Usluga> findById(Integer id) {
		return uslugaRepository.findById(id);
	}
	
	  public List<Usluga> findByKorisnik(KorisnikUsluge korisnik) {
	        return uslugaRepository.findByKorisnik(korisnik);
	    }
	
	public List<Usluga> findByNazivContainingIgnoreCase(String naziv) {
        return uslugaRepository.findByNazivContainingIgnoreCase(naziv);
    }
	
	public Usluga save(Usluga usluga) {
		return uslugaRepository.save(usluga);
	}
	
	public boolean existsById(Integer id) {
		return uslugaRepository.existsById(id);
	}
	
	public void deleteById(Integer id) {
		uslugaRepository.deleteById(id);
	}
}
