package com.example.oms.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UploadResponse {
    private int totalRows;
    private int successCount;
    private int failureCount;
    private List<String> errors;
  
}
