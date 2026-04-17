package francescoDattola.viaggiAziendali.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import francescoDattola.viaggiAziendali.dto.DipendentiDto;
import francescoDattola.viaggiAziendali.dto.NewDipendenteDTO;
import francescoDattola.viaggiAziendali.entities.Dipendenti;
import francescoDattola.viaggiAziendali.exceptions.NotAvailable;
import francescoDattola.viaggiAziendali.exceptions.NotFoundException;
import francescoDattola.viaggiAziendali.repositories.DipendentiRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class DipendentiService {
    private final DipendentiRepository dipendentiRepository;
    private final Cloudinary cloudinary;
    public DipendentiService(DipendentiRepository dipendentiRepository,Cloudinary cloudinary)
    {this.dipendentiRepository = dipendentiRepository;
        this.cloudinary = cloudinary;
    }

    public Dipendenti save(DipendentiDto d){
        if(this.dipendentiRepository.existsByEmail(d.email())) throw new NotAvailable(d.email()+" già in uso");
        Dipendenti dipendente = new Dipendenti(d.email(),d.name(),d.surname(),d.username());
        log.info(d.name() + " salvato nel db");
        return this.dipendentiRepository.save(dipendente);
    }
    public Page<Dipendenti> findAll(int page, int size, String sortBy){
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortBy));
        return this.dipendentiRepository.findAll(pageable);
    }

    public Dipendenti findById(UUID id){
        return this.dipendentiRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
    }
    public Dipendenti findByIdAndUpdate(UUID id, DipendentiDto nd){
        Dipendenti found = findById(id);
        found.setEmail(nd.email());
        found.setName(nd.name());
        found.setSurname(nd.surname());
        found.setUsername(nd.username());
        return this.dipendentiRepository.save(found);
    }
    public void deleteById(UUID id){
        Dipendenti found = findById(id);
        this.dipendentiRepository.deleteById(id);
        log.info(found.getName()+" delete from the db");
    }
    public Dipendenti avatarUpload(MultipartFile file,UUID id){
        Dipendenti found = findById(id);
        try {
           Map photo =  this.cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
           String url = (String) photo.get("url");
            found.setAvatar(url);
            return this.dipendentiRepository.save(found);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
