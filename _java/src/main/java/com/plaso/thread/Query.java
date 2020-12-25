package com.plaso.thread;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Query {
}


interface RowHandle<T>{
    T handle(ResultSet rs);
}

class RecordQuery{
    private final Connection conn;

    public RecordQuery(Connection conn){
        this.conn = conn;
    }

    public <T> T query(RowHandle<T> handle, String sql, Object... params) throws SQLException {
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            int index = 1;
            for (Object param : params){
                stmt.setObject(index++, param);
            }
            ResultSet resultSet = stmt.executeQuery();
            return handle.handle(resultSet);
        }
    }
}
