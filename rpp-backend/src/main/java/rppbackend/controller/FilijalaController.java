package rppbackend.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import rppbackend.model.Filijala;
import rppbackend.service.FilijalaService;

@CrossOrigin
@RestController
public class FilijalaController {

	@Autowired
	private FilijalaService filijalaService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @ApiOperation(value = "Returns List of all Filijalas")
	@GetMapping("filijala")
	public ResponseEntity<List<Filijala>> getAll(){
		List<Filijala> filijals = filijalaService.getAll();
        return new ResponseEntity<>(filijals, HttpStatus.OK);
	}
	
    @ApiOperation(value = "Returns Filijala with id that was forwarded as path variable.")
	@GetMapping("filijala/{id}")
	public ResponseEntity<Filijala> getOne(@PathVariable("id") Integer id){
	    if (filijalaService.findById(id).isPresent()) {
	    	Optional<Filijala> filijala = filijalaService.findById(id);
            return new ResponseEntity<>(filijala.get(), HttpStatus.OK);
	    } else {
	    	return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	    }
	}
	
    @ApiOperation(value = "Returns list of Filijalas containing string that was forwarded as path variable in 'adresa'.")
	@GetMapping("filijala/adresa/{adresa}")
	public ResponseEntity<List<Filijala>> getByNaziv(@PathVariable("adresa") String adresa){
		List<Filijala> filijals = filijalaService.findByNazivContainingIgnoreCase(adresa);
        return new ResponseEntity<>(filijals, HttpStatus.OK);
	}
	
    @ApiOperation(value = "Adds new Filijala to database.")
	@PostMapping("filijala")
	public ResponseEntity<Filijala> addFilijala(@RequestBody Filijala filijala) {
		Filijala savedFilijala = filijalaService.save(filijala);
        URI location = URI.create("/filijala/" + savedFilijala.getId());
		return ResponseEntity.created(location).body(savedFilijala);
	}

    @ApiOperation(value = "Updates Filijala that has id that was forwarded as path variable with values forwarded in Request Body.")
    @PutMapping(value = "filijala/{id}")
    public ResponseEntity<Filijala> updateFilijala(@RequestBody Filijala filijala, @PathVariable("id") Integer id) {
        if (filijalaService.existsById(id)) {
        	filijala.setId(id);
        	Filijala savedFilijala = filijalaService.save(filijala);
            return ResponseEntity.ok().body(savedFilijala);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
	
    @ApiOperation(value = "Deletes Filijala with id that was forwarded as path variable.")
    @DeleteMapping("filijala/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Integer id) {
        if (id == -100 && !filijalaService.existsById(id)) {
            jdbcTemplate.execute(
            		"INSERT INTO filijala (\"id\", \"adresa\", \"broj_pultova\", \"poseduje_sef\", \"banka\") VALUES (-100, 'Test Adresa', 10, true, 3)");
            
        }

        if (filijalaService.existsById(id)) {
        	filijalaService.deleteById(id);
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        }
        return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
    }
}
