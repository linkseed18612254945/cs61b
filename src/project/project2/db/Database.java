package project.project2.db;

import project.project2.Parse;


import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Database {
    private static final String tableStorePath = "C:\\Users\\51694\\cs61b\\src\\project\\project2\\tables";

    private Map<String, Table> tables;

    public Database()
    {
        tables = new HashMap<>();
    }

    public void createTable(String tableName, String[] colNames, String[] colTypes)
    {
        Table newTable = new Table(tableName, colNames, colTypes);
        tables.put(tableName, newTable);
    }

    public void createTableBySelect(String tableName, String[] colNames, String[] selectedTableNames, String[] condition)
    {

    }

    public void insertTable(String tableName, String[] values)
    {
        Table t = tables.get(tableName);
        t.insertRow(values);
    }

    public void printTable(String tableName)
    {
        Table t = tables.get(tableName);
        System.out.println(t);
    }

    public void storeTable(String tableName)
    {
        Table t = tables.get(tableName);
        String path = tableStorePath + "\\" + tableName + ".tbl";
        try
        {
            FileWriter fw = new FileWriter(path);
            fw.write(t.toString());
            fw.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    void storeTable()
    {
        for (String key: tables.keySet())
        {
            storeTable(key);
        }
    }

    /** A table read helper method, get the column names and types */
    private String[][] getColNameAndType(String firstLine)
    {
        String[][] nameAndTypes = new String[2][];
        String[] cols = firstLine.split(",");
        int colNum = cols.length;
        String[] colNames = new String[colNum];
        String[] colTypes = new String[colNum];
        for (int i = 0; i < colNum; i += 1)
        {
            String[] col = cols[i].split(" ");
            colNames[i] = col[0];
            colTypes[i] = col[1];
        }
        nameAndTypes[0] = colNames;
        nameAndTypes[1] = colTypes;
        return nameAndTypes;
    }

    public void loadTable(String tableName)
    {
        try
        {
            String path = tableStorePath + "\\" + tableName + ".tbl";
            String tableString = new String(Files.readAllBytes(Paths.get(path))).trim();
            String[] lines = tableString.split("\n");
            String[][] colNameTypes = getColNameAndType(lines[0]);
            String[] colNames = colNameTypes[0];
            String[] colTypes = colNameTypes[1];
            createTable(tableName, colNames, colTypes);
            for (int i = 1; i < lines.length; i += 1)
            {
                String[] values = lines[i].split(",");
                insertTable(tableName, values);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void selectTables(String[] colExpressions, String[] joinTables, String[] condition)
    {

    }

    public Map<String, Table> getTables()
    {
        return tables;
    }

    public void dropTable(String tableName)
    {
        tables.remove(tableName);
    }

    public String transact(String query)
    {
        Parse dbParser = new Parse(this);
        dbParser.eval(query);
        return "";
    }
}
