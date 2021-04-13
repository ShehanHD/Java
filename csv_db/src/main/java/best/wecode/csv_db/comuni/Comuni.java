package best.wecode.csv_db.comuni;

import javax.persistence.*;

@Entity
@Table
public class Comuni {
    @Id
    @SequenceGenerator(
            name = "comuni_sequence",
            sequenceName = "comuni_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "comuni_sequence"
    )
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String slug;
    @Column(precision=10, scale=2, nullable = false)
    private Float lat;
    @Column(precision=10, scale=2, nullable = false)
    private Float lng;
    @Column(nullable = false)
    private Integer codice_provincia_istat;
    @Column(nullable = false)
    private Integer codice_comune_istat;
    @Column(nullable = false)
    private Integer codice_alfanumerico_istat;
    @Column(nullable = false)
    private Boolean capoluogo_provincia;
    @Column(nullable = false)
    private Boolean capoluogo_regione;

    public Comuni() {
    }

    public Comuni(Long id, String name, String slug, Float lat, Float lng, Integer codice_provincia_istat, Integer codice_comune_istat, Integer codice_alfanumerico_istat, Boolean capoluogo_provincia, Boolean capoluogo_regione) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.lat = lat;
        this.lng = lng;
        this.codice_provincia_istat = codice_provincia_istat;
        this.codice_comune_istat = codice_comune_istat;
        this.codice_alfanumerico_istat = codice_alfanumerico_istat;
        this.capoluogo_provincia = capoluogo_provincia;
        this.capoluogo_regione = capoluogo_regione;
    }

    public Comuni(String name, String slug, Float lat, Float lng, Integer codice_provincia_istat, Integer codice_comune_istat, Integer codice_alfanumerico_istat, Boolean capoluogo_provincia, Boolean capoluogo_regione) {
        this.name = name;
        this.slug = slug;
        this.lat = lat;
        this.lng = lng;
        this.codice_provincia_istat = codice_provincia_istat;
        this.codice_comune_istat = codice_comune_istat;
        this.codice_alfanumerico_istat = codice_alfanumerico_istat;
        this.capoluogo_provincia = capoluogo_provincia;
        this.capoluogo_regione = capoluogo_regione;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLng() {
        return lng;
    }

    public void setLng(Float lng) {
        this.lng = lng;
    }

    public Integer getCodice_provincia_istat() {
        return codice_provincia_istat;
    }

    public void setCodice_provincia_istat(Integer codice_provincia_istat) {
        this.codice_provincia_istat = codice_provincia_istat;
    }

    public Integer getCodice_comune_istat() {
        return codice_comune_istat;
    }

    public void setCodice_comune_istat(Integer codice_comune_istat) {
        this.codice_comune_istat = codice_comune_istat;
    }

    public Integer getCodice_alfanumerico_istat() {
        return codice_alfanumerico_istat;
    }

    public void setCodice_alfanumerico_istat(Integer codice_alfanumerico_istat) {
        this.codice_alfanumerico_istat = codice_alfanumerico_istat;
    }

    public Boolean getCapoluogo_provincia() {
        return capoluogo_provincia;
    }

    public void setCapoluogo_provincia(Boolean capoluogo_provincia) {
        this.capoluogo_provincia = capoluogo_provincia;
    }

    public Boolean getCapoluogo_regione() {
        return capoluogo_regione;
    }

    public void setCapoluogo_regione(Boolean capoluogo_regione) {
        this.capoluogo_regione = capoluogo_regione;
    }

    @Override
    public String toString() {
        return "Comuni{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", slug='" + slug + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                ", codice_provincia_istat=" + codice_provincia_istat +
                ", codice_comune_istat=" + codice_comune_istat +
                ", codice_alfanumerico_istat=" + codice_alfanumerico_istat +
                ", capoluogo_provincia=" + capoluogo_provincia +
                ", capoluogo_regione=" + capoluogo_regione +
                '}';
    }
}
