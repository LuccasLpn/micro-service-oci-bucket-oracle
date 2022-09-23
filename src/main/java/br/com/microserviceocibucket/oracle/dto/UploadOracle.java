package br.com.microserviceocibucket.oracle.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.InputStream;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UploadOracle {
    private InputStream midia;
    private String nameSpace;
    private String bucketName;
    private String fileName;
}
