package br.com.microserviceocibucket.oracle.config;


import br.com.microserviceocibucket.oracle.dto.UploadOracle;
import com.oracle.bmc.ConfigFileReader;
import com.oracle.bmc.auth.ConfigFileAuthenticationDetailsProvider;
import com.oracle.bmc.objectstorage.ObjectStorage;
import com.oracle.bmc.objectstorage.ObjectStorageClient;
import com.oracle.bmc.objectstorage.requests.PutObjectRequest;
import com.oracle.bmc.objectstorage.transfer.UploadConfiguration;
import com.oracle.bmc.objectstorage.transfer.UploadManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.io.IOException;
@Component
public class OracleConfig {

    @Value("${oracle.file}")
    private String configurationFilePath;
    private ObjectStorage client;
    private ConfigFileReader.ConfigFile configFile;
    private ConfigFileAuthenticationDetailsProvider provider;

    @PostConstruct
    void init() throws IOException {
        configFile = ConfigFileReader.parse(configurationFilePath, "DEFAULT");
        provider = new ConfigFileAuthenticationDetailsProvider(configFile);
        client = new ObjectStorageClient(provider);
    }
    private UploadConfiguration uploadConfiguration(){
        return UploadConfiguration
                .builder()
                .allowMultipartUploads(true)
                .allowParallelUploads(true)
                .build();
    }
    private UploadManager uploadManager(){
        
        return new UploadManager(client, uploadConfiguration());
    }

    public void uploadBucket(UploadOracle uploadOracle) throws IOException {
        var request = PutObjectRequest.builder()
                .bucketName(uploadOracle.getBucketName())
                .namespaceName(uploadOracle.getNameSpace())
                .objectName(uploadOracle.getFileName())
                .build();
        var uploadDetails = UploadManager.UploadRequest.builder(uploadOracle.getMidia(),
                uploadOracle.getMidia().available()).allowOverwrite(true).build(request);
        uploadManager().upload(uploadDetails);
    }

}
