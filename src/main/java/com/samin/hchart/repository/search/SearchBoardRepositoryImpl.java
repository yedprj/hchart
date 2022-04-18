package com.samin.hchart.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;

import com.samin.hchart.entity.CovidBoard;
import com.samin.hchart.entity.QCovidBoard;
import com.samin.hchart.entity.QMember;
import com.samin.hchart.entity.QReply;

import lombok.extern.log4j.Log4j2;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class SearchBoardRepositoryImpl extends QuerydslRepositorySupport implements SearchBoardRepository {

    public SearchBoardRepositoryImpl(){
        super(CovidBoard.class);
    }

    @Override
    public CovidBoard search1() {

        log.info("Search1.....................");

        QCovidBoard covidBoard = QCovidBoard.covidBoard;
        QReply reply = QReply.reply;
        QMember member = QMember.member;

        JPQLQuery<CovidBoard> jpqlQuery = from(covidBoard);
        jpqlQuery.leftJoin(member).on(covidBoard.writer.eq(member));
        jpqlQuery.leftJoin(reply).on(reply.covidBoard.eq(covidBoard));

        jpqlQuery.select(covidBoard, member.email, reply.count()).groupBy(covidBoard);

        log.info("--------------------------------");
        log.info(jpqlQuery);
        log.info("--------------------------------");

        List<CovidBoard> result = jpqlQuery.fetch();

        return null;
    }

    @Override
    public Page<Object[]> searchPage(String type, String keyword, Pageable pageable) {

        log.info("searchPage...................................................");

        QCovidBoard covidBoard = QCovidBoard.covidBoard;
        QReply reply = QReply.reply;
        QMember member = QMember.member;

        JPQLQuery<CovidBoard> jpqlQuery = from(covidBoard);
        jpqlQuery.leftJoin(member).on(covidBoard.writer.eq(member));
        jpqlQuery.leftJoin(reply).on(reply.covidBoard.eq(covidBoard));

        // select b, w, count(t) from covid_Board b
        // left join b.writer w left join reply r on r.board = b
        JPQLQuery<Tuple> tuple = jpqlQuery.select(covidBoard, member, reply.count());

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        BooleanExpression expression = covidBoard.no.gt(2L);

        booleanBuilder.and(expression);

        log.info("---------------------------------------");

        if (type != null) {
            String[] typeArr = type.split("");

            BooleanBuilder conditionBuilder = new BooleanBuilder();

            for (String t : typeArr) {
                switch (t) {
                    case "t":
                        conditionBuilder.or(covidBoard.title.contains(keyword));
                        break;
                    case "w" :
                        conditionBuilder.or(member.email.contains(keyword));
                        break;
                    case "c" :
                        conditionBuilder.or(covidBoard.content.contains(keyword));
                        break;
                }
            }
            booleanBuilder.and(conditionBuilder);
        }
        tuple.where(booleanBuilder);

        Sort sort = pageable.getSort();

        sort.stream().forEach(order -> {
            Order direction = order.isAscending()? Order.ASC : Order.DESC;
            String prop = order.getProperty();

            PathBuilder orderByExpression = new PathBuilder(CovidBoard.class, "covidBoard");

            tuple.orderBy(new OrderSpecifier(direction, orderByExpression.get(prop)));
        });

        tuple.groupBy(covidBoard);

        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> result = tuple.fetch();

        log.info(result);

        long count = tuple.fetchCount();

        log.info("COUNT: " + count);

        return new PageImpl<Object[]>(
                result.stream().map(t -> t.toArray()).collect(Collectors.toList()), pageable, count);
    }
}
