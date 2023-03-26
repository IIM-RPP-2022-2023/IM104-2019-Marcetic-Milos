package rppbackend.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rppbackend.model.KorisnikUsluge;
import rppbackend.repository.KorisnikUslugeRepository;

@Service
public class KorisnikUslugeService {

	@Autowired
	private KorisnikUslugeRepository korisnikRepository;
	
	public List<KorisnikUsluge> getAll(){
		return korisnikRepository.findAll();
	}
	
	public Optional<KorisnikUsluge> findById(Integer id) {
		return korisnikRepository.findById(id);
	}
	
	public List<KorisnikUsluge> findByImeContainingIgnoreCase(String ime) {
        return korisnikRepository.findByImeContainingIgnoreCase(ime);
    }
	
	public KorisnikUsluge save(KorisnikUsluge korisnik) {
		return korisnikRepository.save(korisnik);
	}
	
	public boolean existsById(Integer id) {
		return korisnikRepository.existsById(id);
	}
	
	public void deleteById(Integer id) {
		korisnikRepository.deleteById(id);
	}
}
