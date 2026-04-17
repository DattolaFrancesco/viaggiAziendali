package francescoDattola.viaggiAziendali.controllers;

import francescoDattola.viaggiAziendali.dto.DipendentiDto;
import francescoDattola.viaggiAziendali.dto.NewDipendenteDTO;
import francescoDattola.viaggiAziendali.entities.Dipendenti;
import francescoDattola.viaggiAziendali.exceptions.NotValidEmployeesInfos;
import francescoDattola.viaggiAziendali.services.DipendentiService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class DipendenteController {
    private final DipendentiService dipendentiService;
    public DipendenteController(DipendentiService dipendentiService) {this.dipendentiService = dipendentiService;}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewDipendenteDTO save(@RequestBody @Validated DipendentiDto d, BindingResult validation){
        if(validation.hasErrors()) {
            List<String> errors = validation.getFieldErrors().stream().map(error->error.getDefaultMessage()).toList();
            throw new NotValidEmployeesInfos(errors);
        }
       Dipendenti dip =  this.dipendentiService.save(d);
       return new NewDipendenteDTO(dip.getId());
    }
    @GetMapping
    public Page<Dipendenti> findAll(
           @RequestParam(defaultValue = "0") int page,
           @RequestParam(defaultValue = "5") int size,
           @RequestParam(defaultValue = "name")  String sortBy){
        return  this.dipendentiService.findAll(page,size,sortBy);
    }
    @GetMapping("/{id}")
    public Dipendenti findById(@PathVariable UUID id){
        return this.dipendentiService.findById(id);
    }
    @PutMapping("/{id}")
    public Dipendenti findByIdAndUpdate(@PathVariable UUID id, @RequestBody  DipendentiDto d){
        return this.dipendentiService.findByIdAndUpdate(id,d);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable UUID id){
        this.dipendentiService.deleteById(id);
    }
    @PatchMapping("/{id}/avatar")
    public Dipendenti uploadAvatar(@RequestParam("profile_avatar") MultipartFile file,@PathVariable UUID id){
        return this.dipendentiService.avatarUpload(file,id);
    }
}
