package br.com.collegesmaster.endpoint;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@FrameworkEndpoint
public class RevokeTokenEndpoint {

	@Resource(name = "tokenServices")
    ConsumerTokenServices tokenServices;
 
	@DeleteMapping("/oauth/token")
    public @ResponseBody ResponseEntity<Boolean> revokeToken(final HttpServletRequest request) {
        final String authorization = request.getHeader("TOKEN-ID");
        if (authorization != null){
            String tokenId = authorization;
            return new ResponseEntity<Boolean>(tokenServices.revokeToken(tokenId), HttpStatus.OK);
        }
        return new ResponseEntity<Boolean>(Boolean.FALSE, HttpStatus.NO_CONTENT);
    }
}
