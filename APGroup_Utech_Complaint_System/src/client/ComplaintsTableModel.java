package client;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import server.Complaint;
import server.ComplaintCRUDImplement;

class ComplaintsTableModel extends AbstractTableModel {
    private List<Complaint> complaints;
    private String[] columnNames = {"ID", "Category", "Description", "Status", "Advisor", "Created At", "Updated At"};

    public ComplaintsTableModel(List<Complaint> complaints) {
        this.complaints = complaints;
    }

    @Override
    public int getRowCount() {
        return complaints.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Complaint complaint = complaints.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return complaint.getComplaintId();
            case 1:
                return complaint.getCategory();
            case 2:
                return complaint.getDescription();
            case 3:
                return complaint.getStatus();
            case 4:
                return complaint.getStaffId();
            case 5:
                return complaint.getCreateAt();
            case 6:
                return complaint.getUpdatedAt();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}