package data_models.Decoder_Models;

public class KeyPair {
    String key;
    String value;

    public KeyPair(String key, String value) {
        this.key = key;
        this.value = value;
    }
    // Getter for the key
    public String getKey() {
        return key;
    }

    // Getter for the value
    public String getValue() {
        return value;
    }


}
