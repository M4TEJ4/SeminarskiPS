package forme.model;

import domen.PlanTreninga;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelTabelePlanTreninga extends AbstractTableModel {

    private List<PlanTreninga> lista;

    private final String[] kolone = {
        "ID",
        "Početak",
        "Kraj",
        "Treninga nedeljno",
        "Ukupno vežbi",
        "Faktor akt.",
        "Kalorije/dan",
        "Trener",
        "Klijent"
    };

    public ModelTabelePlanTreninga(List<PlanTreninga> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista == null ? 0 : lista.size();
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
        PlanTreninga p = lista.get(rowIndex);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        switch (columnIndex) {
            case 0: return p.getIdPlanTreninga();
            case 1: return p.getDatumPocetka() != null ? sdf.format(p.getDatumPocetka()) : "";
            case 2: return p.getDatumKraja() != null ? sdf.format(p.getDatumKraja()) : "";
            case 3: return p.getBrojTreningaNedeljno();
            case 4: return p.getUkupanBrojVezbi();
            case 5: return p.getFaktorAktivnosti();
            case 6: return p.getDnevniUnosKalorija();
            case 7:
                return p.getTrener() != null ? (p.getTrener().getIme() + " " + p.getTrener().getPrezime()) : "";
            case 8:
                return p.getKlijent() != null ? (p.getKlijent().getIme() + " " + p.getKlijent().getPrezime()) : "";
            default: return "N/A";
        }
    }

    public List<PlanTreninga> getLista() {
        return lista;
    }

    public void setLista(List<PlanTreninga> novaLista) {
        this.lista = novaLista;
        fireTableDataChanged();
    }
}