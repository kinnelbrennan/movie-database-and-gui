import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.AbstractTableModel;

public class AwardTableModel extends AbstractTableModel {
    // The result set from the movie table to be modeled
    private ResultSet rs;

    public AwardTableModel(ResultSet rs) {
        this.rs = rs;
    }

    //count the rows in our set
    public int getRowCount() {
        try {
            rs.last();
            return rs.getRow();
        } catch (SQLException e) {
            System.out.println("SQL Exception thrown:");
            e.printStackTrace();
            return 0;
        }
    }

    //we know this from the data in our DB
    public int getColumnCount() {
        return 2;
    }

    public String getColumnName(int column) {
        try {
            String colName = rs.getMetaData().getColumnName(column + 1);
            return colName;
        } catch (SQLException e) {
            System.out.println("SQL Exception thrown");
            e.printStackTrace();
            return "";
        }
    }

    public Object getValueAt(int row, int column) {
        try {
            rs.absolute(row + 1);
            return rs.getObject(column + 1);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
