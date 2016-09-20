package com.myproject.axom.priceengine.command;

import com.myproject.axom.priceengine.api.CreateProductsCommand;
import org.axonframework.repository.Repository;

import com.myproject.axom.priceengine.query.ProductRepository;
import org.axonframework.unitofwork.UnitOfWork;
import org.axonframework.unitofwork.UnitOfWorkListener;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

/**
 * @author Robin Wang 9/19/2016
 */
public class ProductCommandHandlerTest {
    private ProductCommandHandler productCommandHandler;
    @Mock
    private UnitOfWork mockUnitOfWork;

    @Mock
    private Repository<Product> mockRepository;
    @Mock
    private ProductRepository mockProductRepository;
    @Mock
    private Product mockProduct;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        productCommandHandler = new ProductCommandHandler();

        productCommandHandler.setRepository(mockRepository);
        productCommandHandler.setProductRepository(mockProductRepository);
    }


    @Test
    public void testHandleCreateContactCommand_otherProblemWithTransaction() throws Exception {
        CreateProductsCommand command = new CreateProductsCommand();
        command.setProductId("name");
        command.setProductName("name");
        command.setMarket("HH");

        productCommandHandler.handle(command, mockUnitOfWork);

        ArgumentCaptor<UnitOfWorkListener> unitOfWorkListenerArgumentCaptor =
                ArgumentCaptor.forClass(UnitOfWorkListener.class);

        verify(mockUnitOfWork).registerListener(unitOfWorkListenerArgumentCaptor.capture());

        unitOfWorkListenerArgumentCaptor.getValue().onRollback(mockUnitOfWork, new RuntimeException("Something went horribly wrong"));


    }




}
