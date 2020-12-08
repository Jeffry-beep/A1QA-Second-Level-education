package framework.db;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class DBRequestSender {

	private final SessionFactory factory;

	public DBRequestSender() {
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> select(String selectRequest, Class<T> mappingClass) {
		List<T> entitiesList = null;
		try (Session session = factory.openSession()) {
			entitiesList = session.createNativeQuery(selectRequest).addEntity(mappingClass).getResultList();
		}
		return entitiesList;
	}

	public void delete(String deleteRequest) {
		try (Session session = factory.openSession()) {
			session.beginTransaction();
			session.createNativeQuery(deleteRequest).executeUpdate();
			session.getTransaction().commit();
		}
	}
}
