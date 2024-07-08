package data_models.File_Decoder_Models;

public class FileDecoderModelParts {
    public String Identifier;
    public String KeyHeader;
    public String ValueHeader;
    public String SubIDString;
    public String IDPartvalue;
    public int Start;
    public int end;

    public String getValueHeader() {
        return ValueHeader;
    }

    public void setValueHeader(String valueHeader) {
        ValueHeader = valueHeader;
    }

    public String getSubIDString() {
        return SubIDString;
    }

    public void setSubIDString(String subIDString) {
        SubIDString = subIDString;
    }

    public String getIDPartvalue() {
        return IDPartvalue;
    }

    public void setIDPartvalue(String IDPartvalue) {
        this.IDPartvalue = IDPartvalue;
    }

    public String getKeyHeader() {
        return KeyHeader;
    }

    public void setKeyHeader(String keyHeader) {
        KeyHeader = keyHeader;
    }

    public int getStart() {
        return Start;
    }

    public void setStart(int start) {
        Start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public String getIdentifier() {
        return Identifier;
    }

    public void setIdentifier(String identifier) {
        Identifier = identifier;
    }
}
