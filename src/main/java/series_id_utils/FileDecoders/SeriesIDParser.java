package series_id_utils.FileDecoders;
import data_models.Decoder_Models.DecoderModel;
import data_models.Decoder_Models.KeyPair;
import data_models.File_Decoder_Models.FileDecoderModel;
import data_models.File_Decoder_Models.FileDecoderModelParts;

import java.util.ArrayList;
import java.util.List;

import static util.SeriesIDDecoder.loadModelsWithID;

public class SeriesIDParser {

    private DecoderModel decoderModel;

    public SeriesIDParser(DecoderModel model) {
        this.decoderModel = model;
    }



    // Method to find the value for a given key
    private static String findValueForKey(List<KeyPair> keyPairs, String key) {
        int length = key.length();
        for (KeyPair keyPair : keyPairs) {
            String getKey = keyPair.getKey();
            while(getKey.length()< length){
               getKey= "0"+getKey; // add leading zeros until correct length
            }
            if (getKey.equals(key)) {
                return keyPair.getValue();
            }
        }
        return "Key not found";
    }

    public static FileDecoderModel fileDeocderModelBuilder(String seriesID){
        DecoderModel model = loadModelsWithID(seriesID);
        FileDecoderModel fileDecoderModel = new FileDecoderModel();
        ArrayList<FileDecoderModelParts> fileDecoderModelParts = new ArrayList<>();
        for(int i=0; i<model.getDecoderModelParts().size(); i++){
            FileDecoderModelParts fileDecoderModelPart = new FileDecoderModelParts();
        int start = model.getDecoderModelParts().get(i).getStart_position()-1;

        int end = model.getDecoderModelParts().get(i).getEnd_position();
        //Identifier from Model Decoder
        String identifier = model.getDecoderModelParts().get(i).getIdentifier();
        fileDecoderModelPart.setIdentifier(identifier);
        //Find Start
        fileDecoderModelPart.setStart(start);
        //Find End
        fileDecoderModelPart.setEnd(end);
        //extract Substring using Start & End
        String substring = seriesID.substring(start,end);
        fileDecoderModelPart.setSubIDString(seriesID.substring(start,end));
        //Find value for Part
        List<KeyPair> kp = model.getDecoderModelParts().get(i).getKeypairs();
        fileDecoderModelPart.setIDPartvalue(findValueForKey(kp,substring ));
        if(i>0){
            //Extract the Key's Header
            fileDecoderModelPart.setKeyHeader(model.getDecoderModelParts().get(i).getKeypairs().get(0).getKey());
            //Extract the Values's Header
            fileDecoderModelPart.setValueHeader(model.getDecoderModelParts().get(i).getKeypairs().get(0).getValue());
        }
            fileDecoderModelParts.add(fileDecoderModelPart);

        }
        fileDecoderModel.setModelParts(fileDecoderModelParts);
        return fileDecoderModel;
    }




        public static void main(String[] args) {
        String seriesID = "SMS01000000000000001";
        FileDecoderModel fileDecoderModel = fileDeocderModelBuilder(seriesID);
        for(int i=0;i<fileDecoderModel.getModelParts().size();i++){
            System.out.println("Value " + i + " : " + fileDecoderModel.ModelParts.get(i).getIDPartvalue());
            System.out.println("Identifier " + i + " : " + fileDecoderModel.ModelParts.get(i).getIdentifier());
            System.out.println("Header " + i + " : " + fileDecoderModel.ModelParts.get(i).getKeyHeader());
            System.out.println("Part Value " + i + " : " + fileDecoderModel.ModelParts.get(i).getIDPartvalue());
            System.out.print("Part Start " + i + " : " + fileDecoderModel.ModelParts.get(i).getStart()+"\n");
            System.out.print("Part End: " + i + " : " + fileDecoderModel.ModelParts.get(i).getEnd()+"\n");
            }

    }

}



