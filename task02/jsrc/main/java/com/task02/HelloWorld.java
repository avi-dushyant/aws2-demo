package com.task02;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.syndicate.deployment.annotations.lambda.LambdaHandler;
import com.syndicate.deployment.model.RetentionSetting;

import java.util.HashMap;
import java.util.Map;
@LambdaHandler(
        lambdaName = "hello_world",
        roleName = "hello_world-role",
        isPublishVersion = false,
        aliasName = "learn",
        logsExpiration = RetentionSetting.SYNDICATE_ALIASES_SPECIFIED
)
public class HelloWorld implements RequestHandler<Map<String, Object>, Map<String, Object>> {
    @Override
    public Map<String, Object> handleRequest(Map<String, Object> request, Context context) {
        String path = (String) request.get("path");
        String method = (String) request.get("httpMethod");

        Map<String, Object> responseMap = new HashMap<>();
        if (path.contains("/hello") && "GET".equalsIgnoreCase(method)) {
            responseMap.put("statusCode", 200);
            responseMap.put("message", "Hello from Lambda");
        } else {
            responseMap.put("statusCode", 400);
            responseMap.put("message", String.format(
                    "Bad request syntax or unsupported method. Request path: %s. HTTP method: %s",
                    path, method
            ));       }
        return responseMap;
    }
}




//@LambdaHandler(
//    lambdaName = "hello_world",
//	roleName = "hello_world-role",
//	isPublishVersion = true,
//	aliasName = "learn",
//	logsExpiration = RetentionSetting.SYNDICATE_ALIASES_SPECIFIED
//)
//public class HelloWorld implements RequestHandler<Object, Map<String, Object>> {
//
//	public Map<String, Object> handleRequest(Object request, Context context) {
//		System.out.println("Hello from lambda");
//		Map<String, Object> resultMap = new HashMap<String, Object>();
//		resultMap.put("statusCode", 200);
//		resultMap.put("message", "Hello from Lambda");
//		return resultMap;
//	}
//}
