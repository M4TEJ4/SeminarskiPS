package forme.model;

import domen.TreningSaTrenerom;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelTabeleTreningSaTrenerom extends AbstractTableModel {

    private List<TreningSaTrenerom> lista;

    private final String[] kolone = {
        "Klijent",
        "Nivo podrške",
        "Zdravstveno stanje"
    };

    public ModelTabeleTreningSaTrenerom(List<TreningSaTrenerom> lista) {
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
        TreningSaTrenerom t = lista.get(rowIndex);
        switch (columnIndex) {
            case 0: return t.getKlijent();
            case 1: return t.getNivoPodrske();
            case 2: return t.getZdravstvenoStanje();
            default: return "N/A";
        }
    }

    public List<TreningSaTrenerom> getLista() {
        return lista;
    }

    public void setLista(List<TreningSaTrenerom> novaLista) {
        this.lista = novaLista;
        fireTableDataChanged();
    }
}
