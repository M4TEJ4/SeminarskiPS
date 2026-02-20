package forme.model;

import domen.Trener;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

public class ModelTabeleTrener extends AbstractTableModel {

    private List<Trener> lista;

    private final String[] kolone = {
        "ID",
        "Ime",
        "Prezime",
        "Korisničko ime"
    };

    public ModelTabeleTrener(List<Trener> lista) {
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
        Trener t = lista.get(rowIndex);

        switch (columnIndex) {
            case 0: return t.getIdTrener();
            case 1: return t.getIme();
            case 2: return t.getPrezime();
            case 3: return t.getKorisnickoIme();
            default: return "N/A";
        }
    }

    public List<Trener> getLista() {
        return lista;
    }

    public void setLista(List<Trener> novaLista) {
        this.lista = novaLista;
        fireTableDataChanged();
    }

    public void pretrazi(String ime, String prezime, String korisnickoIme) {
        List<Trener> filtrirana = lista.stream()
            .filter(t -> ime == null || ime.isEmpty() ||
                    (t.getIme() != null && t.getIme().toLowerCase().contains(ime.toLowerCase())))
            .filter(t -> prezime == null || prezime.isEmpty() ||
                    (t.getPrezime() != null && t.getPrezime().toLowerCase().contains(prezime.toLowerCase())))
            .filter(t -> korisnickoIme == null || korisnickoIme.isEmpty() ||
                    (t.getKorisnickoIme() != null && t.getKorisnickoIme().toLowerCase().contains(korisnickoIme.toLowerCase())))
            .collect(Collectors.toList());

        this.lista = filtrirana;
        fireTableDataChanged();
    }
}