package com.tableau.airtable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;


import java.sql.*;
import java.util.Properties;

public class AirtableJDBCTest {

    public Connection getConnection() throws SQLException {
        DriverManager.registerDriver (new com.tableau.airtable.AirtableJDBC());
        Properties props = new Properties();
        if (System.getenv("AIRTABLE_API_KEY") != null)
	    props.put("key", System.getenv("AIRTABLE_API_KEY"));
        props.put("base", "appOQS45OT1iBFN9l");
        return DriverManager.getConnection("jdbc:airtable:https://api.airtable.com/v0/appOQS45OT1iBFN9l/Breeding%20Plants", props);
    }

    public Statement createStatement() throws SQLException {
        Connection con = getConnection();
        return con.createStatement();
    }

    @Test
    public void testConnection() throws SQLException {
        getConnection();
    }


    @Test
    public void testCreateStatement() throws SQLException {
        createStatement();
    }

    @Test
    public void testGetTables() throws SQLException {
	Connection conn = getConnection();
	DatabaseMetaData md = conn.getMetaData();
	ResultSet rs = md.getTables(null, null, "%", null);
	while (rs.next()) {
	    System.out.println("    table = " + rs.getString(3));
	}
	rs.close();
    }


    @Test
    public void testGetSchema() throws SQLException {
	Connection conn = getConnection();
	DatabaseMetaData md = conn.getMetaData();
        ResultSet rs = md.getSchemas();
	System.out.println("List of schemas: "); 
	while(rs.next()) {
	    System.out.println(
			       "   "+rs.getString("TABLE_SCHEM") 
			       + ", "+rs.getString("TABLE_CATALOG")); 
	} 
	rs.close();
    }


    @Test
    public void testExecuteQuery() throws SQLException {
        Statement stmt = createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM \"appOQS45OT1iBFN9l\".\"Breeding%20Plants\" \"Breeding_20Plants\"");
        ResultSetMetaData rsm = rs.getMetaData();
        //assertTrue(rsm.getColumnCount() == 17);
	System.out.println(">>>> rsm.getColumnCount() " + rsm.getColumnCount());
        while(rs.next()) {
            for (int col = 1; col <= rsm.getColumnCount(); col++) {
                Object obj = rs.getObject(col);
                if (obj != null)
                    System.out.print(obj.toString() + "\t");
                else
                    System.out.print("NULL\t");
            }
            System.out.println();
        }
    }

    @Test
    public void testExecuteQuery2() throws SQLException {
        Statement stmt = createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM \"appOQS45OT1iBFN9l\".\"Breeding%20Plants\"");
        ResultSetMetaData rsm = rs.getMetaData();
        //assertTrue(rsm.getColumnCount() == 17);
	System.out.println(">>>> rsm.getColumnCount() " + rsm.getColumnCount());
        while(rs.next()) {
            for (int col = 1; col <= rsm.getColumnCount(); col++) {
                Object obj = rs.getObject(col);
                if (obj != null)
                    System.out.print(obj.toString() + "\t");
                else
                    System.out.print("NULL\t");
            }
            System.out.println();
        }
    }

    /***
   SELECT "Breeding_20Plants"."  Plant Key" AS "X_Plant_Key",
     "Breeding_20Plants"."Active" AS "Active",
     "Breeding_20Plants"."Breeder" AS "Breeder",
     "Breeding_20Plants"."Breeding Plant Lookup" AS "Breeding_Plant_Lookup",
     "Breeding_20Plants"."Color" AS "Color",
     "Breeding_20Plants"."Data Check: Hip Reference" AS "Data_Check__Hip_Reference",
     "Breeding_20Plants"."Data Check: Synced With Lookup" AS "Data_Check__Synced_With_Lookup",
     "Breeding_20Plants"."Hip Key" AS "Hip_Key",
     "Breeding_20Plants"."Petal Count" AS "Petal_Count",
     "Breeding_20Plants"."Picture" AS "Picture",
     "Breeding_20Plants"."Pollen Parent Hips" AS "Pollen_Parent_Hips",
     "Breeding_20Plants"."Pollen Parent" AS "Pollen_Parent",
     "Breeding_20Plants"."Seed Parent Hips" AS "Seed_Parent_Hips",
     "Breeding_20Plants"."Seed Parent" AS "Seed_Parent",
     "Breeding_20Plants"."ShortName" AS "ShortName",
     "Breeding_20Plants"."Species" AS "Species",
     "Breeding_20Plants"."URL" AS "URL",
     "Breeding_20Plants"."id" AS "id"
   FROM "appOQS45OT1iBFN9l"."Breeding%20Plants" "Breeding_20Plants"
    **/

    @Test
    public void testExecuteQuery3() throws SQLException {
        Statement stmt = createStatement();
        ResultSet rs = stmt.executeQuery(
"SELECT \"Breeding_20Plants\".\"  Plant Key\" AS \"X_Plant_Key\", \"Breeding_20Plants\".\"Active\" AS \"Active\",\"Breeding_20Plants\".\"Breeder\" AS \"Breeder\",\"Breeding_20Plants\".\"Breeding Plant Lookup\" AS \"Breeding_Plant_Lookup\",\"Breeding_20Plants\".\"Color\" AS \"Color\",\"Breeding_20Plants\".\"Data Check: Hip Reference\" AS \"Data_Check__Hip_Reference\",\"Breeding_20Plants\".\"Data Check: Synced With Lookup\" AS \"Data_Check__Synced_With_Lookup\",\"Breeding_20Plants\".\"Hip Key\" AS \"Hip_Key\",\"Breeding_20Plants\".\"Petal Count\" AS \"Petal_Count\",\"Breeding_20Plants\".\"Picture\" AS \"Picture\",\"Breeding_20Plants\".\"Pollen Parent Hips\" AS \"Pollen_Parent_Hips\",\"Breeding_20Plants\".\"Pollen Parent\" AS \"Pollen_Parent\",\"Breeding_20Plants\".\"Seed Parent Hips\" AS \"Seed_Parent_Hips\",\"Breeding_20Plants\".\"Seed Parent\" AS \"Seed_Parent\",\"Breeding_20Plants\".\"ShortName\" AS \"ShortName\",\"Breeding_20Plants\".\"Species\" AS \"Species\",\"Breeding_20Plants\".\"URL\" AS \"URL\",\"Breeding_20Plants\".\"id\" AS \"id\" FROM \"appOQS45OT1iBFN9l\".\"Breeding%20Plants\" \"Breeding_20Plants\"");
        ResultSetMetaData rsm = rs.getMetaData();
        //assertTrue(rsm.getColumnCount() == 17);
	System.out.println(">>>> rsm.getColumnCount() " + rsm.getColumnCount());
        while(rs.next()) {
            for (int col = 1; col <= rsm.getColumnCount(); col++) {
                Object obj = rs.getObject(col);
                if (obj != null)
                    System.out.print(obj.toString() + "\t");
                else
                    System.out.print("NULL\t");
            }
            System.out.println();
        }
    }

    @Test
    public void testGetColumns() throws SQLException {
	Connection conn = getConnection();
	DatabaseMetaData md = conn.getMetaData();
        ResultSet rs = md.getColumns("Airtable", "appOQS45OT1iBFN9l", "Breeding%20Plants", "");
	System.out.println("List of columns: "); 
	while(rs.next()) {
	    System.out.println(
			       "   "+rs.getString("TABLE_SCHEM") 
			       + ", "+rs.getString("TABLE_CATALOG")); 
	} 
	rs.close();
    }

}
