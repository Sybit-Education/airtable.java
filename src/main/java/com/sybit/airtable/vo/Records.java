package com.sybit.airtable.vo;

import java.util.List;
import java.util.Map;

/**
 * Created by Stephan on 07.03.2017.
 */
public class Records {

    private List<Map<String, Object>> records;

    public List<Map<String, Object>> getRecords() {
        return records;
    }

    public void setRecords(List<Map<String, Object>> records) {
        this.records = records;
    }

}
