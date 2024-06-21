package data_models.Decoder_Models;


import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;


public class DecoderModelParts {
    public int part_number;
    public String identifier;
    public int start_position;
    public int end_position;
    public List<KeyPair> keypairs;

    public int getPart_number() {
        return part_number;
    }

    public void setPart_number(int part_number) {
        this.part_number = part_number;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public int getStart_position() {
        return start_position;
    }

    public void setStart_position(int start_position) {
        this.start_position = start_position;
    }

    public int getEnd_position() {
        return end_position;
    }

    public void setEnd_position(int end_position) {
        this.end_position = end_position;
    }
    public List<KeyPair> getKeypairs() {
        return keypairs;
    }

    public void setKeypairs(List<KeyPair> keypairs) {
        this.keypairs = keypairs;
    }


}
