package acme;

import org.h2.engine.Session;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LibraryController {

    private final HttpSession session;

    public LibraryController(HttpSession session) {
        this.session = session;
    }

    @RequestMapping(value = "/register")
    public String add() {
        return session.toString();
    }
}
