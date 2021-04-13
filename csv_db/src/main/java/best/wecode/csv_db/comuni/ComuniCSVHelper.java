package best.wecode.csv_db.comuni;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ComuniCSVHelper {
    public static String FILE_TYPE = "text/csv";
    static String[] HEADERS = {"id","name","slug","lat","lng","codice_provincia_istat","codice_comune_istat","codice_alfanumerico_istat","capoluogo_provincia","capoluogo_regione"};

    public static boolean isCSVFile(MultipartFile file){
        return FILE_TYPE.equals(file.getContentType());
    }

    public static List<Comuni> csvToComuni(InputStream is) {
        BufferedReader fileReader = null;
        try {
            fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

        CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT
                                                            .withDelimiter(';')
                                                            .withFirstRecordAsHeader()
                                                            .withIgnoreHeaderCase());

        List<Comuni> comuni = new ArrayList<Comuni>();

        Iterable<CSVRecord> csvRecords = csvParser.getRecords();

        for (CSVRecord csvRecord : csvRecords){
            Comuni c = new Comuni(
                    Long.parseLong(csvRecord.get("id")),
                    csvRecord.get("name"),
                    csvRecord.get("slug"),
                    Float.parseFloat(csvRecord.get("lat")),
                    Float.parseFloat(csvRecord.get("lng")),
                    Integer.parseInt(csvRecord.get("codice_provincia_istat")),
                    Integer.parseInt(csvRecord.get("codice_comune_istat")),
                    Integer.parseInt(csvRecord.get("codice_alfanumerico_istat")),
                    Boolean.parseBoolean(csvRecord.get("capoluogo_provincia")),
                    Boolean.parseBoolean(csvRecord.get("capoluogo_regione"))
            );

            comuni.add(c);
        }

        return comuni;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse");
        }
    }

}
