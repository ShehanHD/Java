package best.wecode.csv_db.comuni;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ComuniService {
    private final ComuniRepository comuniRepository;

    @Autowired
    public ComuniService(ComuniRepository comuniRepository) {
        this.comuniRepository = comuniRepository;
    }

    public List<Comuni> getComuni(){
        return comuniRepository.findAll();
    }

    public void addComuni(MultipartFile file) throws IOException {
        List<Comuni> comuni = ComuniCSVHelper.csvToComuni(file.getInputStream());

        comuniRepository.saveAll(comuni);
    }
}
