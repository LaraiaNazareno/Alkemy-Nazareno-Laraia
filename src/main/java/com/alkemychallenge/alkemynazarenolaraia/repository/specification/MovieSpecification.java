package com.alkemychallenge.alkemynazarenolaraia.repository.specification;

import com.alkemychallenge.alkemynazarenolaraia.dto.MovieFilterDTO;
import com.alkemychallenge.alkemynazarenolaraia.entity.Genre;
import com.alkemychallenge.alkemynazarenolaraia.entity.Movie;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Component
public class MovieSpecification {

    public Specification<Movie> getByFilters(MovieFilterDTO filtersDTO) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filtersDTO.getTitle())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("title")),
                                "%" + filtersDTO.getTitle().toLowerCase() + "%"
                        )
                );
            }

            if (StringUtils.hasLength(filtersDTO.getCreationDate())) {
                // TODO: Reuse this in a function
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(filtersDTO.getCreationDate(), formatter);

                predicates.add(
                        criteriaBuilder.equal(root.<LocalDate>get("creationDate"), date)
                );
            }

            if (filtersDTO.getGenre()!= null) {
                Join<Genre, Movie> join = root.join("genre", JoinType.INNER);
                Expression<String> genreId = join.get("id");
                predicates.add(genreId.in(filtersDTO.getGenre()));
            }

            // Remove duplicates
            query.distinct(true);

            // Order resolver
            String orderByField = "creationDate";
            query.orderBy(
                    filtersDTO.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))
            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }



}
