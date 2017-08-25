package spittr.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;
import spittr.Spittle;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wenda on 8/13/2017.
 */
@Service("spittleService")
public class SpittleServiceImpl implements SpittleService {
    @Override
    @Secured("ROLE_SPITTER")
    public void addSpittle(Spittle spittle) {
        System.out.println(" @Secured(\"ROLE_SPITTER\")");
    }

    @Override
    public void addSpittleUnSecured(Spittle spittle) {
        System.out.println(" unsecured method");
    }

    @Override
    @RolesAllowed("ROLE_SPITTER")
    public void addSpittleJSR250(Spittle spittle) {
        System.out.println(" JSR250 @RolesAllowed(\"ROLE_SPITTER\")");
    }

    @PreAuthorize("(hasRole('ROLE_SPITTER') and #spittle.message.length() <= 10) or hasRole('ROLE_PREMIUM')")
    public void addSpittlePreAuthorize(Spittle spittle) {
        System.out.println("@PreAuthorize(\"(hasRole('ROLE_SPITTER') and #spittle.message.length() <= 10) or hasRole('ROLE_PREMIUM')\")");
    }

//    @PostAuthorize("returnObject.message == principal.username")
    public Spittle getSpittleById(long id) {
        System.out.println("@PostAuthorize(\"returnObject.spitter.username == principal.username\")");
        Spittle spittle = new Spittle(id, "rpc for sptittle", new Date(), 1.0, 1.0);
        return spittle;
    }

    @PreAuthorize("hasAnyRole({'ROLE_SPITTER', 'ROLE_ADMIN'})")
    @PostFilter("hasRole('ROLE_ADMIN') || filterObject.message == principal.username")
    public List<Spittle> getOffensiveSpittles() {
        List<Spittle> spittleList = new ArrayList<Spittle>();
        for (int i = 0; i < 5; i++) {
            spittleList.add(new Spittle("message-" + i, new Date()));
        }
        spittleList.add(new Spittle("spitter", new Date()));
        spittleList.add(new Spittle("user", new Date()));
        spittleList.add(new Spittle("admin", new Date()));
        return spittleList;
    }

    @PreAuthorize("hasAnyRole({'ROLE_SPITTER', 'ROLE_ADMIN'})")
    @PreFilter("hasRole('ROLE_ADMIN') || filterObject.message == principal.username")
    public void deleteSpittles(List<Spittle> spittles) {
        System.out.println("@PreFilter(\"hasRole('ROLE_ADMIN') || filterObject.message == principal.username\")");
        for (Spittle spittle : spittles) {
            System.out.println(spittle);
        }
    }

    @PreAuthorize("hasAnyRole({'ROLE_SPITTER', 'ROLE_ADMIN'})")
    @PreFilter("hasPermission(targetObject, 'delete')")
    public void deleteSpittlesUsingPermissionEvaluator(List<Spittle> spittles) {
        System.out.println( " @PreFilter(\"hasPermission(targetObject, 'delete')\")");
        for (Spittle spittle : spittles) {
            System.out.println(spittle);
        }
    }
}