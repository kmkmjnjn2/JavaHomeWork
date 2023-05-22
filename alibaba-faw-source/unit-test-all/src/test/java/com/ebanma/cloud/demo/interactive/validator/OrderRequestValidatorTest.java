package com.ebanma.cloud.demo.interactive.validator;

import com.ebanma.cloud.demo.exception.BadRequestException;
import com.ebanma.cloud.demo.interactive.request.OrderCreateRequest;
import org.assertj.core.api.AbstractThrowableAssert;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.matchers.Or;

import java.math.BigDecimal;

public class OrderRequestValidatorTest {

    private OrderRequestValidator orderRequestValidator;

    @Before
    public  void setUp() throws Exception {
        orderRequestValidator = new OrderRequestValidator();
    }

    @Test
    public void testThrowException(){
        //given
        OrderCreateRequest orderCreateRequest = new OrderCreateRequest();
        orderCreateRequest.setOrderAmountTotal(new BigDecimal(100));
        orderCreateRequest.setProductAmountTotal(new BigDecimal(50));

        //when
        AbstractThrowableAssert<?, ? extends Throwable> abstractThrowableAssert = Assertions.assertThatThrownBy(
                () -> orderRequestValidator.validateCreate(orderCreateRequest)
        );
        //then
        Assertions.assertThat(abstractThrowableAssert).isNotNull();
        abstractThrowableAssert.isInstanceOf(BadRequestException.class);
        abstractThrowableAssert.hasMessage("订单总价不能高于商品总价");
    }

}