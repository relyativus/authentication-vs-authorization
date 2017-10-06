package co.inventorsoft.vakaliuk.oauth.server;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ProtectedEndpoint {

    @GetMapping("/protected/user")
    public ResponseEntity showProtectedData() {
        final Map<String, Object> map = new HashMap<>();
        map.put("message", "You can access protected resource");
        return ResponseEntity.ok(map);
    }
}
