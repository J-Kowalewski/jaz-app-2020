package pl.edu.pjwstk.jaz.endpoints;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@PreAuthorize("hasAnyAuthority('admin')")
@RestController
public class Edit {
    @GetMapping("/edit")
    public void edit(){

    }
}
