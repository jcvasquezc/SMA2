package com.sma2.sma2.Synchronize;

import java.io.File;
import java.util.List;

import okhttp3.OkHttpClient;

public class FileTransmitter {

    public static final String SERVICE_URL = "http://localhost";
    public static final String FILE_SERVER_ROOT="http://localhost";
    public static final String LIBRARY="My Library"; // Find a name for the storage

    private static OkHttpClient client = new OkHttpClient();
    private static SeafileApi api = new SeafileApi(SERVICE_URL,FILE_SERVER_ROOT);

    private String username;
    private String password;
    private String token;

    public FileTransmitter(String username, String password) {
        this.username = username;
        this.password = password;
        this.token = api.obtainAuthToken(client, this.username, this.password);
    }


    public void uploadFile(File file) throws NullPointerException{

        // Variables to store the directory id and the upload link we need to upload
        String repo_id = null;
        String uploadLink = null;

        List<jsonObject.Library> libraries = api.listLibraries(client,this.token);
        //Log.e("Libraries",libraries.toString());

        // Check the list of libraries, and save the repository id if it exists.
        for(jsonObject.Library library:libraries) {
            if(library.getName().equals(LIBRARY)) {
                repo_id = library.getId();
                break;
            }
        }

       // Log.e("repo_id",repo_id);

        // Get the upload link and prepare if necessary
        if (repo_id != null) {
            uploadLink = api.getUploadLink(client, token, repo_id, "");
            if(uploadLink.contains("seafile.example.com")){
                uploadLink = uploadLink.replace("seafile.example.com","localhost");
            }
        } else {
            throw new NullPointerException("The target repository was not found!");
        }
        // Upload file
        List<jsonObject.UploadFileRes> uploadFileResList=api.uploadFile(client,token,uploadLink,"/","",file);
    }
}
