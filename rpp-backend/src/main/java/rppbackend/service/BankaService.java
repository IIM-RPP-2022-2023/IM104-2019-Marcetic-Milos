package rppbackend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rppbackend.model.Banka;
import rppbackend.repository.BankaRepository;

@Service
public class BankaService {

	@Autowired
	private BankaRepository bankaRepository;
	
	public List<Banka> getAll(){
		return bankaRepository.findAll();
	}
	
	public Optional<Banka> findById(Integer id) {
		return bankaRepository.findById(id);
	}
	
	public List<Banka> findByNazivContainingIgnoreCase(String naziv) {
        return bankaRepository.findByNazivContainingIgnoreCase(naziv);
    }
	
	public Banka save(Banka banka) {
		return bankaRepository.save(banka);
	}
	
	public boolean existsById(Integer id) {
		return bankaRepository.existsById(id);
	}
	
	public void deleteById(Integer id) {
		bankaRepository.deleteById(id);
	}

}
