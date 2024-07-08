package data_models.File_Decoder_Models;

import java.util.ArrayList;

public class FileDecoderModel {
   public String SeriesID;
    public ArrayList<FileDecoderModelParts> ModelParts;


    public ArrayList<FileDecoderModelParts> getModelParts() {
        return ModelParts;
    }

    public void setModelParts(ArrayList<FileDecoderModelParts> modelParts) {
        ModelParts = modelParts;
    }

    public void addModelParts(FileDecoderModelParts modelPart) {
        this.ModelParts.add(modelPart);
    }

    public String getSeriesID() {
        return SeriesID;
    }

    public void setSeriesID(String seriesID) {
        SeriesID = seriesID;
    }


}
