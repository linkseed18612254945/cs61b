package project.project2.db;


import simpleTools.EasyString;

/**
 * Created by 51694 on 2017/3/27.
 */
public class Table
{
    //private final int columnNum;
    private int rowNum, colNum;
    private String tableName;
    private String[] colNames;
    private String[] colTypes;
    private Column[] columns;

    public Table(String name, String[] colNames, String[] colTypes)
    {
        tableName = name;
        colNum = colNames.length;
        rowNum = 0;
        this.colNames = colNames;
        this.colTypes = colTypes;
        columns = new Column[colNum];
        createCols();
    }

    private void createCols()
    {
        for (int i = 0; i < colNum; i += 1)
        {
            if (colTypes[i].equals("int"))
            {
                Column<Integer> col = new Column<>(colNames[i], colTypes[i]);
                columns[i] = col;
            }
            else if (colTypes[i].equals("string"))
            {
                Column<String> col = new Column<>(colNames[i], colTypes[i]);
                columns[i] = col;
            }
            else if (colTypes[i].equals("float"))
            {
                Column<Float> col = new Column<>(colNames[i], colTypes[i]);
                columns[i] = col;
            }
            else
            {
                return;
            }
        }
    }

    public String getTableName()
    {
        return tableName;
    }

    public void insertRow(String[] values)
    {
        if (values.length != colNum)
        {
            throw new ArrayIndexOutOfBoundsException("Insert with wrong column number.");
        }
        for (int i = 0; i < colNum; i += 1)
        {
            columns[i].insert(values[i]);
        }
        rowNum += 1;
    }


    public String getColNames()
    {
        return EasyString.pyStyleJoin(colNames, ' ', colTypes, ',');
    }

    public String toString()
    {
        String tableString = "";
        tableString += getColNames() + "\n";
        for (int i = 0; i < rowNum; i += 1)
        {
            for (int j = 0; j < colNum; j += 1)
            {
                if (j == colNum - 1)
                {
                    tableString += columns[j].getItems().get(i);
                    break;
                }
                tableString += columns[j].getItems().get(i) + ",";
            }

            tableString = tableString + "\n";
        }
        return tableString;
    }
}
