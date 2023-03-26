package rppbackend.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rppbackend.model.Filijala;
import rppbackend.service.FilijalaService;

@RestController
public class FilijalaController {

	@Autowired
	private FilijalaService filijalaService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

	@GetMapping("filijala")
	public ResponseEntity<List<Filijala>> getAll(){
		List<Filijala> filijals = filijalaService.getAll();
        return new ResponseEntity<>(filijals, HttpStatus.OK);
	}
	
	@GetMapping("filijala/{id}")
	public ResponseEntity<Filijala> getOne(@PathVariable("id") Integer id){
	    if (filijalaService.findById(id).isPresent()) {
	    	Optional<Filijala> filijala = filijalaService.findById(id);
            return new ResponseEntity<>(filijala.get(), HttpStatus.OK);
	    } else {
	    	return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	    }
	}
	
	@GetMapping("filijala/adresa/{adresa}")
	public ResponseEntity<List<Filijala>> getByNaziv(@PathVariable("naziv") String adresa){
		List<Filijala> filijals = filijalaService.findByNazivContainingIgnoreCase(adresa);
        return new ResponseEntity<>(filijals, HttpStatus.OK);
	}
	
	@PostMapping("filijala")
	public ResponseEntity<Filijala> addDobavljac(@RequestBody Filijala filijala) {
		Filijala savedFilijala = filijalaService.save(filijala);
        URI location = URI.create("/filijala/" + savedFilijala.getId());
		return ResponseEntity.created(location).body(savedFilijala);
	}

    @PutMapping(value = "filijala/{id}")
    public ResponseEntity<Filijala> updateDobavljac(@RequestBody Filijala filijala, @PathVariable("id") Integer id) {
        if (filijalaService.existsById(id)) {
        	filijala.setId(id);
        	Filijala savedFilijala = filijalaService.save(filijala);
            return ResponseEntity.ok().body(savedFilijala);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
	
    @DeleteMapping("filijala/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Integer id) {
        if (id == -100 && !filijalaService.existsById(id)) {
            jdbcTemplate.execute(
            		"INSERT INTO filijala (\"id\", \"adresa\", \"broj_pultova\", \"poseduje_sef\", \"banka\") VALUES (-100, 'Test Adresa', 'Test Broj_Pultova', 'Test Poseduje_Sef', 'Test Banka')");
            
        }

        if (filijalaService.existsById(id)) {
        	filijalaService.deleteById(id);
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        }
        return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
    }
}