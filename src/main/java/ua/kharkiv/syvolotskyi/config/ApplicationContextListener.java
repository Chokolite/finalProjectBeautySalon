package ua.kharkiv.syvolotskyi.config;

import org.apache.log4j.Logger;
import ua.kharkiv.syvolotskyi.repository.*;
import ua.kharkiv.syvolotskyi.repository.impl.*;
import ua.kharkiv.syvolotskyi.service.*;
import ua.kharkiv.syvolotskyi.service.impl.*;
import ua.kharkiv.syvolotskyi.transaction.TransactionManager;
import ua.kharkiv.syvolotskyi.transaction.impl.TransactionManagerImpl;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;


public class ApplicationContextListener implements ServletContextListener {

    private static final Logger LOGGER = Logger.getLogger(ApplicationContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        LOGGER.debug("context init start");
        ServletContext context = servletContextEvent.getServletContext();

        TransactionManager transactionManager = initTransactionManager();
        initServices(context, transactionManager);
        LOGGER.debug("context init finish");
    }

    private void initServices(ServletContext context, TransactionManager transactionManager) {
        UserRepository userRepository = new UserRepositoryImpl();
        UserService userService = new UserServiceImpl(transactionManager, userRepository);
        ServiceRepository serviceRepository = new ServiceRepositoryImpl();
        ServiceService serviceService = new ServiceServiceImpl(transactionManager, serviceRepository);
        CatalogRepository catalogRepository = new CatalogRepositoryImpl();
        CatalogService catalogService = new CatalogServiceImpl(transactionManager, catalogRepository);
        AppointmentRepository appointmentRepository = new AppointmentRepositoryImpl();
        AppointmentService appointmentService = new AppointmentServiceImpl(transactionManager, appointmentRepository);
        ReviewRepository reviewRepository = new ReviewRepositoryImpl();
        ReviewService reviewService = new ReviewServiceImpl(transactionManager, reviewRepository);


        context.setAttribute(UserService.class.toString(), userService);
        context.setAttribute(ServiceService.class.toString(), serviceService);
        context.setAttribute(CatalogService.class.toString(), catalogService);
        context.setAttribute(AppointmentService.class.toString(), appointmentService);
        context.setAttribute(ReviewService.class.toString(), reviewService);
    }

    private TransactionManager initTransactionManager() {
        try {
            DataSource dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/mydb"); ///node JNDI tree for jdbc
            return new TransactionManagerImpl(dataSource);
        } catch (NamingException e) {
            throw new RuntimeException("data source cant init");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}