package br.com.collegesmaster.endpoint;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@FrameworkEndpoint
public class RevokeTokenEndpoint {

	@Resource(name = "tokenServices")
    ConsumerTokenServices tokenServices;
	
	@Resource
	JdbcTokenStore jdbcTokenStore;
 
	@DeleteMapping("/oauth/token")
    public @ResponseBody ResponseEntity<Boolean> revokeToken(final HttpServletRequest request) {
        final String accessToken = request.getHeader("TOKEN-ID");
        final String refreshToken = request.getHeader("REFRESH-TOKEN-ID");
        if (accessToken != null){
        	final Boolean revoked = tokenServices.revokeToken(accessToken);
            jdbcTokenStore.removeRefreshToken(refreshToken);
            return new ResponseEntity<Boolean>(revoked, HttpStatus.OK);
        }
        return new ResponseEntity<Boolean>(Boolean.FALSE, HttpStatus.NO_CONTENT);
    }
}
