package br.com.projeto.services;

import br.com.projeto.model.User;
import br.com.projeto.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Service
public class UserServices implements UserDetailsService {
    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(UserServices.class.getName());

    @Autowired
    UserRepository repository;
    @Autowired
    private UserRepository userRepository;

    public UserServices(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Finding one User by name!");
        var user = repository.findByUsername(username);
        if (user != null){
            return user;
        }else {
            throw new UsernameNotFoundException("" + username + " not found");
        }
    }

    public User createUser(User usuarioNovo) {
        logger.info("Criate one User!");
        var user = repository.save(usuarioNovo);
        if (user.getId() != null){
            return user;
        }else {
            throw new UsernameNotFoundException("" + usuarioNovo.getUsername() + " not found");
        }
    }
}
