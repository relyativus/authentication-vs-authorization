package co.inventorsoft.vakaliuk.oauth.client.flow;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Controller
public class CodeFlowEndpoint {

    private static final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/code")
    public String showCodeIndex() {
        return "code-index";
    }

    @GetMapping("/implicit")
    public String showImplicitIndex() {
        return "implicit-index";
    }

    @GetMapping("/code-verify")
    public ResponseEntity verify(final @RequestParam String code) {
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("code", code);
        params.add("redirect_uri", "http://localhost:8081/code-verify");
        params.add("client_id", "untrusted");

        final RequestEntity<MultiValueMap<String, String>> tokenRequest = RequestEntity
                .post(URI.create("http://localhost:8080/oauth/token"))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .header(HttpHeaders.AUTHORIZATION, "Basic dW50cnVzdGVkOnRlc3Q=")
                .body(params);

        final ResponseEntity<DefaultOAuth2AccessToken> tokenResponse = restTemplate.exchange(tokenRequest,
                DefaultOAuth2AccessToken.class);
        return ResponseEntity.ok(tokenResponse.getBody());
    }

    @GetMapping("/implicit-verify")
    public ResponseEntity verify() {
        return ResponseEntity.ok(200);
    }

}
