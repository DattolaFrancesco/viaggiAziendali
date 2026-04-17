package francescoDattola.viaggiAziendali.controllers;

import francescoDattola.viaggiAziendali.dto.DipendentiDto;
import francescoDattola.viaggiAziendali.dto.TripDTO;
import francescoDattola.viaggiAziendali.dto.ViaggioDTO;
import francescoDattola.viaggiAziendali.entities.Dipendenti;
import francescoDattola.viaggiAziendali.entities.Prenotazioni;
import francescoDattola.viaggiAziendali.exceptions.NotValidEmployeesInfos;
import francescoDattola.viaggiAziendali.services.PrenotazioniService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reservations")
public class PrenotazioniController {
    private final PrenotazioniService prenotazioniService;
    public PrenotazioniController(PrenotazioniService prenotazioniService) {this.prenotazioniService = prenotazioniService;}

    @PostMapping
    public Prenotazioni save(@RequestBody @Validated ViaggioDTO viaggioDTO, BindingResult validation){
        if(validation.hasErrors()) {
            List<String> errors = validation.getFieldErrors().stream().map(error->error.getDefaultMessage()).toList();
            throw new NotValidEmployeesInfos(errors);
        }
        return this.prenotazioniService.save(viaggioDTO);
    }
    @GetMapping
    public Page<Prenotazioni> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id")  String sortBy){
        return  this.prenotazioniService.findAll(page,size,sortBy);
    }
    @GetMapping("/{id}")
    public Prenotazioni findById(@PathVariable UUID id){
        return this.prenotazioniService.findById(id);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable UUID id){
        this.prenotazioniService.deleteById(id);
    }
    @PutMapping("/{id}")
    public Prenotazioni findByIdAndUpdate(@PathVariable UUID id,@RequestBody @Validated ViaggioDTO viaggioDTO, BindingResult validation){
        if(validation.hasErrors()) {
            List<String> errors = validation.getFieldErrors().stream().map(error->error.getDefaultMessage()).toList();
            throw new NotValidEmployeesInfos(errors);
        }
        return this.prenotazioniService.findByIdAndUpdate(id,viaggioDTO);

    }
    @PatchMapping("/{id}/tripsStatus")
    public Prenotazioni findTripByIdAndUpdate(@PathVariable UUID id, @RequestBody @Validated TripDTO tripDTO, BindingResult validation){
        if(validation.hasErrors()) {
            List<String> errors = validation.getFieldErrors().stream().map(error->error.getDefaultMessage()).toList();
            throw new NotValidEmployeesInfos(errors);
        }
        return this.prenotazioniService.findTripByIdAndUpdate(id, tripDTO);

    }

}
