package com.sybit.airtable;

import com.sybit.airtable.exception.AirtableException;

import java.sql.*;
import java.util.Map;
import java.util.Properties;

public class AirtableJDBCConnection implements Connection {

    private Airtable at;
    private Base base;

    public AirtableJDBCConnection(String url, String base, String apiKey) throws AirtableException {
        at = new Airtable();
        at.configure(new Configuration(apiKey, url, null));
        this.base = new Base(base, at);
    }

    @Override
    public Statement createStatement() throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement prepareStatement(String s) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    @Override
    public CallableStatement prepareCall(String s) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    @Override
    public String nativeSQL(String s) throws SQLException {
        return null;
    }

    @Override
    public void setAutoCommit(boolean b) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    @Override
    public boolean getAutoCommit() throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    @Override
    public void commit() throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    @Override
    public void rollback() throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    @Override
    public void close() throws SQLException {

    }

    @Override
    public boolean isClosed() throws SQLException {
        return false;
    }

    @Override
    public DatabaseMetaData getMetaData() throws SQLException {
        return null;
    }

    @Override
    public void setReadOnly(boolean b) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    @Override
    public boolean isReadOnly() throws SQLException {
        return true;
    }

    @Override
    public void setCatalog(String database) throws SQLException {
        this.base = new Base(database, at);
    }

    @Override
    public String getCatalog() throws SQLException {
        return null;
    }

    @Override
    public void setTransactionIsolation(int i) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    @Override
    public int getTransactionIsolation() throws SQLException {
        return 0;
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    @Override
    public void clearWarnings() throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    @Override
    public Statement createStatement(int i, int i1) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement prepareStatement(String s, int i, int i1) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    @Override
    public CallableStatement prepareCall(String s, int i, int i1) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    @Override
    public Map<String, Class<?>> getTypeMap() throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    @Override
    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    @Override
    public void setHoldability(int i) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    @Override
    public int getHoldability() throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    @Override
    public Savepoint setSavepoint() throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    @Override
    public Savepoint setSavepoint(String s) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    @Override
    public void rollback(Savepoint savepoint) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    @Override
    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    @Override
    public Statement createStatement(int i, int i1, int i2) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement prepareStatement(String s, int i, int i1, int i2) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    @Override
    public CallableStatement prepareCall(String s, int i, int i1, int i2) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    @Override
    public PreparedStatement prepareStatement(String s, int i) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    @Override
    public PreparedStatement prepareStatement(String s, int[] ints) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    @Override
    public PreparedStatement prepareStatement(String s, String[] strings) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    @Override
    public Clob createClob() throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    @Override
    public Blob createBlob() throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    @Override
    public NClob createNClob() throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    @Override
    public SQLXML createSQLXML() throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    @Override
    public boolean isValid(int i) throws SQLException {
        return false;
    }

    @Override
    public void setClientInfo(String s, String s1) throws SQLClientInfoException {
        throw new SQLClientInfoException();
    }

    @Override
    public void setClientInfo(Properties properties) throws SQLClientInfoException {
        throw new SQLClientInfoException();
    }

    @Override
    public String getClientInfo(String s) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    @Override
    public Properties getClientInfo() throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    @Override
    public Array createArrayOf(String s, Object[] objects) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    @Override
    public Struct createStruct(String s, Object[] objects) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    @Override
    public <T> T unwrap(Class<T> aClass) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }

    @Override
    public boolean isWrapperFor(Class<?> aClass) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }
}