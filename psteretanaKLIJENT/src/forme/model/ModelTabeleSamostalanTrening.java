package forme.model;

import domen.SamostalanTrening;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelTabeleSamostalanTrening extends AbstractTableModel {

    private List<SamostalanTrening> lista;

    private final String[] kolone = {
        "Klijent",
        "Teretana",
        "Kardio"
    };

    public ModelTabeleSamostalanTrening(List<SamostalanTrening> lista) {
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
        SamostalanTrening s = lista.get(rowIndex);

        switch (columnIndex) {
            case 0: return s.getKlijent(); 
            case 1: return s.isTeretana() ? "DA" : "NE";
            case 2: return s.isKardio() ? "DA" : "NE";
            default: return "N/A";
        }
    }

    public List<SamostalanTrening> getLista() {
        return lista;
    }

    public void setLista(List<SamostalanTrening> novaLista) {
        this.lista = novaLista;
        fireTableDataChanged();
    }
}
