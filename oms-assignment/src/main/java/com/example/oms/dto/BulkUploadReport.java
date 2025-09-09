package com.example.oms.dto;

import java.util.ArrayList;
import java.util.List;

public class BulkUploadReport {
    private int totalRows;
    private int successCount;
    private int failureCount;
    private final List<String> errors = new ArrayList<>();

    public void incSuccess(){ successCount++; }
    public void incFailure(){ failureCount++; }
    public void setTotalRows(int totalRows){ this.totalRows = totalRows; }
    public void addError(String e){ errors.add(e); }

    public int getTotalRows(){ return totalRows; }
    public int getSuccessCount(){ return successCount; }
    public int getFailureCount(){ return failureCount; }
    public List<String> getErrors(){ return errors; }
}
