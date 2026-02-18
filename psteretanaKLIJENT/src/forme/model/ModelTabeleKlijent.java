package forme.model;

import domen.Klijent;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

public class ModelTabeleKlijent extends AbstractTableModel {

    private List<Klijent> lista;

    private final String[] kolone = {
        "ID",
        "Ime",
        "Prezime",
        "Datum rođenja",
        "Godine",
        "Pol",
        "Visina",
        "Težina",
        "BMR",
        "Telefon"
    };

    public ModelTabeleKlijent(List<Klijent> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Klijent k = lista.get(rowIndex);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        switch (columnIndex) {
            case 0: return k.getIdKlijent();
            case 1: return k.getIme();
            case 2: return k.getPrezime();
            case 3: return k.getDatumRodjenja() != null ? sdf.format(k.getDatumRodjenja()) : "";
            case 4: return k.getBrojGodina();
            case 5: return k.getPol() != null ? k.getPol().name() : "";
            case 6: return k.getVisina();
            case 7: return k.getTezina();
            case 8: return k.getBMR();
            case 9: return k.getBrojTelefona();
            default: return "N/A";
        }
    }

    public List<Klijent> getLista() {
        return lista;
    }

    public void setLista(List<Klijent> novaLista) {
        this.lista = novaLista;
        fireTableDataChanged();
    }
 
    public void pretrazi(String ime, String prezime, String telefon) {

        List<Klijent> filtrirana = lista.stream()
            .filter(k -> ime == null || ime.isEmpty() ||
                    k.getIme().toLowerCase().contains(ime.toLowerCase()))
            .filter(k -> prezime == null || prezime.isEmpty() ||
                    k.getPrezime().toLowerCase().contains(prezime.toLowerCase()))
            .filter(k -> telefon == null || telefon.isEmpty() ||
                    (k.getBrojTelefona() != null &&
                     k.getBrojTelefona().toLowerCase().contains(telefon.toLowerCase())))
            .collect(Collectors.toList());

        this.lista = filtrirana;
        fireTableDataChanged();
    }
}
