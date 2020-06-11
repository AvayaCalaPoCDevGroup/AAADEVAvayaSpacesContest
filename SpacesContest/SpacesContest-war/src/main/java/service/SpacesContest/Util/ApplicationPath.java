package service.SpacesContest.Util;

import java.io.File;
import java.net.URISyntaxException;
import java.security.CodeSource;

public class ApplicationPath {
    public static String getApplcatonPath(){
        CodeSource codeSource = ApplicationPath.class.getProtectionDomain().getCodeSource();
        File rootPath = null;
        try {
            rootPath = new File(codeSource.getLocation().toURI().getPath());
        } catch (URISyntaxException e) {
          return e.toString();
        }           
        return rootPath.getParentFile().getPath();
    }//end of getApplcatonPath()
}
