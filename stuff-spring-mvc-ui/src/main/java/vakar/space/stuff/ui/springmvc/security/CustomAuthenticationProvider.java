package vakar.space.stuff.ui.springmvc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import space.vakar.stuff.persistence.model.User;
import vakar.space.stuff.ui.springmvc.presenter.UserPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private UserPresenter userPresenter;

    @Autowired
    public CustomAuthenticationProvider(UserPresenter userPresenter) {
        this.userPresenter = userPresenter;
    }

    @Override
    public Authentication authenticate(Authentication authentication) {
        String userName = authentication.getName();
        String password = authentication.getCredentials().toString();
        if (!authorizedUser(userName, password)) {
            throw new AuthenticationCredentialsNotFoundException("Invalid Credentials!");
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(() -> "ROLE_USER");
        return new UsernamePasswordAuthenticationToken(userName, password, grantedAuthorities);
    }

    private boolean authorizedUser(String username, String password) {
        Optional<User> userOpt = userPresenter.findUserByUsername(username);
        return userOpt
                .filter(user -> username.equals(user.getUsername()) && password.equals(user.getPswd()))
                .isPresent();
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass);
    }
}
