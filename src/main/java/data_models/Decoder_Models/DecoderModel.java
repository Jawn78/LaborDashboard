package data_models.Decoder_Models;


import java.util.ArrayList;
import java.util.List;

public class DecoderModel {
    private String prefix;
    private List<DecoderModelParts> decoderModelParts = new ArrayList<>();

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public List<DecoderModelParts> getDecoderModelParts() {
        return decoderModelParts;
    }

    public void addDecoderModelParts(DecoderModelParts part) {
        this.decoderModelParts.add(part);
    }


}
