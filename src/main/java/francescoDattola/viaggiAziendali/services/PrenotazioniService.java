package francescoDattola.viaggiAziendali.services;

import francescoDattola.viaggiAziendali.dto.DipendentiDto;
import francescoDattola.viaggiAziendali.dto.TripDTO;
import francescoDattola.viaggiAziendali.dto.ViaggioDTO;
import francescoDattola.viaggiAziendali.entities.Dipendenti;
import francescoDattola.viaggiAziendali.entities.Prenotazioni;
import francescoDattola.viaggiAziendali.entities.Viaggi;
import francescoDattola.viaggiAziendali.exceptions.NotAvailableDate;
import francescoDattola.viaggiAziendali.exceptions.NotFoundException;
import francescoDattola.viaggiAziendali.repositories.PrenotazioniRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class PrenotazioniService {
    private final PrenotazioniRepository prenotazioniRepository;
    private final DipendentiService dipendentiService;
    private final ViaggiService viaggiService;
    public PrenotazioniService(PrenotazioniRepository prenotazioniRepository,DipendentiService dipendentiService,ViaggiService viaggiService)
    {this.prenotazioniRepository = prenotazioniRepository;
        this.dipendentiService = dipendentiService;
        this.viaggiService = viaggiService;
    }

    public Prenotazioni save(ViaggioDTO viaggioDTO){
        Dipendenti foundDipendente = this.dipendentiService.findById(viaggioDTO.dipendenti());
        if(this.prenotazioniRepository.existsByViaggi_DipendentiAndViaggi_Date(foundDipendente,viaggioDTO.date()))
            throw new NotAvailableDate(viaggioDTO.date() +" is already booked by this employee "+viaggioDTO.dipendenti());
        Viaggi viaggi = new Viaggi(viaggioDTO.date(),viaggioDTO.destination(),foundDipendente,viaggioDTO.states());
        this.viaggiService.save(viaggi);
        return this.prenotazioniRepository.save(new Prenotazioni(viaggioDTO.note(),viaggi));
    }
    public Page<Prenotazioni> findAll(int page, int size, String sortBy){
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortBy));
        return this.prenotazioniRepository.findAll(pageable);
    }
    public Prenotazioni findById(UUID id){
        return this.prenotazioniRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
    }
    public void deleteById(UUID id){
        Prenotazioni found = findById(id);
        this.prenotazioniRepository.deleteById(id);
        log.info(found.getId()+" delete from the db");
    }
    public Prenotazioni findByIdAndUpdate(UUID id, ViaggioDTO vd){
       Prenotazioni foundP = this.prenotazioniRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
       Viaggi foundV = foundP.getViaggi();
       Dipendenti foundD = this.dipendentiService.findById(vd.dipendenti());
       foundV.setDate(vd.date());
       foundV.setDestination(vd.destination());
       foundV.setStates(vd.states());
       foundV.setDipendenti(foundD);
       this.viaggiService.save(foundV);
       if(vd.note() != null) foundP.setNote(vd.note());
       this.prenotazioniRepository.save(foundP);
       return foundP;
    }
    public Prenotazioni findTripByIdAndUpdate(UUID id, TripDTO tripDTO){
        Prenotazioni foundP = this.prenotazioniRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
        Viaggi foundV = foundP.getViaggi();
        foundV.setStates(tripDTO.states());
        this.viaggiService.save(foundV);
        this.prenotazioniRepository.save(foundP);
        return foundP;
    }
}
