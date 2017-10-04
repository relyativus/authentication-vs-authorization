package co.inventorsoft.vakaliuk.basic.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/protected")
public class BasicProtectedEndpoint {

    @GetMapping("/user")
    public ResponseEntity<Map<String, String>> getUserInfo(@AuthenticationPrincipal UserDetails userDetails) {
        final Map<String, String> data = new HashMap<>();
        data.put("username", userDetails.getUsername());
        data.put("authorized", "true");
        return ResponseEntity.ok(data);
    }

    @GetMapping("/admin")
    public ResponseEntity<Map<String, String>> getAdminInfo(@AuthenticationPrincipal UserDetails userDetails) {
        final Map<String, String> data = new HashMap<>();
        data.put("username", userDetails.getUsername());
        data.put("authorized", "true");
        return ResponseEntity.ok(data);
    }
}
