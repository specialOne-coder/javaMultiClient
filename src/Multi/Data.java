package Multi;

import java.io.Serializable;
import javax.swing.ImageIcon;

public class Data implements Serializable {


    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
    

    
    private String Status;
    private String name;
    private ImageIcon image;
    private byte[] file;
}
