package jpabook.jpashop.service;

import jpabook.jpashop.entity.*;
import jpabook.jpashop.enumtype.OrderStatus;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repo.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @PersistenceContext
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception {
        // given
        Member member = createMember();
        Item book = createBook("시골 JPA", 10, 10000);

        int orderCount = 2;

        // when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        // then
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, getOrder.getStatus());
        assertEquals("주문한 상품 종류가 수가 정확해야 한다.", 1, getOrder.getOrderItems().size());
        assertEquals("주문 가격은 가격 * 수량이다.", 10000 * orderCount, getOrder.getTotalPrice());
        assertEquals("주문 수량만큼 재고가 줄어야 한다.", 8, book.getStockQuantity());
    }

    @Test(expected = NotEnoughStockException.class)
    public void 상품주문_재고수량초과() throws Exception{
        // given
        Member member = createMember();
        Item item = createBook("시골 JPA", 10, 10000);

        int orderCount = 11;

        // when
        orderService.order(member.getId(), item.getId(), orderCount);

        // then
        fail("재고 수량 부족 예외가 발행해야 한다.");
    }

    @Test
    public void 주문취소() throws Exception {
        // given

        // when

        // then
    }

    @Test
    public void 상품주문재고수량초과() throws Exception {
        // given

        // when

        // then
    }


    // command + option + p
    // ctrl + alt + p 를 통해서 자동으로 파라미터로 변경하는 단축키를 사용해보자.
    private Item createBook(String name, int quantity, int orderPrice) {
        Item book = new Book();
        book.setName(name);
        book.setPrice(orderPrice);
        book.setStockQuantity(quantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(member);
        return member;
    }

}