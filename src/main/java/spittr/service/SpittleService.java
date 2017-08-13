package spittr.service;

import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import spittr.Spittle;

import java.util.List;

/**
 * Created by wenda on 8/13/2017.
 */
public interface SpittleService {
    void addSpittle(Spittle spittle);
    void addSpittleUnSecured(Spittle spittle);
    void addSpittleJSR250(Spittle spittle);
    void addSpittlePreAuthorize(Spittle spittle);
    Spittle getSpittleById(long id);
    List<Spittle> getOffensiveSpittles();
    void deleteSpittles(List<Spittle> spittles);
    void deleteSpittlesUsingPermissionEvaluator(List<Spittle> spittles);
}
