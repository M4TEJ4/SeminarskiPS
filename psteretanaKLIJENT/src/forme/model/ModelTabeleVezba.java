package forme.model;

import domen.Vezba;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

public class ModelTabeleVezba extends AbstractTableModel {

    private List<Vezba> lista;

    private final String[] kolone = {"ID", "Naziv", "Grupa mišića", "Oprema"};

    public ModelTabeleVezba(List<Vezba> lista) {
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
        Vezba v = lista.get(rowIndex);
        switch (columnIndex) {
            case 0: return v.getIdVezba();
            case 1: return v.getNaziv();
            case 2: return v.getGrupaMisica();
            case 3: return v.getOprema();
            default: return "N/A";
        }
    }

    public List<Vezba> getLista() {
        return lista;
    }

    public void setLista(List<Vezba> novaLista) {
        this.lista = novaLista;
        fireTableDataChanged();
    }

    public void pretrazi(String naziv, String grupaMisica, String oprema) {
        List<Vezba> filtrirana = lista.stream()
                .filter(v -> naziv == null || naziv.isEmpty()
                        || (v.getNaziv() != null && v.getNaziv().toLowerCase().contains(naziv.toLowerCase())))
                .filter(v -> grupaMisica == null || grupaMisica.isEmpty()
                        || (v.getGrupaMisica() != null && v.getGrupaMisica().toLowerCase().contains(grupaMisica.toLowerCase())))
                .filter(v -> oprema == null || oprema.isEmpty()
                        || (v.getOprema() != null && v.getOprema().toLowerCase().contains(oprema.toLowerCase())))
                .collect(Collectors.toList());

        this.lista = filtrirana;
        fireTableDataChanged();
    }
}
