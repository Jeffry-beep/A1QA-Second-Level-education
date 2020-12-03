package framework.db;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

public class DBRequestSender {

	private final SessionFactory factory;

	public DBRequestSender() {
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> select(String selectRequest) {
		try (Session session = factory.openSession()) {
			return session.createNativeQuery(selectRequest).getResultList();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> select(String selectRequest, String... parameters) {
		try (Session session = factory.openSession()) {
			Query<Object[]> query = session.createNativeQuery(selectRequest);
			for (int i = 1; i <= parameters.length; i++) {
				query.setParameter(i, parameters[i - 1]);
			}
			return query.getResultList();
		}
	}

}
