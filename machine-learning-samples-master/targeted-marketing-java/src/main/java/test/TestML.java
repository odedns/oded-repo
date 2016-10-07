package test;

import java.util.HashMap;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.machinelearning.AmazonMachineLearningAsyncClient;
import com.amazonaws.services.machinelearning.AmazonMachineLearningClient;
import com.amazonaws.services.machinelearning.model.GetMLModelRequest;
import com.amazonaws.services.machinelearning.model.GetMLModelResult;
import com.amazonaws.services.machinelearning.model.PredictRequest;
import com.amazonaws.services.machinelearning.model.PredictResult;

public class TestML {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		 
		BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAIXHYDNHEWXMKAGDQ", "vhVFeDOnhVsqC13gEV4VDCkkyoVHp3BOtV31+GLM");
		AmazonMachineLearningClient client = new AmazonMachineLearningAsyncClient(awsCreds);
		client.setRegion(Regions.EU_WEST_1);
		System.out.println("connected ....");
		String mlModelId = "ml-GQ0w7MTHB6Q";
		GetMLModelRequest modelRequest = new GetMLModelRequest().withMLModelId(mlModelId);
        GetMLModelResult model = client.getMLModel(modelRequest);
		System.out.println("got model = " + model.getName());
		
		System.out.println("model status  = " + model.getStatus());
		String predictEndpoint = model.getEndpointInfo().getEndpointUrl();
		System.out.println("predictEndPoint = " + predictEndpoint);
		predictEndpoint = "https://realtime.machinelearning.eu-west-1.amazonaws.com";
		
		HashMap<String,String> record = new HashMap<String, String>();
		record.put("Var1", "12");
		record.put("Var2", "16052016");
		record.put("Var3", "transfers-deposits");
		record.put("Var4", "-100");
		
		 PredictRequest request = new PredictRequest()
         .withMLModelId(mlModelId)
         .withPredictEndpoint(predictEndpoint)
         .withRecord(record);
		
		 
		 
		PredictResult res = client.predict(request);
		System.out.println("res = " + res);
		
	}

}
