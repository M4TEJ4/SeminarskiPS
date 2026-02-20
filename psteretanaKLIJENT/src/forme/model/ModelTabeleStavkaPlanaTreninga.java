package forme.model;

import domen.StavkaPlanaTreninga;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelTabeleStavkaPlanaTreninga extends AbstractTableModel {

    private final List<StavkaPlanaTreninga> lista;

    private final String[] kolone = {
        "Rb",
        "Vežba",
        "Serija",
        "Ponavljanja",
        "Napomena"
    };

    public ModelTabeleStavkaPlanaTreninga(List<StavkaPlanaTreninga> lista) {
        this.lista = (lista == null) ? new ArrayList<>() : lista;
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
        StavkaPlanaTreninga s = lista.get(rowIndex);

        switch (columnIndex) {
            case 0: return s.getRb();
            case 1: return s.getVezba() != null ? s.getVezba().getNaziv() : "";
            case 2: return s.getBrojSerija();
            case 3: return s.getBrojPonavljanja();
            case 4: return s.getNapomena();
            default: return "N/A";
        }
    }

    public List<StavkaPlanaTreninga> getLista() {
        return lista;
    }

    public void dodaj(StavkaPlanaTreninga s) {
        lista.add(s);
        resequence();
        fireTableDataChanged();
    }

    public void obrisi(int row) {
        lista.remove(row);
        resequence();
        fireTableDataChanged();
    }

    public void setLista(List<StavkaPlanaTreninga> nova) {
        lista.clear();
        if (nova != null) lista.addAll(nova);
        resequence();
        fireTableDataChanged();
    }

    private void resequence() {
        int rb = 1;
        for (StavkaPlanaTreninga s : lista) {
            s.setRb(rb++);
        }
    }
}