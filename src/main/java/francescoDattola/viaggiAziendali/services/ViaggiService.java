package francescoDattola.viaggiAziendali.services;

import francescoDattola.viaggiAziendali.dto.ViaggioDTO;
import francescoDattola.viaggiAziendali.entities.Viaggi;
import francescoDattola.viaggiAziendali.exceptions.NotFoundException;
import francescoDattola.viaggiAziendali.repositories.ViaggioRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ViaggiService {
    private final ViaggioRepository viaggioRepository;

    public ViaggiService(ViaggioRepository viaggioRepository) {this.viaggioRepository = viaggioRepository;}
    public Viaggi save(Viaggi v){
        return this.viaggioRepository.save(v);
    }
    public Viaggi findById(UUID id){
        return this.viaggioRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
    }
}
