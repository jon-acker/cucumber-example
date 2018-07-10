package acme;

import org.h2.engine.Session;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class BasketController {

    private final HttpSession session;

    public BasketController(HttpSession session) {
        this.session = session;
    }

    @RequestMapping(value = "/basket/add")
    public String add() {
        return session.toString();
    }
}
