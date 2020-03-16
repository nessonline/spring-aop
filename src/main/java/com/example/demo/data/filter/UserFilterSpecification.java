package com.example.demo.data.filter;

import com.example.demo.data.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class UserFilterSpecification implements Specification<User> {

    private final UserFilter filter;

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        if (isEmptyFilter()) return cb.disjunction();

        List<Predicate> predicateList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(filter.getId())) {
            predicateList.add(root.get("id").in(filter.getId()));
        }
        if (!StringUtils.isEmpty(filter.getLogin())) {
            predicateList.add(cb.equal(root.get("login"), filter.getLogin()));
        }

        return cb.and(predicateList.toArray(new Predicate[0]));
    }

    public static Sort getSort() {
        return Sort.by("id");
    }

    private boolean isEmptyFilter() {
        return CollectionUtils.isEmpty(filter.getId())
                && StringUtils.isEmpty(filter.getLogin())
                ;
    }
}
