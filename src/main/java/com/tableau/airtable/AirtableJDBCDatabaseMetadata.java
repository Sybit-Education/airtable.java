package com.tableau.airtable;

import com.sybit.airtable.vo.RecordItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AirtableJDBCDatabaseMetadata implements DatabaseMetaData {
    private String base;
    private String defaultTable;
    private Connection conn;

    AirtableJDBCDatabaseMetadata(String base, String defaultTable, Connection conn) {
        this.base = base;
        this.defaultTable = defaultTable;
	this.conn = conn;
    }

    @Override
    public boolean allProceduresAreCallable() throws SQLException {
        return false;
    }

    @Override
    public boolean allTablesAreSelectable() throws SQLException {
        return true;
    }

    @Override
    public String getURL() throws SQLException {
        return null;
    }

    @Override
    public String getUserName() throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    @Override
    public boolean isReadOnly() throws SQLException {
        return true;
    }

    @Override
    public boolean nullsAreSortedHigh() throws SQLException {
        return true;
    }

    @Override
    public boolean nullsAreSortedLow() throws SQLException {
        return false;
    }

    @Override
    public boolean nullsAreSortedAtStart() throws SQLException {
        return true;
    }

    @Override
    public boolean nullsAreSortedAtEnd() throws SQLException {
        return false;
    }

    @Override
    public String getDatabaseProductName() throws SQLException {
        return "Airtable";
    }

    @Override
    public String getDatabaseProductVersion() throws SQLException {
        return "Online";
    }

    @Override
    public String getDriverName() throws SQLException {
        return "AirtableJDBC";
    }

    @Override
    public String getDriverVersion() throws SQLException {
        return "0.0";
    }

    @Override
    public int getDriverMajorVersion() {
        return 0;
    }

    @Override
    public int getDriverMinorVersion() {
        return 0;
    }

    @Override
    public boolean usesLocalFiles() throws SQLException {
        return false;
    }

    @Override
    public boolean usesLocalFilePerTable() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsMixedCaseIdentifiers() throws SQLException {
        return false;
    }

    @Override
    public boolean storesUpperCaseIdentifiers() throws SQLException {
        return false;
    }

    @Override
    public boolean storesLowerCaseIdentifiers() throws SQLException {
        return false;
    }

    @Override
    public boolean storesMixedCaseIdentifiers() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsMixedCaseQuotedIdentifiers() throws SQLException {
        return false;
    }

    @Override
    public boolean storesUpperCaseQuotedIdentifiers() throws SQLException {
        return false;
    }

    @Override
    public boolean storesLowerCaseQuotedIdentifiers() throws SQLException {
        return false;
    }

    @Override
    public boolean storesMixedCaseQuotedIdentifiers() throws SQLException {
        return false;
    }

    @Override
    public String getIdentifierQuoteString() throws SQLException {
        return "";
    }

    @Override
    public String getSQLKeywords() throws SQLException {
        return "";
    }

    @Override
    public String getNumericFunctions() throws SQLException {
        return "";
    }

    @Override
    public String getStringFunctions() throws SQLException {
        return "";
    }

    @Override
    public String getSystemFunctions() throws SQLException {
        return "";
    }

    @Override
    public String getTimeDateFunctions() throws SQLException {
        return "";
    }

    @Override
    public String getSearchStringEscape() throws SQLException {
        return "";
    }

    @Override
    public String getExtraNameCharacters() throws SQLException {
        return "";
    }

    @Override
    public boolean supportsAlterTableWithAddColumn() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsAlterTableWithDropColumn() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsColumnAliasing() throws SQLException {
        return false;
    }

    @Override
    public boolean nullPlusNonNullIsNull() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsConvert() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsConvert(int i, int i1) throws SQLException {
        return false;
    }

    @Override
    public boolean supportsTableCorrelationNames() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsDifferentTableCorrelationNames() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsExpressionsInOrderBy() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsOrderByUnrelated() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsGroupBy() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsGroupByUnrelated() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsGroupByBeyondSelect() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsLikeEscapeClause() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsMultipleResultSets() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsMultipleTransactions() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsNonNullableColumns() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsMinimumSQLGrammar() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsCoreSQLGrammar() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsExtendedSQLGrammar() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsANSI92EntryLevelSQL() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsANSI92IntermediateSQL() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsANSI92FullSQL() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsIntegrityEnhancementFacility() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsOuterJoins() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsFullOuterJoins() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsLimitedOuterJoins() throws SQLException {
        return false;
    }

    @Override
    public String getSchemaTerm() throws SQLException {
        return "";
    }

    @Override
    public String getProcedureTerm() throws SQLException {
        return "";
    }

    @Override
    public String getCatalogTerm() throws SQLException {
        return "";
    }

    @Override
    public boolean isCatalogAtStart() throws SQLException {
        return false;
    }

    @Override
    public String getCatalogSeparator() throws SQLException {
        return null;
    }

    @Override
    public boolean supportsSchemasInDataManipulation() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsSchemasInProcedureCalls() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsSchemasInTableDefinitions() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsSchemasInIndexDefinitions() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsSchemasInPrivilegeDefinitions() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsCatalogsInDataManipulation() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsCatalogsInProcedureCalls() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsCatalogsInTableDefinitions() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsCatalogsInIndexDefinitions() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsCatalogsInPrivilegeDefinitions() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsPositionedDelete() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsPositionedUpdate() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsSelectForUpdate() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsStoredProcedures() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsSubqueriesInComparisons() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsSubqueriesInExists() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsSubqueriesInIns() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsSubqueriesInQuantifieds() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsCorrelatedSubqueries() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsUnion() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsUnionAll() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsOpenCursorsAcrossCommit() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsOpenCursorsAcrossRollback() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsOpenStatementsAcrossCommit() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsOpenStatementsAcrossRollback() throws SQLException {
        return false;
    }

    @Override
    public int getMaxBinaryLiteralLength() throws SQLException {
        return 0;
    }

    @Override
    public int getMaxCharLiteralLength() throws SQLException {
        return 0;
    }

    @Override
    public int getMaxColumnNameLength() throws SQLException {
        return 0;
    }

    @Override
    public int getMaxColumnsInGroupBy() throws SQLException {
        return 0;
    }

    @Override
    public int getMaxColumnsInIndex() throws SQLException {
        return 0;
    }

    @Override
    public int getMaxColumnsInOrderBy() throws SQLException {
        return 0;
    }

    @Override
    public int getMaxColumnsInSelect() throws SQLException {
        return 0;
    }

    @Override
    public int getMaxColumnsInTable() throws SQLException {
        return 0;
    }

    @Override
    public int getMaxConnections() throws SQLException {
        return 0;
    }

    @Override
    public int getMaxCursorNameLength() throws SQLException {
        return 0;
    }

    @Override
    public int getMaxIndexLength() throws SQLException {
        return 0;
    }

    @Override
    public int getMaxSchemaNameLength() throws SQLException {
        return 0;
    }

    @Override
    public int getMaxProcedureNameLength() throws SQLException {
        return 0;
    }

    @Override
    public int getMaxCatalogNameLength() throws SQLException {
        return 0;
    }

    @Override
    public int getMaxRowSize() throws SQLException {
        return 0;
    }

    @Override
    public boolean doesMaxRowSizeIncludeBlobs() throws SQLException {
        return false;
    }

    @Override
    public int getMaxStatementLength() throws SQLException {
        return 0;
    }

    @Override
    public int getMaxStatements() throws SQLException {
        return 0;
    }

    @Override
    public int getMaxTableNameLength() throws SQLException {
        return 0;
    }

    @Override
    public int getMaxTablesInSelect() throws SQLException {
        return 0;
    }

    @Override
    public int getMaxUserNameLength() throws SQLException {
        return 0;
    }

    @Override
    public int getDefaultTransactionIsolation() throws SQLException {
        return 0;
    }

    @Override
    public boolean supportsTransactions() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsTransactionIsolationLevel(int i) throws SQLException {
        return false;
    }

    @Override
    public boolean supportsDataDefinitionAndDataManipulationTransactions() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsDataManipulationTransactionsOnly() throws SQLException {
        return false;
    }

    @Override
    public boolean dataDefinitionCausesTransactionCommit() throws SQLException {
        return false;
    }

    @Override
    public boolean dataDefinitionIgnoredInTransactions() throws SQLException {
        return false;
    }

    @Override
    public ResultSet getProcedures(String s, String s1, String s2) throws SQLException {
	throw new SQLException("getProc");
    }

    @Override
    public ResultSet getProcedureColumns(String s, String s1, String s2, String s3) throws SQLException {
	throw new SQLException("getProcColumns");
    }

    @Override
    public ResultSet getTables(String s, String s1, String s2, String[] strings) throws SQLException {
/***
 *
 * TABLE_CAT String => table catalog (may be null)
 * TABLE_SCHEM String => table schema (may be null)
 * TABLE_NAME String => table name
 * TABLE_TYPE String => table type. Typical types are "TABLE", "VIEW", "SYSTEM TABLE", "GLOBAL TEMPORARY", "LOCAL TEMPORARY", "ALIAS", "SYNONYM".
 * REMARKS String => explanatory comment on the table
 * TYPE_CAT String => the types catalog (may be null)
 * TYPE_SCHEM String => the types schema (may be null)
 * TYPE_NAME String => type name (may be null)
 * SELF_REFERENCING_COL_NAME String => name of the designated "identifier" column of a typed table (may be null)
 * REF_GENERATION String => specifies how values in SELF_REFERENCING_COL_NAME are created. Values are "SYSTEM", "USER", "DERIVED". (may be null)
 */
        RecordItem item = new RecordItem();
        item.setFields(Stream.of(new String[][] {
                { "TABLE_CAT", "Airtable" },
                { "TABLE_SCHEM", base },
                { "TABLE_NAME", defaultTable },
                { "TABLE_TYPE", "TABLE"},
                { "REMARKS", ""},
		}).collect(Collectors.toMap(data -> data[0], data -> data[1], 
					    (u, v) -> {
						throw new IllegalStateException(String.format("Duplicate key %s", u));
					    },
					    LinkedHashMap::new)));
        item.setCreatedTime((new Timestamp(System.currentTimeMillis())).toString());
        item.setId("1");
        return new AirtableJDBCResultSet(Collections.singletonList(item), null);
    }

    @Override
    public ResultSet getSchemas() throws SQLException {
	// TABLE_SCHEM String => schema name
	// TABLE_CATALOG String => catalog name (may be null)
        RecordItem item = new RecordItem();
        item.setFields(Stream.of(new String[][] {
                { "TABLE_SCHEM", base },
                { "TABLE_CATALOG", "Airtable" },
		}).collect(Collectors.toMap(data -> data[0], data -> data[1], 
					    (u, v) -> {
						throw new IllegalStateException(String.format("Duplicate key %s", u));
					    },
					    LinkedHashMap::new)));

        item.setCreatedTime((new Timestamp(System.currentTimeMillis())).toString());
        item.setId("1");
        return new AirtableJDBCResultSet(Collections.singletonList(item), null);
    }

    @Override
    public ResultSet getCatalogs() throws SQLException {
	/// TABLE_CAT String => catalog name
        RecordItem item = new RecordItem();
        item.setFields(Stream.of(new String[][] {
                { "TABLE_CAT", "Airtable" },
		}).collect(Collectors.toMap(data -> data[0], data -> data[1], 
					    (u, v) -> {
						throw new IllegalStateException(String.format("Duplicate key %s", u));
					    },
					    LinkedHashMap::new)));

        item.setCreatedTime((new Timestamp(System.currentTimeMillis())).toString());
        item.setId("1");
        return new AirtableJDBCResultSet(Collections.singletonList(item), null);
    }

    @Override
    public ResultSet getTableTypes() throws SQLException {
	throw new SQLException("getTableTypes");
    }

    @Override
    public ResultSet getColumns(String catalog, String schemaPattern, String tableNamePattern, String columnNamePattern) throws SQLException {
	/**
	    TABLE_CAT String => table catalog (may be null)
	    TABLE_SCHEM String => table schema (may be null)
	    TABLE_NAME String => table name
	    COLUMN_NAME String => column name
	    DATA_TYPE int => SQL type from java.sql.Types
	    TYPE_NAME String => Data source dependent type name, for a UDT the type name is fully qualified
	    COLUMN_SIZE int => column size.
	    BUFFER_LENGTH is not used.
	    DECIMAL_DIGITS int => the number of fractional digits. Null is returned for data types where DECIMAL_DIGITS is not applicable.
	    NUM_PREC_RADIX int => Radix (typically either 10 or 2)
	    NULLABLE int => is NULL allowed.
	              columnNoNulls - might not allow NULL values
	              columnNullable - definitely allows NULL values
	              columnNullableUnknown - nullability unknown
	    REMARKS String => comment describing column (may be null)
	    COLUMN_DEF String => default value for the column, which should be interpreted as a string when the value is enclosed in single quotes (may be null)
	    SQL_DATA_TYPE int => unused
	    SQL_DATETIME_SUB int => unused
	    CHAR_OCTET_LENGTH int => for char types the maximum number of bytes in the column
	    ORDINAL_POSITION int => index of column in table (starting at 1)
	    IS_NULLABLE String => ISO rules are used to determine the nullability for a column.
	              YES --- if the column can include NULLs
	              NO --- if the column cannot include NULLs
	    empty string --- if the nullability for the column is unknown
	    SCOPE_CATALOG String => catalog of table that is the scope of a reference attribute (null if DATA_TYPE isn't REF)
	    SCOPE_SCHEMA String => schema of table that is the scope of a reference attribute (null if the DATA_TYPE isn't REF)
	    SCOPE_TABLE String => table name that this the scope of a reference attribute (null if the DATA_TYPE isn't REF)
	    SOURCE_DATA_TYPE short => source type of a distinct type or user-generated Ref type, SQL type from java.sql.Types (null if DATA_TYPE isn't DISTINCT or user-generated REF)
	    IS_AUTOINCREMENT String => Indicates whether this column is auto incremented
	              YES --- if the column is auto incremented
	              NO --- if the column is not auto incremented
	              empty string --- if it cannot be determined whether the column is auto incremented
	    IS_GENERATEDCOLUMN String => Indicates whether this is a generated column
	              YES --- if this a generated column
	              NO --- if this not a generated column
	              empty string --- if it cannot be determined whether this is a generated column
	**/
	Statement stmt = conn.createStatement();
        // Downloads all the data. Grossly inefficient
	ResultSet rs = stmt.executeQuery("SELECT * FROM \"" + schemaPattern + "\".\"" + tableNamePattern + "\"");
	ResultSetMetaData rsmd = rs.getMetaData();
	int columnCount = rsmd.getColumnCount();
	LinkedList<RecordItem> columnData = new LinkedList<RecordItem>();
	for (int i = 1; i <= columnCount; i++) {
	    LinkedHashMap<String, Object> columnMetaData = new LinkedHashMap<String, Object>();
	    columnMetaData.put("TABLE_CAT", "Airtable");
	    columnMetaData.put("TABLE_SCHEM", schemaPattern);
	    columnMetaData.put("TABLE_NAME", tableNamePattern);
	    columnMetaData.put("COLUMN_NAME", rsmd.getColumnName(i));
	    columnMetaData.put("DATA_TYPE", rsmd.getColumnType(i));
	    columnMetaData.put("TYPE_NAME", rsmd.getColumnTypeName(i));
	    columnMetaData.put("COLUMN_SIZE", 0); // TODO
	    columnMetaData.put("BUFFER_LENGTH", 0); // TODO
	    columnMetaData.put("DECIMAL_DIGITS", 0); // TODO
	    columnMetaData.put("NUM_PREC_RADIX", 0); // TODO
	    columnMetaData.put("NULLABLE", rsmd.isNullable(i)); // TODO
	    columnMetaData.put("REMARKS", null); // TODO
	    columnMetaData.put("COLUMN_DEF", null); // TODO
	    columnMetaData.put("SQL_DATA_TYPE", null); // TODO
	    columnMetaData.put("SQL_DATETIME_SUB", null); // TODO
	    columnMetaData.put("CHAR_OCTET_LENGTH", null); // TODO
	    columnMetaData.put("ORDINAL_POSITION", i);
	    columnMetaData.put("IS_NULLABLE", "YES");
	    columnMetaData.put("SCOPE_CATALOG", null);
	    columnMetaData.put("SCOPE_SCHEMA", null);
	    columnMetaData.put("SCOPE_TABLE", null);
	    columnMetaData.put("SOURCE_DATA_TYPE", null);
	    columnMetaData.put("IS_AUTOINCREMENT", "NO");
	    columnMetaData.put("IS_GENERATEDCOLUMN", "NO");
	    RecordItem ri = new RecordItem();
	    ri.setFields(columnMetaData);
	    columnData.add(ri);
	}
	return new AirtableJDBCResultSet(columnData, null);
    }

    @Override
    public ResultSet getColumnPrivileges(String s, String s1, String s2, String s3) throws SQLException {
	throw new SQLException("getColumnPriv");
    }

    @Override
    public ResultSet getTablePrivileges(String s, String s1, String s2) throws SQLException {
	throw new SQLException("getColumnPriv2");
    }

    @Override
    public ResultSet getBestRowIdentifier(String s, String s1, String s2, int i, boolean b) throws SQLException {
	throw new SQLException("getBestRowId");
    }

    @Override
    public ResultSet getVersionColumns(String s, String s1, String s2) throws SQLException {
	throw new SQLException("getVersionColumns");
    }

    @Override
    public ResultSet getPrimaryKeys(String s, String s1, String s2) throws SQLException {
	return new AirtableJDBCResultSet();
    }

    @Override
    public ResultSet getImportedKeys(String s, String s1, String s2) throws SQLException {
	return new AirtableJDBCResultSet();
    }

    @Override
    public ResultSet getExportedKeys(String s, String s1, String s2) throws SQLException {
	throw new SQLException("getExportedKeys");
    }

    @Override
    public ResultSet getCrossReference(String s, String s1, String s2, String s3, String s4, String s5) throws SQLException {
	throw new SQLException("getCrossReference");
    }

    @Override
    public ResultSet getTypeInfo() throws SQLException {
	throw new SQLException("getTypeInfo");
    }

    @Override
    public ResultSet getIndexInfo(String s, String s1, String s2, boolean b, boolean b1) throws SQLException {
	throw new SQLException("getIndexInfo");
    }

    @Override
    public boolean supportsResultSetType(int i) throws SQLException {
        return false;
    }

    @Override
    public boolean supportsResultSetConcurrency(int i, int i1) throws SQLException {
        return false;
    }

    @Override
    public boolean ownUpdatesAreVisible(int i) throws SQLException {
        return false;
    }

    @Override
    public boolean ownDeletesAreVisible(int i) throws SQLException {
        return false;
    }

    @Override
    public boolean ownInsertsAreVisible(int i) throws SQLException {
        return false;
    }

    @Override
    public boolean othersUpdatesAreVisible(int i) throws SQLException {
        return false;
    }

    @Override
    public boolean othersDeletesAreVisible(int i) throws SQLException {
        return false;
    }

    @Override
    public boolean othersInsertsAreVisible(int i) throws SQLException {
        return false;
    }

    @Override
    public boolean updatesAreDetected(int i) throws SQLException {
        return false;
    }

    @Override
    public boolean deletesAreDetected(int i) throws SQLException {
        return false;
    }

    @Override
    public boolean insertsAreDetected(int i) throws SQLException {
        return false;
    }

    @Override
    public boolean supportsBatchUpdates() throws SQLException {
        return false;
    }

    @Override
    public ResultSet getUDTs(String s, String s1, String s2, int[] ints) throws SQLException {
	throw new SQLException("getUDTs");
    }

    @Override
    public Connection getConnection() throws SQLException {
        return conn;
    }

    @Override
    public boolean supportsSavepoints() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsNamedParameters() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsMultipleOpenResults() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsGetGeneratedKeys() throws SQLException {
        return false;
    }

    @Override
    public ResultSet getSuperTypes(String s, String s1, String s2) throws SQLException {
        return new AirtableJDBCResultSet();
    }

    @Override
    public ResultSet getSuperTables(String s, String s1, String s2) throws SQLException {
        return new AirtableJDBCResultSet();
    }

    @Override
    public ResultSet getAttributes(String s, String s1, String s2, String s3) throws SQLException {
        return new AirtableJDBCResultSet();
    }

    @Override
    public boolean supportsResultSetHoldability(int i) throws SQLException {
        return false;
    }

    @Override
    public int getResultSetHoldability() throws SQLException {
        return 0;
    }

    @Override
    public int getDatabaseMajorVersion() throws SQLException {
        return 1;
    }

    @Override
    public int getDatabaseMinorVersion() throws SQLException {
        return 0;
    }

    @Override
    public int getJDBCMajorVersion() throws SQLException {
        return 4;
    }

    @Override
    public int getJDBCMinorVersion() throws SQLException {
        return 0;
    }

    @Override
    public int getSQLStateType() throws SQLException {
        return 0;
    }

    @Override
    public boolean locatorsUpdateCopy() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsStatementPooling() throws SQLException {
        return false;
    }

    @Override
    public RowIdLifetime getRowIdLifetime() throws SQLException {
        return null;
    }

    @Override
    public ResultSet getSchemas(String s, String s1) throws SQLException {
        return new AirtableJDBCResultSet();
    }

    @Override
    public boolean supportsStoredFunctionsUsingCallSyntax() throws SQLException {
        return false;
    }

    @Override
    public boolean autoCommitFailureClosesAllResultSets() throws SQLException {
        return false;
    }

    @Override
    public ResultSet getClientInfoProperties() throws SQLException {
        return new AirtableJDBCResultSet();
    }

    @Override
    public ResultSet getFunctions(String s, String s1, String s2) throws SQLException {
        return new AirtableJDBCResultSet();
    }

    @Override
    public ResultSet getFunctionColumns(String s, String s1, String s2, String s3) throws SQLException {
        return new AirtableJDBCResultSet();
    }

    @Override
    public ResultSet getPseudoColumns(String catalog, String schemaPattern, String tableNamePattern, String columnNamePattern) throws SQLException {
        return new AirtableJDBCResultSet();
    }

    @Override
    public boolean generatedKeyAlwaysReturned() throws SQLException {
        return false;
    }

    @Override
    public long getMaxLogicalLobSize() throws SQLException {
        return 0;
    }

    @Override
    public boolean supportsRefCursors() throws SQLException {
        return false;
    }

    @Override
    public boolean supportsSharding() throws SQLException {
        return false;
    }

    @Override
    public <T> T unwrap(Class<T> aClass) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> aClass) throws SQLException {
        return false;
    }
}
