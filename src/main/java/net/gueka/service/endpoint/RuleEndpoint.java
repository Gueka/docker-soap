package net.gueka.service.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import lombok.extern.log4j.Log4j2;
import net.gueka.demo.schema.GetRuleValidationRequest;
import net.gueka.demo.schema.GetRuleValidationResponse;
import net.gueka.demo.schema.ObjectFactory;

@Log4j2
@Endpoint
public class RuleEndpoint {

	public static final String NAMESPACE_URI = "http://www.gueka.net/demo/schema";
	private static final String SUBMIT_SM_REQ = "getRuleValidationRequest";

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = SUBMIT_SM_REQ)
	@ResponsePayload
	public GetRuleValidationResponse submitSM(@RequestPayload GetRuleValidationRequest request) {
		log.info("A message just arrive with userId: " + request.getData().getUserId());
		GetRuleValidationResponse response = new ObjectFactory().createGetRuleValidationResponse();
		response.getRule().add("first test done");
		return response;
	}

}