package best.wecode.csv_db.comuni;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/comuni")
@CrossOrigin("*")
public class ComuniController {
    final ComuniService comuniService;

    @Autowired
    public ComuniController(ComuniService comuniService) {
        this.comuniService = comuniService;
    }

    @GetMapping
    public List<Comuni> getComuni(){
        return comuniService.getComuni();
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file")MultipartFile file) {

        if(ComuniCSVHelper.isCSVFile(file)) {
            try {
                comuniService.addComuni(file);
                return ResponseEntity.status(HttpStatus.OK).body("CSV file successfully uploaded");
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Upload failed");
            }
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Need a CSV file");
        }
    }
}
